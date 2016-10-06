package com.amrittwanabasu.plustwonotes.network.api.interceptors;

import android.content.Context;

import com.amrittwanabasu.plustwonotes.auth.Guard;
import com.amrittwanabasu.plustwonotes.network.api.ApiConstants;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Interceptor;

public class AuthInterceptor implements Interceptor {

    /**
     * Auth Guard instance.
     */
    private Guard guard;

    /**
     * Construct AuthInterceptor instance.
     *
     * @param context   Application Context
     */
    public AuthInterceptor(Context context) {
        this.guard = new Guard(context);
    }

    /**
     * Intercepts an request.
     *
     * @param chain     Request Chain.
     * @return          Response of the request.
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if(( ! request.url().toString().contentEquals(ApiConstants.API_AUTH_URL)) && guard.isAuthenticated() && (request.header("Authorization") == null)) {
            return attachToken(chain);
        }

        return chain.proceed(request);
    }

    /**
     * Attaches a token to the request.
     *
     * @param chain     Request chain.
     * @return          Response of the current request.
     * @throws IOException
     */
    private Response attachToken(Chain chain) throws IOException{
        return chain.proceed(chain.request()
                                .newBuilder()
                                .header("Authorization", "Bearer " + guard.getToken())
                                .build());
    }
}
