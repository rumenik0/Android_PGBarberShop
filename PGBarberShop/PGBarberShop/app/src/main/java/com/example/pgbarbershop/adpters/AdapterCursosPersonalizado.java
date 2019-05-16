package com.example.pgbarbershop.adpters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pgbarbershop.R;
import com.example.pgbarbershop.model.ServicosModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCursosPersonalizado extends BaseAdapter {

    private final List<ServicosModel> cursos;
    private final Activity act;

    public AdapterCursosPersonalizado(List<ServicosModel> cursos, Activity act) {
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
        View view = act.getLayoutInflater().inflate(R.layout.listaserv, parent, false);

        TextView nome = (TextView)view.findViewById(R.id.nome);
        TextView descricao = (TextView)view.findViewById(R.id.descricao);
        ImageView imagem = (ImageView)view.findViewById(R.id.imagem);

        ServicosModel curso = cursos.get(position);
        nome.setText(curso.getNome());
        descricao.setText(curso.getDescricao());
        Picasso.get().load(curso.getUrlImage()).into(imagem);

        return view;
    }
}
