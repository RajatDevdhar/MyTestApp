package com.ss2.shieldsquare.Network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface API {

    @Headers({
            "Cache-Control: max-age=640000",
            "User-Agent: ShieldSquare-TestApp",
            "Accept: application/vnd.yourapi.v1.full+json"})
    @GET("/mobile/test.html")
    Call<ResponseBody> callSample();
}
