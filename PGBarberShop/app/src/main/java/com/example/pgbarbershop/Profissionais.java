package com.example.pgbarbershop;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pgbarbershop.adpters.AdapterCursosPersonalizado;
import com.example.pgbarbershop.adpters.AdapterProfissional;
import com.example.pgbarbershop.model.ProfissionaisModel;
import com.example.pgbarbershop.model.Servicos;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Profissionais extends Fragment implements View.OnClickListener {
    ListView listserv;
    List<ProfissionaisModel> lista = new ArrayList<>();
    private Activity activity;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profissionais, container, false);
        listserv = (ListView) v.findViewById(R.id.listprof);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("profissional");

        ChildEventListener child;
        child = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ProfissionaisModel prof = dataSnapshot.getValue(ProfissionaisModel.class);
                lista.add(prof);
                AdapterProfissional adapter = new AdapterProfissional(lista, getActivity());
                listserv.setAdapter(adapter);
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
        ref.addChildEventListener(child);
        return v;
    }
    @Override
    public void onClick(View v) {}
}
