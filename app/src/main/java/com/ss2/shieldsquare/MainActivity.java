package com.ss2.shieldsquare;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ss2.shieldsquare.Network.API;
import com.ss2.shieldsquare.Network.RetrofitClientInstance;



import com.shieldsquare.mobile_captcha_sdk.core.ShieldSquare;
import com.shieldsquare.mobile_captcha_sdk.utils.Utils;


import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    final String INTRO_MESSAGE = "Hi Welcome to ShieldSquare !!";
    TextView tvWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAction;

        tvWelcome=(TextView)findViewById(R.id.welcomeText);
        btnAction=(Button)findViewById(R.id.btnAction);

        tvWelcome.setText(INTRO_MESSAGE);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            tvWelcome.setText(INTRO_MESSAGE);
            performNetworkOperation();
            }
        });
    }

    private void performNetworkOperation() {
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        RetrofitClientInstance.getRetrofit().create(API.class)
                .callSample()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        progressBar.setVisibility(View.GONE);
                        tvWelcome.setText("Received response from server");
                        Utils.Logger.log(String.format(Locale.getDefault(),
                                "Response from: %s \nHeaders: %s \nResponse body: %s",
                                call.request().url(), response.headers(), response.body()),
                                Utils.Logger.DEBUG);
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        tvWelcome.setText(t.getMessage());
                        Utils.Logger.log(t.getMessage(), Utils.Logger.DEBUG);
                    }
                });

    }
}
