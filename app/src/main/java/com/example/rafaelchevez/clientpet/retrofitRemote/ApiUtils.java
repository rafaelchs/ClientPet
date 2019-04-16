package com.example.rafaelchevez.clientpet.retrofitRemote;

import com.example.rafaelchevez.clientpet.iRetrofit.PetService;

public class ApiUtils {

    public static final String API_URL = "http://192.168.100.1:8080/pet/";


    public static PetService getUserService(){
        return RetrofitClient.getClient(API_URL).create(PetService.class);
    }
}
