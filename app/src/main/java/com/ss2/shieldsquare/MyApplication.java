package com.ss2.shieldsquare;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shieldsquare.mobile_captcha_sdk.captcha.TextCaptcha;
import com.shieldsquare.mobile_captcha_sdk.core.ShieldSquare;

public class MyApplication extends Application {

     final static String CID = "ad34";

    @Override
    public void onCreate() {
        super.onCreate();

        TextCaptcha textCaptcha = new TextCaptcha.Builder()
                .build();

        ShieldSquare shieldSquare = new ShieldSquare.Builder(this)
                .setSubscriberID(CID)
                .setCaptchaOption(textCaptcha)
                .build();

        shieldSquare.setUserId("testApp1");
    }
}
