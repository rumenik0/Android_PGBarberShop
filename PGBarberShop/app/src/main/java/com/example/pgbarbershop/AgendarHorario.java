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
import android.widget.CalendarView;
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

public class AgendarHorario extends Fragment implements View.OnClickListener {
    private Activity activity;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final Bundle args = getArguments();

        View v = inflater.inflate(R.layout.fragment_agendar_horario, container, false);
        TextView t = (TextView) v.findViewById(R.id.filial);
        t.setText(args.getString("filial"));
        TextView s = (TextView) v.findViewById(R.id.servico);
        s.setText(args.getString("servico"));
        TextView p = (TextView) v.findViewById(R.id.profissional);
        p.setText(args.getString("profissional"));

        final CalendarView calendarView = (CalendarView) v.findViewById(R.id.calendarView1);

        // quando selecionado alguma data diferente da padrão
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {
                Bundle bundle = new Bundle();
                bundle.putString("filial", args.getString("filial"));
                bundle.putString("servico",args.getString("servico"));
                bundle.putString("profissional",args.getString("profissional"));
                bundle.putString("horario",dayOfMonth+"/"+month+"/"+year);
                AgendarFinal fim = new AgendarFinal();
                fim.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav,fim).addToBackStack("home").commit();

            }
        });
        return v;
    }
    @Override
    public void onClick(View v) {}
}
