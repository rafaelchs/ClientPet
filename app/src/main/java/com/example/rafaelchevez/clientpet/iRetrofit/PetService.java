package com.example.rafaelchevez.clientpet.iRetrofit;

import com.example.rafaelchevez.clientpet.model.Pet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PetService {

    @POST("add")
    Call<Pet>insert_pet(@Body Pet objPet);

    @GET("show")
    Call<List<Pet>>list();

    @DELETE("delete/{id}")
    Call<Pet>delete_pet(@Path("id") int id);

    @PUT("update/{id}")
    Call<Pet>update_pet(@Path("id") int id,@Body Pet objPet);

}
