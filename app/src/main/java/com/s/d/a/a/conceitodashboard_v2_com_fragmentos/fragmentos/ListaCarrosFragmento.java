package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.s.d.a.a.androidutils.Transacao;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities.InformacoesCarrosActivity;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.dominio.Carro;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.dominio.CarroAdapter;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.dominio.ListaDeCarros;
import com.s.d.a.a.servico.CarroService;

import java.util.List;

public class ListaCarrosFragmento extends ConceitoDashboardFragmento implements OnItemClickListener, Transacao {
    private static final String TAG = "ConceitoDashboard_V2";
    protected ListView listView;
    protected List<Carro> carros;
    protected String tipo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_carros_fragmento, null);
        Bundle args = getArguments();
        if(args != null) {
            tipo = args.getString(Carro.TIPO);
        }
        if(tipo == null) {
            tipo = Carro.TIPO_LUXO;
        }
        listView = view.findViewById(R.id.listview);
        listView.setOnItemClickListener((android.widget.AdapterView.OnItemClickListener)this);

        // Configura o id do ProgressBar, para ser ligado/desligado ao abrir transação
        setProgressId(R.id.progressLista);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Recuperamos a lista de carros salva pelo onSaveInstanceState(bundle)
            ListaDeCarros lista = (ListaDeCarros) savedInstanceState.getSerializable(ListaDeCarros.KEY);
            Log.i(TAG,"Lendo estado: savedInstanceState(carros)");
            this.carros = lista.carros;
        }
        // Se a lista de carros existe, atualiza a lista, senão dispara a busca
        if (carros != null) {
            atualizarView();
        } else {
            startTransacao((com.s.d.a.a.androidutils.Transacao)this);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"Salvando Estado: onSaveInstanceState(bundle)");
        // Salvar o estado da tela
        outState.putSerializable(ListaDeCarros.KEY, new ListaDeCarros(carros));
    }
    @Override
    public void executar() throws Exception {
        // Busca os carros em uma thread
        this.carros = CarroService.getCarros(getActivity(), tipo);
    }
    @Override
    public void atualizarView() {
        // Atualiza os carros na thread principal
        if (carros != null && !carros.isEmpty() && getActivity() != null) {
            // Atualiza o ListView
            listView.setAdapter(new CarroAdapter(getActivity(), carros));
            // Recupera o primeiro carro da lista, para exibir os detalhes
            Carro c = carros.get(0);
            visualizarDetalhes(c, false);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
        Carro c = (Carro) parent.getAdapter().getItem(posicao);
        visualizarDetalhes(c, true);
    }
    // Exibe os detalhes do carro
    private void visualizarDetalhes(Carro carro, boolean exibirDetalhes) {
        InformacoesCarrosFragmento fragDetalhes = new InformacoesCarrosFragmento();
        // Passa o carro selecionado por parâmetro
        Bundle bundle = new Bundle();
        bundle.putSerializable(Carro.KEY, carro);
        fragDetalhes.setArguments(bundle);
        View layoutDireita = getActivity().findViewById(R.id.layoutDireita);
        if(layoutDireita != null) {
            // Atualiza o fragment no layout da direita
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.layoutDireita, fragDetalhes);
            ft.commit();
        } else {
            // Senão inicia uma nova activity
            if(exibirDetalhes) {
                // Somente navega para próxima tela se algum carro foi selecionado
                // Não em caso da primeira requisição
                Intent intent = new Intent(getActivity(), InformacoesCarrosActivity.class);
                intent.putExtra(Carro.KEY, carro);
                startActivity(intent);
            }
        }
    }
}
