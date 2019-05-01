package com.s.d.a.a.conceitodashboard;

//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.s.d.a.a.androidutils.Transacao;
import com.s.d.a.a.servico.CarroService;

import java.util.List;

//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import com.s.d.a.a.androidutils.Utilitaria;
//import java.io.IOException;

public class ListaCarrosActivity extends ExecutarTransacoes implements AdapterView.OnItemClickListener, Transacao {
    private ListView listView;
    private List<Carro> carros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);

        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(this);

        startTransacao(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int posicao, long id){
        //Ao selecionar o carro, informações serão exibidas
        Carro c = (Carro) parent.getAdapter().getItem(posicao);
        Intent intent = new Intent(this, InformacoesCarros.class);
        intent.putExtra(Carro.KEY, c);
        startActivity(intent);



        //Carro c = (Carro) parent.getAdapter().getItem(posicao);
        //Toast.makeText(this, "Carro: " + c.nome, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void executar() throws Exception {
        //Busca carros em uma Thread
        this.carros = CarroService.getCarros(this, Carro.TIPO_ESPORTIVOS);
    }

    @Override
    public void atualizarView() {
        //Atualiza os carros na Thread principal
        if (carros != null){
            listView.setAdapter(new CarroAdapter(this, carros));
        }
    }

    /** boolean redeOk = Utilitaria.isNetworkAvailable(this);

     if (redeOk){
     String tipo = getIntent().getStringExtra("tipo");

     //Usando a classe AsyncTask
     new BuscaCarrosService(this, tipo).execute();

     } else{
     Utilitaria.alertDialog(this, R.string.erro_conexao_indisponivel);
     } */

    /** protected abstract class BuscaCarrosServiceInner extends AsyncTask<Void, Integer, List<Carro>>{
        private static  final String  TAG = "ListaCarros";
        private Context contexto;
        private ProgressDialog progresso;
        private String tipo;

        public BuscaCarrosServiceInner(Context contexto, String tipo){
            this.contexto = contexto;
            this.tipo     = tipo;
        }

        @Override
        //Antes de executar, exibe janela de progresso
        protected void onPreExecute(){
            progresso = ProgressDialog.show(contexto, contexto.getString(R.string.app_name),
                    contexto.getString(R.string.aguarde));
        }

        //Busca os carros em segundo plano dentro da thread
        protected List<Carro> doInBackgroud(Void... params){
            try {
                List<Carro> carros = CarroService.getCarros(contexto, tipo);
                return  carros;
            } catch (IOException e){
                Log.e(TAG, e.getMessage(), e);
                Utilitaria.alertDialog(contexto, R.string.erro_io);
            } finally {
                progresso.dismiss();
            }

            return null;
        }

        //Atualiza a view sincronizando com a thread principal
        protected void onPostExecute(List<Carro> carros){
            listView.setAdapter(new CarroAdapter(ListaCarrosActivity.this, carros));
        }
    } */

}

//Versão antiga da activity
/** public class ListaCarrosActivity extends AppCompatActivity implements Runnable{

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

        //Não usado
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
}*/
