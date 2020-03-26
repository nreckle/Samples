package com.nreckle.databinding;

public class User {
    private String firstName;
    public int age;

    public User(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}
