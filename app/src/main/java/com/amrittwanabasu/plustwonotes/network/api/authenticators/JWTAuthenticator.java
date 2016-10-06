package com.amrittwanabasu.plustwonotes.network.api.authenticators;

import android.content.Context;

import com.amrittwanabasu.plustwonotes.auth.Guard;
import com.amrittwanabasu.plustwonotes.models.Token;
import com.amrittwanabasu.plustwonotes.network.api.ApiConstants;
import com.amrittwanabasu.plustwonotes.network.api.ServiceFactory;
import com.amrittwanabasu.plustwonotes.network.api.services.AuthService;

import java.io.IOException;

import okhttp3.Route;
import okhttp3.Request;
import okhttp3.Response;

import retrofit2.Call;
import okhttp3.Authenticator;

public class JWTAuthenticator implements Authenticator {

    /**
     * Authentication Guard instance.
     */
    private Guard guard;

    /**
     * Application context.
     */
    private Context context;

    /**
     * Construct JWTAuthenticator.
     *
     * @param context Application Context
     */
    public JWTAuthenticator(Context context) {
        this.context = context;
        this.guard = new Guard(this.context);
    }

    /**
     * Authenticates the requests.
     *
     * @param route     Route of the request.
     * @param response  Response got from previous unauthorized request.
     * @return          New request with authorization header.
     * @throws IOException
     */
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        AuthService authClient = ServiceFactory.makeService(this.context,AuthService.class);

        Call<Token> call = authClient.authenticate(ApiConstants.API_ID, ApiConstants.API_SECRET);

        Token token = call.execute().body();

        if(token == null) {
            // don't retry again if no token.
            return null;
        }

        this.guard.setToken(token.getToken());

        return attachToken(response,token.getToken());
    }

    /**
     * Attaches Authentication header to new request.
     *
     * @param response  Response of which new request is to be sent.
     * @param token     Authentication token to be attached.
     * @return          New request with authentication headers.
     */
    private Request attachToken(Response response,String token) {
        return response.request().newBuilder()
                        .header("Authorization","Bearer " + token)
                        .build();
    }
}
