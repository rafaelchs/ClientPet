package com.example.rafaelchevez.clientpet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rafaelchevez.clientpet.activity.ActivityAdapterList;
import com.example.rafaelchevez.clientpet.activity.ActivityListPet;
import com.example.rafaelchevez.clientpet.iRetrofit.PetService;
import com.example.rafaelchevez.clientpet.model.Pet;
import com.example.rafaelchevez.clientpet.retrofitRemote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText txtname;
    private EditText txtPhone;
    private Button btnSave, btnShow;
    public PetService petService;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtname = (EditText) findViewById(R.id.editTextName);
        txtPhone = (EditText) findViewById(R.id.editTextTelephone);
        btnSave = (Button) findViewById(R.id.btnSend);
        petService = ApiUtils.getUserService();


    }

   public void addPet(View v){

       if(txtname.getText().toString().isEmpty() || txtPhone.getText().toString().isEmpty()){

           Toast.makeText(MainActivity.this, "pr", Toast.LENGTH_SHORT).show();
       }else{

        Pet obj = new Pet();
        obj.setName(txtname.getText().toString());
        obj.setTelephone(txtPhone.getText().toString());
        add_new_pet(obj);
        clean();
        Toast.makeText(MainActivity.this, "Added successfully!", Toast.LENGTH_SHORT).show();
       }

   }

   public void add_new_pet(Pet objPet){

        Call<Pet> call = petService.insert_pet(objPet);
        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {

                if (response.isSuccessful()){

                    Toast.makeText(MainActivity.this, "Added successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {

                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void showList(View view){

        Intent inten = new Intent(MainActivity.this,ActivityListPet.class);
        startActivity(inten);

    }

    public void clean(){

        txtname.setText(" ");
        txtPhone.setText(" ");
    }



}
