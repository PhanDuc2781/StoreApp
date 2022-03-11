package com.example.phanduc_store.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phanduc_store.Activity.HomeActivity;
import com.example.phanduc_store.Activity.LoginActivity;
import com.example.phanduc_store.R;

public class LogOutFragment extends Fragment {


    public LogOutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View  view = inflater.inflate(R.layout.fragment_log_out, container, false);
         showDialog();
         return view ;

    }

    private void showDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("Bạn có muốn đăng xuất không ?")
//set message
                .setMessage("Chọn!")
//set positive button
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked

                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        ((Activity) getActivity()).overridePendingTransition(0, 0);
                    }
                })
//set negative button
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                        ((Activity) getActivity()).overridePendingTransition(0, 0);
                    }
                })
                .show();
    }
}