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

public class MyuAdapter extends ArrayAdapter<Employeeu> {

    Context context;
    List<Employeeu> arrayListEmployee;


    public MyuAdapter(@NonNull Context context, List<Employeeu> arrayListEmployee) {
        super(context, R.layout.customu_list_item,arrayListEmployee);

        this.context = context;
        this.arrayListEmployee = arrayListEmployee;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customu_list_item,null,true);

        TextView tvUserID = view.findViewById(R.id.txt_userid);
        TextView tvUserName = view.findViewById(R.id.txt_username);

        tvUserID.setText(arrayListEmployee.get(position).getbookId());
        tvUserName.setText(arrayListEmployee.get(position).getUserName());

        return view;
    }
}
