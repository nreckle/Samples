package com.nreckle.databinding

import androidx.databinding.Bindable

open class ObservableUser(private var firstName: String, private var age: Int, private var testInt: Int) : ObservableViewModel() {

    @Bindable
    fun getTestInt(): Int {
        return testInt
    }

    fun setTestInt(value: Int) {
        this.testInt = value
        // Even if the input is empty, force a refresh of the view
        notifyPropertyChanged(BR.testInt)
    }

    @Bindable
    fun getAge(): Int {
        return age
    }

    @Bindable
    fun getFirstName(): String {
        return firstName
    }

    fun setFirstName(firstName: String) {
        if (this.firstName != firstName) {
            this.firstName = firstName
            notifyPropertyChanged(BR.firstName)
        }
    }

    fun setAge(age: Int) {
        if (this.age != age) {
            this.age = age
            notifyPropertyChanged(BR.age)
        }
    }

    override fun toString(): String {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                '}'
    }

}