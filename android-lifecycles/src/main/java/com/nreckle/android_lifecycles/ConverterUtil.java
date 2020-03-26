package com.nreckle.android_lifecycles;

import androidx.databinding.BindingConversion;

import java.util.Date;

public class ConverterUtil {
    @BindingConversion
    public static String millionsToDate(long timeInMillions) {
        return new Date(timeInMillions).toString();
    }
}
