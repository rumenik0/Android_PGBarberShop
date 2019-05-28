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
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pgbarbershop.adpters.AdapterCursosPersonalizado;
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

public class AgendarFinal extends Fragment implements View.OnClickListener {
    private Activity activity;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final Bundle args = getArguments();

        View v = inflater.inflate(R.layout.fragment_agendar_final, container, false);
        TextView t = (TextView) v.findViewById(R.id.filial);
        t.setText(args.getString("filial"));
        TextView s = (TextView) v.findViewById(R.id.servico);
        s.setText(args.getString("servico"));
        TextView p = (TextView) v.findViewById(R.id.profissional);
        p.setText(args.getString("profissional"));
        TextView h = (TextView) v.findViewById(R.id.horario);
        h.setText(args.getString("horario"));

        Button b = (Button) v.findViewById(R.id.agendar);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Filial filial = new Filial();
                filial.setNome(args.getString("filial"));

                Servicos servicos = new Servicos();
                servicos.setNome(args.getString("servico"));

                ProfissionaisModel profissional = new ProfissionaisModel();
                profissional.setNome(args.getString("profissional"));

                Agendamento a = new Agendamento();
                a.setFilial(filial);
                a.setServicos(servicos);
                a.setProfissional(profissional);
                a.setData(args.getString("horario"));

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference ref = db.getReference().child("agendamento");

                ref.push().setValue(a);

                Toast.makeText(getActivity().getApplicationContext(), "Agendado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });




        return v;
    }
    @Override
    public void onClick(View v) {}
}
