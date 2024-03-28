package com.example.optimumhealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MynAdapter extends ArrayAdapter<Employeen> {

    Context context;
    List<Employeen> arrayListEmployee;


    public MynAdapter(@NonNull Context context, List<Employeen> arrayListEmployee) {
        super(context, R.layout.customn_list_item,arrayListEmployee);

        this.context = context;
        this.arrayListEmployee = arrayListEmployee;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customn_list_item,null,true);

        TextView tvNurseID = view.findViewById(R.id.txt_nurseid);
        TextView tvNurseName = view.findViewById(R.id.txt_nursename);

        tvNurseID.setText(arrayListEmployee.get(position).getnurseId());
        tvNurseName.setText(arrayListEmployee.get(position).getNurseName());

        return view;
    }
}
