package com.nreckle.databinding;

import androidx.databinding.ObservableField;

public class ObservableFieldsUser {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<Integer> age = new ObservableField<>();

    @Override
    public String toString() {
        return "ObservableFieldsUser{" +
                "firstName=" + firstName.get() +
                ", age=" + age.get() +
                '}';
    }
}
