package com.example.rafaelchevez.clientpet.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.rafaelchevez.clientpet.MainActivity;
import com.example.rafaelchevez.clientpet.R;
import com.example.rafaelchevez.clientpet.iRetrofit.PetService;
import com.example.rafaelchevez.clientpet.model.Pet;
import com.example.rafaelchevez.clientpet.retrofitRemote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListPet extends AppCompatActivity {

    private PetService petService;
    public ListView listView;

    List<Pet> list = new ArrayList<Pet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pet);

        listView = (ListView) findViewById(R.id.listV);
        petService = ApiUtils.getUserService();

        list_pets();

    }

    public void list_pets() {

        Call<List<Pet>> call = petService.list();
        call.enqueue(new Callback<List<Pet>>() {

            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                list = response.body();
                listView.setAdapter(new ActivityAdapterList(ActivityListPet.this,R.layout.list_pet,list));
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {

                Log.e("ERROR: ", t.getMessage());

            }
        });
    }


}
