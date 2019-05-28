package com.example.pgbarbershop;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pgbarbershop.adpters.AdapterCursosPersonalizado;
import com.example.pgbarbershop.adpters.AdapterMeusHorarios;
import com.example.pgbarbershop.adpters.AdapterProfissional;
import com.example.pgbarbershop.model.Agendamento;
import com.example.pgbarbershop.model.Filial;
import com.example.pgbarbershop.model.ProfissionaisModel;
import com.example.pgbarbershop.model.Servicos;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MeusHorarios extends Fragment implements View.OnClickListener {
    ListView listhorarios;
    List<Agendamento> lista = new ArrayList<>();
    private Activity activity;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meus_horarios, container, false);

        listhorarios = (ListView) v.findViewById(R.id.listhorario);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference().child("agendamento");

        ChildEventListener child;
        child = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Agendamento a = dataSnapshot.getValue(Agendamento.class);
                a.setId(dataSnapshot.getKey());
                lista.add(a);
                AdapterMeusHorarios adapter = new AdapterMeusHorarios(lista, getActivity());
                listhorarios.setAdapter(adapter);
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

        listhorarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String prof = (String) ((TextView)view.findViewById(R.id.horario)).getText();
                String key = (String) ((TextView)view.findViewById(R.id.chave)).getText();
                ref.child(key).removeValue();
                Toast.makeText(getActivity().getApplicationContext(), "Desmarcado", Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav,new MeusHorarios()).addToBackStack(null).commit();
            }
        });

        return v;
    }
    @Override
    public void onClick(View v) {}
}
