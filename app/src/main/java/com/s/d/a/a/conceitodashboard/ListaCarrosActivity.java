package com.s.d.a.a.conceitodashboard;

import java.io.IOException;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.s.d.a.a.servico.CarroService;



public class ListaCarrosActivity extends AppCompatActivity {
    private static final String TAG = "ConceitoDashboard";

    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);


        try{
            tipo = getIntent().getStringExtra("tipo");
            List<Carro> carros = CarroService.getCarros(this, tipo);
            for(Carro c : carros){
                Log.i(TAG, "Carro: " + c.nome);
            }
        }catch (IOException e){
            Log.e(TAG, e.getMessage(), e);
        }

    }




}
