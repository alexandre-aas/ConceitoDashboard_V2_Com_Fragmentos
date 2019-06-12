package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.s.d.a.a.androidutils.DownloadImagemUtil;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.AplicacaoDashboard;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.dominio.Carro;

public class InformacoesCarrosFragmento extends ConceitoDashboardFragmento implements View.OnClickListener {
    private static final String TAG = "ConceitoDashboard_V2";
    private Carro carro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.informacoes_carros_fragmento, null);
        // Não queremos a barra do header aqui
        View layoutHeader = view.findViewById(R.id.layoutHeader);
        if(layoutHeader != null) {
            layoutHeader.setVisibility(View.GONE);
        }
        // Abrir o site
        Button btSite = view.findViewById(R.id.btAbrirSite);
        btSite.setOnClickListener(this);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            // Recebe o carro selecionado e atualiza os detalhes
            carro = (Carro) args.getSerializable(Carro.KEY);
            updateView();
        }
    }
    // Atualiza os detalhes do carro
    private void updateView() {
        View view = getView();
        Log.i(TAG, "Exibindo carro: " + carro.nome);
        TextView tHeader = view.findViewById(R.id.tHeader);
        TextView tDesc = view.findViewById(R.id.tDesc);
        if(tHeader != null) {
            tHeader.setText(carro.nome);
        }
        tDesc.setText(carro.desc);

        // Lê a imagem do cache
        final ImageView img = view.findViewById(R.id.img);
        AplicacaoDashboard application = (AplicacaoDashboard) getActivity().getApplication();
        DownloadImagemUtil downloader = application.getDownloadImagemUtil();
        Bitmap bitmap = downloader.getBitmap(carro.urlFoto);
        if(bitmap != null) {
            img.setImageBitmap(bitmap);
        }
    }
    @Override
    public void onClick(View view) {
        String url = carro.urlInfo;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
