package com.s.d.a.a.conceitodashboard_v2_com_fragmentos;

//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.s.d.a.a.androidutils.Transacao;
import com.s.d.a.a.androidutils.DownloadImagemUtil;
import com.s.d.a.a.servico.CarroService;

import java.util.List;

//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import com.s.d.a.a.androidutils.Utilitaria;
//import java.io.IOException;

public class ListaCarrosActivity extends ExecutarTransacoes implements OnItemClickListener, Transacao {
    private static final String TAG = "ConceitoDashboard";
    private ListView listView;
    private List<Carro> carros;
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);


        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(this);

        //armazena tipo do veiculo selecionado
        tipo = getIntent().getStringExtra("tipo");

        //Recupera a lsita de carros salva
        carros = (List<Carro>) getLastNonConfigurationInstance();

        Log.i(TAG,"Lendo estado: getLastNonConfigurationInstance()");
        if (carros == null && savedInstanceState != null) {
            // Recupera a lista de carros salva pelo onSaveInstanceState(bundle)
            ListaDeCarros lista = (ListaDeCarros) savedInstanceState.getSerializable(ListaDeCarros.KEY);
            Log.i(TAG,"Lendo estado: savedInstanceState(carros)");
            this.carros = lista.carros;
        }

        if (carros != null) {
            // Atualiza o ListView diretamente
            listView.setAdapter(new CarroAdapter(this, carros));
        } else {
            startTransacao(this);
        }

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"Salvando Estado: onSaveInstanceState(bundle)");
        // Salva o estado da tela
        outState.putSerializable(ListaDeCarros.KEY, new ListaDeCarros(carros));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int posicao, long id){
        Carro c = (Carro) parent.getAdapter().getItem(posicao);
        View layoutDetahes = findViewById(R.id.layoutDetalhes);
        if(layoutDetahes != null) {
            // Se horizontal, atualiza os detalhes na própria tela
            atualizarDetalhes(c);
        } else {
            // Se vertical, abre outra tela de detalhes
            Intent intent = new Intent(this, InformacoesCarros.class);
            intent.putExtra(Carro.KEY, c);
            startActivity(intent);
        }



        //2ª Versão - Ao selecionar o carro, informações serão exibidas
        /**Carro c = (Carro) parent.getAdapter().getItem(posicao);
        Intent intent = new Intent(this, InformacoesCarros.class);
        intent.putExtra(Carro.KEY, c);
        startActivity(intent);*/


        //1ª Versão
        //Carro c = (Carro) parent.getAdapter().getItem(posicao);
        //Toast.makeText(this, "Carro: " + c.nome, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void executar() throws Exception {
        //Busca carros em uma Thread
        //this.carros = CarroService.getCarros(this, Carro.TIPO_ESPORTIVOS);
        this.carros = CarroService.getCarros(this, tipo);
    }

    @Override
    public void atualizarView() {
        // Atualiza os carros na thread principal
        if (carros != null && !carros.isEmpty()) {
            listView.setAdapter(new CarroAdapter(this, carros));
            Carro c = carros.get(0);
            atualizarDetalhes(c);
        }

        /** 1ª Versão - Atualiza os carros na Thread principal
        if (carros != null){
            listView.setAdapter(new CarroAdapter(this, carros));
        }*/
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        Log.i(TAG, "Salvando Estado: onRetainNonConfigurationInstance()");
        return carros;
    }

    // Detalhes do carro
    private void atualizarDetalhes (Carro carro) {
        View layoutDetalhes = findViewById(R.id.layoutDetalhes);
        if(layoutDetalhes != null) {
            Log.i(TAG, "Exibindo carro: " + carro.nome);
            TextView tHeader = (TextView) findViewById(R.id.tHeader);
            TextView tDescDetalhes = (TextView) findViewById(R.id.tDescDetalhes);
            if(tDescDetalhes != null) {
                tDescDetalhes.setText(carro.desc);
            }
            if(tHeader != null) {
                tHeader.setText(carro.nome);
            }

            // Lê a imagem do cache
            ImageView img = findViewById(R.id.imgDetalhes);
            AplicacaoDashboard application = (AplicacaoDashboard) getApplication();
            DownloadImagemUtil downloader = application.getDownloadImagemUtil();
            Bitmap bitmap = downloader.getBitmap(carro.urlFoto);
            if(img != null && bitmap != null) {
                img.setImageBitmap(bitmap);
            }
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
