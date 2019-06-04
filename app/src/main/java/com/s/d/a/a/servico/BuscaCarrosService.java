package com.s.d.a.a.servico;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.s.d.a.a.androidutils.R;
import com.s.d.a.a.androidutils.Utilitaria;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.Carro;

import java.io.IOException;
import java.util.List;

public class BuscaCarrosService extends AsyncTask<Void, Void, List<Carro>> {

    private static final String TAG = "ConceitoDashboard";
    private Context context;
    private ProgressDialog progresso;
    private  String tipo = "";

    public BuscaCarrosService(Context context, String tipo) {
        this.context = context;
        this.tipo = tipo;
    }

    @Override
    // Antes de executar, vamos exibir uma janela de progresso
    protected void onPreExecute() {
        progresso = ProgressDialog.show(context, context.getString(R.string.app_name), "Aguarde...");
    }

    @Override
    // Busca os carros em segundo plano dentro da thread
    protected List<Carro> doInBackground(Void... voids) {
        try {
            List<Carro> carros = CarroService.getCarros(context, tipo);
            return carros;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            Utilitaria.alertDialog(context, "Erro de I/O...");
        } finally {
            progresso.dismiss();
        }
        return null;
    }

    // Atualiza a view dentro sincronizado com a thread e interface
    protected void onPostExecute(List<Carro> carros) {
        for (Carro c : carros) {
            Log.i(TAG, "Carro: " + c.nome);
        }
    }
}
