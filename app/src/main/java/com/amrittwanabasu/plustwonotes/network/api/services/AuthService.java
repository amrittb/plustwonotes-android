package com.amrittwanabasu.plustwonotes.network.api.services;

import com.amrittwanabasu.plustwonotes.models.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;

public interface AuthService {

    @FormUrlEncoded
    @POST("authenticate")
    Call<Token> authenticate(@Field("identifier") String identifier, @Field("secret") String secret);
}
