package com.nreckle.databinding.util;

import android.view.View;

import androidx.databinding.BindingConversion;

public class ConverterUtil {
    public static boolean isZero(int number) {
        return number == 0;
    }

    @BindingConversion
    public static int booleanToVisibility(boolean isNotVisible) {
        return isNotVisible ? View.GONE : View.VISIBLE;
    }
}
