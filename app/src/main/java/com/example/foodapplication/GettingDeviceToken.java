package com.example.foodapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class GettingDeviceToken extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String Devicetoken = FirebaseInstanceId.getInstance().getToken();

    }


}
