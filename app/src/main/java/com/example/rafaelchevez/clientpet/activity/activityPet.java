package com.example.rafaelchevez.clientpet.activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafaelchevez.clientpet.MainActivity;
import com.example.rafaelchevez.clientpet.R;
import com.example.rafaelchevez.clientpet.iRetrofit.PetService;
import com.example.rafaelchevez.clientpet.model.Pet;
import com.example.rafaelchevez.clientpet.retrofitRemote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityPet extends AppCompatActivity {

    private EditText txtname;
    private EditText txtPhone;
    private String petName;
    private String phone;
    private String idPet;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnCall;
    private PetService petService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

         txtname = (EditText) findViewById(R.id.editTextName);
         txtPhone = (EditText) findViewById(R.id.editTextPhone);
         btnDelete = (Button) findViewById(R.id.buttonDelete);
         btnUpdate = (Button) findViewById(R.id.buttonUpdate);
         btnCall = (Button) findViewById(R.id.buttonCall);
         petService = ApiUtils.getUserService();


        Bundle extra = getIntent().getExtras();

        idPet = extra.getString("id");
        petName = extra.getString("petName");
        phone = extra.getString("phone");

        txtname.setText(petName);
        txtPhone.setText(phone);
    }

    public void onClick_delete(View v){

        AlertDialog.Builder alert = new AlertDialog.Builder(activityPet.this);
            alert.setMessage("Sure you want to delete")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delete_pet(Integer.valueOf(idPet));
                            back();
                        }
                    })
                    .setNegativeButton("No",null);

                    alert.show();


    }

    public void delete_pet(int id){

        Call<Pet> call = petService.delete_pet(id);
        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {

                if (response.isSuccessful()){

                    Toast.makeText(activityPet.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {

                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    public void onClick_update(View v){


        if (txtname.getText().toString().isEmpty() || txtPhone.getText().toString().isEmpty()){

            Toast.makeText(activityPet.this, "Error", Toast.LENGTH_SHORT).show();

        }else {

            Pet obj = new Pet();
            obj.setId(Integer.parseInt(idPet));
            obj.setName(txtname.getText().toString());
            obj.setTelephone(txtPhone.getText().toString());

            update_pet(Integer.parseInt(idPet), obj);

            Toast.makeText(activityPet.this, "successfully", Toast.LENGTH_SHORT).show();
            back();
        }


    }

    public void update_pet(int id, Pet objPet){

        Call<Pet> call = petService.update_pet(id,objPet);
        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {

                Toast.makeText(activityPet.this, "successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {

                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void onClick_call(View v){

        String uri = "tel:" + phone;

        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(uri)));
    }

    public void back(){

        Intent intent = new Intent(activityPet.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
