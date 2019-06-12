package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos.ListaCarrosFragmento;

public class ListaCarrosActivity extends ConceitoDashboardActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carros);

        // Não faz mais nada porque o FragmentDetalhesCarro faz tudo
        if (savedInstanceState == null) {
            // Somente insere a primeira vez
            // Porque a FragmentTransaction persiste durante a troca de orientação
            ListaCarrosFragmento fragLista = new ListaCarrosFragmento();
            // Passa os parâmetros para o fragment, que contém o tipo do carro selecionado
            // Por isso estamos utilizando uma transação, pois pelo XML não é
            // possível passar os parâmetros
            Bundle args = getIntent().getExtras();
            fragLista.setArguments(args);
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.add(R.id.layoutEsquerda, fragLista);
            t.commit();
        }
    }

}