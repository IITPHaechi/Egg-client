package com.example.cholmink.joystick_alahu;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by cholmink on 16. 9. 25..
 */
public class ApplicationController extends Application{
    private static ApplicationController instance;
    public static ApplicationController getInstance(){return instance;}

    final String baseURL="http://172.24.1.1";

    private NetworkService networkService;
    public NetworkService getNetworkService(){return networkService;}

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyTag", "가장먼저 실행되는 Application Controller");
        ApplicationController.instance=this;
        this.buildService();
    }
    private void buildService(){
        synchronized (ApplicationController.class){
            Gson gson = new GsonBuilder()
                    .create();
            GsonConverterFactory factory = GsonConverterFactory.create(gson);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(factory)
                    .build();


            networkService = retrofit.create(NetworkService.class);
        }
    }
}
