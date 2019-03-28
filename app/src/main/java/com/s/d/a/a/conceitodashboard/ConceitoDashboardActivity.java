package com.s.d.a.a.conceitodashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class ConceitoDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClassicos;
    private Button btnLuxo;
    private Button btnEsportivos;
    private Button btnSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conceito_dashboard);

        btnClassicos = findViewById(R.id.btnClassicos);
        btnClassicos.setOnClickListener(this);

        btnLuxo = findViewById(R.id.btnLuxo);
        btnLuxo.setOnClickListener(this);

        btnEsportivos = findViewById(R.id.btnEsportivos);
        btnEsportivos.setOnClickListener(this);

        btnSobre = findViewById(R.id.btnSobre);
        btnSobre.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        /** boolean coneccaoInterntOk = AndroidUtils.isNetworkAvailable(this);

        if(coneccaoInterntOk) {
            Intent intent = new Intent(this, ListaCarrosActivity.class);
            if(view == btnEsportivos) {
                intent.putExtra(Carro.TIPO, Carro.TIPO_ESPORTIVOS);
                startActivity(intent);
            } else if(view == btnClassicos) {
                intent.putExtra(Carro.TIPO, Carro.TIPO_CLASSICO);
                startActivity(intent);
            } else if(view == btnLuxo) {
                intent.putExtra(Carro.TIPO, Carro.TIPO_LUXO);
                startActivity(intent);
            } else if(view == btnSobre) {
                startActivity(new Intent(this, SobreActivity.class));
            }
        } else {
            AndroidUtils.alertDialog(this, R.string.erro_conexao_indisponivel);
        }*/
    }
}
