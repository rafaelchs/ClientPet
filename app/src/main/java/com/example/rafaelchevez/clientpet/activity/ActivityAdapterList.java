package com.example.rafaelchevez.clientpet.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafaelchevez.clientpet.R;
import com.example.rafaelchevez.clientpet.model.Pet;

import java.util.List;

public class ActivityAdapterList extends ArrayAdapter<Pet>{

    private Context context;
    private List<Pet> listPet;


    public ActivityAdapterList(@NonNull Context context, @LayoutRes int resource, @NonNull List<Pet> objects) {
        super(context, resource,objects);
        this.context = context;
        this.listPet = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_pet, parent, false);

        TextView txtId = (TextView) rowView.findViewById(R.id.textId);
        TextView txtNamePet = (TextView) rowView.findViewById(R.id.textName);
        TextView txtPhone = (TextView) rowView.findViewById(R.id.textPhone);
        ImageView image = (ImageView)rowView.findViewById(R.id.idImage);

        //txtId.setText(String.format(" %d", listPet.get(pos).getId()));

        txtNamePet.setText(String.format(" %s", listPet.get(pos).getName()));
        txtPhone.setText(String.format(" %s",listPet.get(pos).getTelephone()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, activityPet.class);

                intent.putExtra("id", String.valueOf(listPet.get(pos).getId()));
                intent.putExtra("petName", listPet.get(pos).getName());
                intent.putExtra("phone",listPet.get(pos).getTelephone());
                context.startActivity(intent);
            }
        });

        return rowView;
    }

}
