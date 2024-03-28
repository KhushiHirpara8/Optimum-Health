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

public class MydAdapter extends ArrayAdapter<Employeed> {

    Context context;
    List<Employeed> arrayListEmployee;


    public MydAdapter(@NonNull Context context, List<Employeed> arrayListEmployee) {
        super(context, R.layout.customd_list_item, arrayListEmployee);

        this.context = context;
        this.arrayListEmployee = arrayListEmployee;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customd_list_item, null, true);

        TextView tvDrID = view.findViewById(R.id.txt_drid);
        TextView tvDrName = view.findViewById(R.id.txt_drname);

        tvDrID.setText(arrayListEmployee.get(position).getDrId());
        tvDrName.setText(arrayListEmployee.get(position).getDrName());

        return view;
    }
}
