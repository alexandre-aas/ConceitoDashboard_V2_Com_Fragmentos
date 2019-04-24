package com.s.d.a.a.conceitodashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.s.d.a.a.androidutils.Utilitaria;
import com.s.d.a.a.servico.BuscaCarrosService;
import com.s.d.a.a.servico.CarroService;

import java.io.IOException;
import java.util.List;


public class ListaCarrosActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "ConceitoDashboard";
    private String tipo;
    private ProgressDialog progresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);

        boolean redeOk = Utilitaria.isNetworkAvailable(this);

        if (redeOk){
            tipo = getIntent().getStringExtra("tipo");
            progresso = ProgressDialog.show(this, getString(R.string.app_name), getString(R.string.aguarde));

            //Thread threadListaCarros = new Thread(this, "BuscaCarros");
            //threadListaCarros.start();

            //Usando a classe AsyncTask
            new BuscaCarrosService(this, tipo).execute();

        } else{
            Utilitaria.alertDialog(this, R.string.erro_conexao_indisponivel);
        }

        /** try{
            tipo = getIntent().getStringExtra("tipo");
            List<Carro> carros = CarroService.getCarros(this, tipo);
            for(Carro c : carros){
                Log.i(TAG, "Carro: " + c.nome);
            }
        }catch (IOException e){
            Log.e(TAG, e.getMessage(), e);
        }*/

    }


    @Override
    public void run() {
        //Busca os carros em segundo plano através da thread criada no onCreate da activity
        try{
            final List<Carro> carros = CarroService.getCarros(this, tipo);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for(Carro c : carros){
                        Log.i(TAG, "Carro: " + c.nome);
                    }
                }
            });
        }catch (IOException e){
            Log.e(TAG, e.getMessage(), e);
            Utilitaria.alertDialog(this, R.string.erro_io);
        }
        finally {
            progresso.dismiss();
        }
    }
}
