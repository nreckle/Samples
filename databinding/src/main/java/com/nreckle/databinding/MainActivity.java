package com.nreckle.databinding;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.nreckle.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private Handler mUIHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        // Use without view binding
        // ActivityMainBinding binding =
        //         DataBindingUtil.setContentView(this, R.layout.activity_main);
        final User user = new User("Binding Data User's FirstName", 15);
        activityMainBinding.setUser(user);

        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                user.setFirstName("Modified firstName");
                user.setAge(28);
            }
        });

        final ObservableFieldsUser fieldsUser = new ObservableFieldsUser();
        fieldsUser.firstName.set("Observable Fields User");
        fieldsUser.age.set(25);
        activityMainBinding.setObservableFieldsUser(fieldsUser);

        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                fieldsUser.firstName.set("Observable Modified firstName");
                fieldsUser.age.set(26);
            }
        });

        final ObservableUser observableUser = new ObservableUser("Observable User", 25, 1234);
        activityMainBinding.setObservableUser(observableUser);

        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                observableUser.setFirstName("Observable User Modified firstName");
                observableUser.setTestInt(54321);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityMainBinding = null;
    }
}
