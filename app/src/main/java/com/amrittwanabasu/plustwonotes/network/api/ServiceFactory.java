package com.amrittwanabasu.plustwonotes.network.api;

import android.content.Context;

import com.amrittwanabasu.plustwonotes.network.api.authenticators.JWTAuthenticator;
import com.amrittwanabasu.plustwonotes.network.api.interceptors.AuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    /**
     * OkHttpClient Builder instance.
     */
    private static OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

    /**
     * Retrofit instance.
     */
    private static Retrofit.Builder builder = new Retrofit.Builder()
                                                    .baseUrl(ApiConstants.API_URL)
                                                    .addConverterFactory(GsonConverterFactory.create());

    /**
     * Creates a service instance.
     *
     * @param context       Application context.
     * @param serviceClass  Service class to be instantiated.
     * @param <T>           Type of Service class.
     * @return              Service class instance.
     */
    public static <T> T makeService(Context context, Class<T> serviceClass) {
        OkHttpClient client = clientBuilder.addInterceptor(new AuthInterceptor(context))
                                            .authenticator(new JWTAuthenticator(context))
                                            .build();

        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);
    }
}
