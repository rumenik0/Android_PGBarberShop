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
import com.example.pgbarbershop.model.ProfissionaisModel;
import com.example.pgbarbershop.model.Servicos;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProfissional extends BaseAdapter {

    private final List<ProfissionaisModel> cursos;
    private final Activity act;

    public AdapterProfissional(List<ProfissionaisModel> cursos, Activity act) {
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
        final View view = act.getLayoutInflater().inflate(R.layout.listaprofissional, parent, false);

        TextView nome = (TextView)view.findViewById(R.id.nome);
        final ImageView imagem = (ImageView)view.findViewById(R.id.imagem);

        ProfissionaisModel curso = cursos.get(position);
        nome.setText(curso.getNome());
        Picasso.get().load(curso.getUrlImage()).into(imagem, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap imageBitmap = ((BitmapDrawable) imagem.getDrawable()).getBitmap();

                //RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(view.getResources(), imageBitmap);
                //imageDrawable.setCircular(true);
                //imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                //imageDrawable.setFilterBitmap(true);
                //imageDrawable.setAntiAlias(true);
                imagem.setImageDrawable(createRoundedBitmapDrawableWithBorder(imageBitmap));
            }

            @Override
            public void onError(Exception e) {

            }
        });
        return view;
    }

    private RoundedBitmapDrawable createRoundedBitmapDrawableWithBorder(Bitmap bitmap){
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int borderWidthHalf = 10;
        int bitmapRadius = Math.min(bitmapWidth,bitmapHeight)/2;
        int bitmapSquareWidth = Math.min(bitmapWidth,bitmapHeight);
        int newBitmapSquareWidth = bitmapSquareWidth+borderWidthHalf;
        Bitmap roundedBitmap = Bitmap.createBitmap(newBitmapSquareWidth,newBitmapSquareWidth,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundedBitmap);
        canvas.drawColor(Color.RED);
        int x = borderWidthHalf + bitmapSquareWidth - bitmapWidth;
        int y = borderWidthHalf + bitmapSquareWidth - bitmapHeight;
        canvas.drawBitmap(bitmap, x, y, null);
        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidthHalf*2);
        borderPaint.setColor(Color.WHITE);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getWidth()/2, newBitmapSquareWidth/2, borderPaint);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(),roundedBitmap);
        roundedBitmapDrawable.setCornerRadius(bitmapRadius);
        roundedBitmapDrawable.setAntiAlias(true);

        return roundedBitmapDrawable;
    }
}
