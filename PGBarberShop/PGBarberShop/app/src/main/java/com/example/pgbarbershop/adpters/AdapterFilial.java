package com.example.pgbarbershop.adpters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pgbarbershop.R;
import com.example.pgbarbershop.model.Filial;
import com.example.pgbarbershop.model.Servicos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFilial extends BaseAdapter {

    private final List<Filial> cursos;
    private final Activity act;

    public AdapterFilial(List<Filial> cursos, Activity act) {
        this.cursos = cursos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return cursos.size();
    }

    @Override
    public Object getItem(int position) {
        return cursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.listafilial, parent, false);
        TextView nome = (TextView)view.findViewById(R.id.nome);
        Filial filial = cursos.get(position);
        nome.setText(filial.getNome());
        return view;
    }
}
