package com.ss2.shieldsquare.Network;

import com.shieldsquare.mobile_captcha_sdk.core.ShieldSquareInterceptor;

import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RetrofitClientInstance {
    private static final String BASE_URL = "http://18.188.188.75/mobile/test.html";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new ShieldSquareInterceptor())
                    .cookieJar(com.shieldsquare.mobile_captcha_sdk.core.CookieManager.provideCookieJar())
                    .readTimeout(5, TimeUnit.MINUTES)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build();
        }

        return retrofit;
    }
}
