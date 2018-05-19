package com.example.lela.novikviz;

import android.app.Application;
import com.firebase.client.Firebase;


/**
 * Created by Lela on 23.8.2017..
 */

public class NoviKviz extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
