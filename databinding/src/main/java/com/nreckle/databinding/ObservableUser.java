package com.nreckle.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ObservableUser extends BaseObservable {
    private String firstName;
    private int age;

    public ObservableUser(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    @Bindable
    public int getAge() {
        return age;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}
