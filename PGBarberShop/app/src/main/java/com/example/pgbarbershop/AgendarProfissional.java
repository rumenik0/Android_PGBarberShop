package com.example.pgbarbershop;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

public class AgendarProfissional extends Fragment implements View.OnClickListener {
    ListView listprof;
    List<ProfissionaisModel> lista = new ArrayList<>();
    private Activity activity;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final Bundle args = getArguments();

        View v = inflater.inflate(R.layout.fragment_agendar_profissional, container, false);
        TextView t = (TextView) v.findViewById(R.id.filial);
        t.setText(args.getString("filial"));
        TextView s = (TextView) v.findViewById(R.id.servico);
        s.setText(args.getString("servico"));
        listprof = (ListView) v.findViewById(R.id.listprof);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("profissional");

        ChildEventListener child;
        child = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ProfissionaisModel profissionaisModel = dataSnapshot.getValue(ProfissionaisModel.class);
                lista.add(profissionaisModel);
                AdapterProfissional adapter = new AdapterProfissional(lista, getActivity());
                listprof.setAdapter(adapter);
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

        listprof.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String prof = (String) ((TextView)view.findViewById(R.id.nome)).getText();

                Bundle bundle = new Bundle();
                bundle.putString("filial", args.getString("filial"));
                bundle.putString("servico",args.getString("servico"));
                bundle.putString("profissional",prof);
                AgendarHorario horario = new AgendarHorario();
                horario.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav,horario).addToBackStack("home").commit();


                //Toast.makeText(getActivity().getApplicationContext(), "teste: "+title, Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }
    @Override
    public void onClick(View v) {}
}
