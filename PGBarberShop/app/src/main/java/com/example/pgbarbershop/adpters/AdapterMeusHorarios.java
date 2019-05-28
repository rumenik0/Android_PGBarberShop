package com.example.pgbarbershop.adpters;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pgbarbershop.R;
import com.example.pgbarbershop.model.Agendamento;
import com.example.pgbarbershop.model.ProfissionaisModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMeusHorarios extends BaseAdapter {

    private final List<Agendamento> cursos;
    private final Activity act;

    public AdapterMeusHorarios(List<Agendamento> cursos, Activity act) {
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
        final View view = act.getLayoutInflater().inflate(R.layout.listameushorarios, parent, false);

        TextView nome = (TextView)view.findViewById(R.id.horario);
        TextView key = (TextView)view.findViewById(R.id.chave);

        Agendamento curso = cursos.get(position);
        nome.setText(curso.getData());
        key.setText(curso.getId());
        return view;
    }

}
