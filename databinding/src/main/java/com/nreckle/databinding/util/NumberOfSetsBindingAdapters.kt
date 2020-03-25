/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nreckle.databinding.util

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseMethod


/**
 * The [EditText] that controls the number of sets is using two-way Data Binding. Applying a
 * 2-way expression to the `android:text` attribute of the EditText triggers an update on every
 * keystroke. This is an alternative implementation that uses a [View.OnFocusChangeListener]
 * instead.
 *
 * `numberOfSetsAttrChanged` creates a listener that triggers when the focus is lost
 *
 * `hideKeyboardOnInputDone` (in a different file) will clear focus when the `Done` action on
 * the keyboard is dispatched, triggering `numberOfSetsAttrChanged`.
 */
object NumberOfSetsBindingAdapters {

    /**
     * Needs to be used with [NumberOfSetsConverters.setArrayToString].
     */
    @BindingAdapter("numberOfSets")
    @JvmStatic
    fun setNumberOfSets(view: EditText, value: String) {
        view.setText(value)
    }

    /**
     * Called when the [InverseBindingListener] of the `numberOfSetsAttrChanged` binding adapter
     * is notified of a change.
     *
     * Used with the inverse method of [NumberOfSetsConverters.setArrayToString], which is
     * [NumberOfSetsConverters.stringToSetArray].
     */
    @InverseBindingAdapter(attribute = "numberOfSets")
    @JvmStatic
    fun getNumberOfSets(editText: EditText): String {
        return editText.text.toString()
    }

    /**
     * That this Binding Adapter is not defined in the XML. "AttrChanged" is a special
     * suffix that lets you manage changes in the value, using two-way Data Binding.
     *
     * Note that setting a [View.OnFocusChangeListener] overrides other listeners that might be set
     * with `android:onFocusChangeListener`. Consider supporting both in the same binding adapter
     * with `requireAll = false`. See [android.databinding.adapters.CompoundButtonBindingAdapter]
     * for an example.
     */
    @BindingAdapter("numberOfSetsAttrChanged")
    @JvmStatic
    fun setListener(view: EditText, listener: InverseBindingListener?) {
        view.onFocusChangeListener = View.OnFocusChangeListener { focusedView, hasFocus ->
            val textView = focusedView as TextView
            if (hasFocus) {
                // Delete contents of the EditText if the focus entered.
                textView.text = ""
            } else {
                // If the focus left, update the listener
                listener?.onChange()
            }
        }
    }
}

/**
 * Converters for the number of sets attribute.
 */
object NumberOfSetsConverters {

    /**
     * Used with `numberOfSets` to convert from array to String.
     */
    @InverseMethod("stringToSetArray")
    @JvmStatic
    fun setArrayToString(value: Int): String {
        return String.format("Sets: %d", value)
    }

    /**
     * This is the Inverse Method used in `numberOfSets`, to convert from String to array.
     *
     * Note that Context is passed
     */
    @JvmStatic
    fun stringToSetArray(value: String): Int {
        // Converts String to long
        if (value.isEmpty()) {
            return 0
        }

        return try {
            value.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }
}