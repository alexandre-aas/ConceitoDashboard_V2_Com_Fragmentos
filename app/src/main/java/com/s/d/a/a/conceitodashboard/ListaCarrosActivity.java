package com.s.d.a.a.conceitodashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListaCarrosActivity extends AppCompatActivity {

    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);

        tipo = getIntent().getStringExtra("tipo");

    }
}
