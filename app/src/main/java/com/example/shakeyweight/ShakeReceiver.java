package com.example.shakeyweight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShakeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != intent && intent.getAction().equals("shake.detector")) {
        }
    }
    //Im not entirely sure what running this in the background does but it does something that we may need in the future
    //This code literally does nothing at the current point except prevent an error in android manifest for the receiver thingy

}
