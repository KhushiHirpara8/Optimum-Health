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

public class MyAdapter extends ArrayAdapter<Employee> {

    Context context;
    List<Employee> arrayListEmployee;


    public MyAdapter(@NonNull Context context, List<Employee> arrayListEmployee) {
        super(context, R.layout.custom_list_item,arrayListEmployee);

        this.context = context;
        this.arrayListEmployee = arrayListEmployee;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView tvBookID = view.findViewById(R.id.txt_bookid);
        TextView tvUserName = view.findViewById(R.id.txt_username);

        tvBookID.setText(arrayListEmployee.get(position).getbookId());
        tvUserName.setText(arrayListEmployee.get(position).getUserName());

        return view;
    }
}
