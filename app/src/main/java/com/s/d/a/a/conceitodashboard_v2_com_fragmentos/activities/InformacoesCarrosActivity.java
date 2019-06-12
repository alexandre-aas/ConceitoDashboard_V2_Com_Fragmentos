package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos.InformacoesCarrosFragmento;

public class InformacoesCarrosActivity extends ConceitoDashboardActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacoes_carros_activity);
        // Não faz mais nada porque o FragmentDetalhesCarro faz tudo
        if(savedInstanceState == null) {
            // Somente insere a primeira vez
            // Porque a FragmentTransaction persiste durante a troca de orientação
            InformacoesCarrosFragmento fragDetalhes = new InformacoesCarrosFragmento();

            // Passa os parâmetros para o fragment, porque o bundle contém o carro
            // Por isso estamos utilizando uma FragmentTransaction, pois pelo XML não é possível passar os parâmetros
            Bundle args = getIntent().getExtras();
            fragDetalhes.setArguments(args);
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.add(R.id.layout, fragDetalhes);
            t.commit();
        }
    }

}
