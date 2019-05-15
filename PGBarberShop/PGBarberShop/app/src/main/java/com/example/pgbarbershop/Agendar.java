package com.example.pgbarbershop;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pgbarbershop.adpters.AdapterFilial;
import com.example.pgbarbershop.model.Filial;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Agendar extends Fragment implements View.OnClickListener {
    private Activity activity;
    ListView listfilial;
    List<Filial> lista = new ArrayList<>();
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agendar, container, false);
        listfilial = (ListView) v.findViewById(R.id.listfilial);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference refFilial = db.getReference().child("filial");

        ChildEventListener child;
        child = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                System.out.println(dataSnapshot.getKey());
                Filial filial = dataSnapshot.getValue(Filial.class);
                System.out.println(filial.getNome());
                lista.add(filial);
                AdapterFilial adapter = new AdapterFilial(lista, getActivity());
                listfilial.setAdapter(adapter);
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        };
        refFilial.addChildEventListener(child);

        listfilial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ItemClicked item = parent.getItemAtPosition(position);
                Toast.makeText(getActivity().getApplicationContext(), "teste: "+parent.getItemAtPosition(0), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    @Override
    public void onClick(View v) {}
}
