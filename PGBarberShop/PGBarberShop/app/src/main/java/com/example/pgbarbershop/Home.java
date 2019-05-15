package com.example.pgbarbershop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends Fragment implements View.OnClickListener {
    private Activity activity;
    private ImageView myImg;


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView imgServ = (ImageView) v.findViewById(R.id.servii);
        ImageView imgLoca = (ImageView) v.findViewById(R.id.localizacao);
        ImageView imgAgen = (ImageView) v.findViewById(R.id.agenda);
        ImageView imgProf = (ImageView) v.findViewById(R.id.prof);

        imgServ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav,new Sevicos()).addToBackStack("home").commit();
                //Toast.makeText(getActivity().getApplicationContext(), "teste", Toast.LENGTH_SHORT).show();
            }
        });
        imgLoca.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String latitude = "-8.1322141";
                String longitude = "-34.938227";
                String label = "PG Barber";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(mapIntent);
            }
        });
        imgAgen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav,new Agendar()).addToBackStack("home").commit();
            }
        });
        imgProf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav,new Profissionais()).addToBackStack("home").commit();
            }
        });
        return v;
    }
   // public void clickBtnServ(View v){
     //   System.out.println("Passou aq");
       // FragmentManager fragmentManager = activity.getFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.nav,new Sevicos()).commit();
    //}

    @Override
    public void onClick(View v) {
        System.out.print("teste xserv");
        Log.d("click", "clickBtnServ: teste aq");
        if (v.getId() == R.id.servii) {
            System.out.print("teste xserv");
            Log.d("click", "clickBtnServ: teste aq");
        }
    }


}
