package com.s.d.a.a.conceitodashboard_v2_com_fragmentos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.s.d.a.a.androidutils.DownloadImagemUtil;

public class InformacoesCarros extends FragmentActivity implements OnClickListener {
    private static final String TAG = "ConceitoDashboard";
    private Carro carro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_carros);
        carro = (Carro) getIntent().getSerializableExtra(Carro.KEY);
        Log.i(TAG, "Exibindo carro: " + carro.nome);
        TextView tTitulo = (TextView) findViewById(R.id.tHeader);
        TextView tDesc = (TextView) findViewById(R.id.tDesc);
        tTitulo.setText(carro.nome);

        tDesc.setText(carro.desc);
        // Lê a imagem do cache
        // DownloadImagemUtil possui um HashMap interno. Chave=URL
        ImageView img = (ImageView) findViewById(R.id.img);
        DownloadImagemUtil downloader = new DownloadImagemUtil(this);
        Bitmap bitmap = downloader.getBitmap(carro.urlFoto);
        if(bitmap != null) {
            img.setImageBitmap(bitmap);
        }
        // Site
        Button btSite = (Button) findViewById(R.id.btAbrirSite);
        btSite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String url = carro.urlInfo;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
