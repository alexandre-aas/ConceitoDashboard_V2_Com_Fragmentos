package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities.v11;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.Fragment;
import android.app.ActionBar.Tab;

import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities.ConceitoDashboardActivity;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.Main;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities.SobreActivity;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.dominio.Carro;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos.SobreFragmento;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos.v11.ListaCarrosTabletFragmento;


public class ListaCarrosTabletActivity extends ConceitoDashboardActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carros);

        // Liga a navegaçao por Tabs
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_header));
        actionBar.setDisplayHomeAsUpEnabled(true);

        String tipoParam = getIntent().getStringExtra(Carro.TIPO);

        // Carros - Clássicos
        ListaCarrosTabletFragmento fragClassicos = new ListaCarrosTabletFragmento();
        Bundle args = new Bundle();
        args.putString(Carro.TIPO, Carro.TIPO_CLASSICO);
        fragClassicos.setArguments(args);

        // Carros - Luxo
        ListaCarrosTabletFragmento fragLuxo = new ListaCarrosTabletFragmento();
        args = new Bundle();
        args.putString(Carro.TIPO, Carro.TIPO_LUXO);
        fragLuxo.setArguments(args);

        // Carros - Esportivos
        ListaCarrosTabletFragmento fragEsportivos = new ListaCarrosTabletFragmento();
        args = new Bundle();
        args.putString(Carro.TIPO, Carro.TIPO_ESPORTIVOS);
        fragEsportivos.setArguments(args);

        // Tab 1 - Clássicos
        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText(R.string.menu_classicos);
        tab1.setTabListener(new TabSelecionaCarroListener(fragClassicos));

        // Tab 2 - Luxo
        ActionBar.Tab tab2 = actionBar.newTab();
        tab2.setText(R.string.menu_luxo);
        tab2.setTabListener(new TabSelecionaCarroListener(fragLuxo));

        // Tab 3 - Esportivos
        ActionBar.Tab tab3 = actionBar.newTab();
        tab3.setText(R.string.menu_esportivos);
        tab3.setTabListener(new TabSelecionaCarroListener(fragEsportivos));

        // Adiciona as Tabs (verifica qual deve ser selecionada pelo flag no
        // segundo parâmetro)
        actionBar.addTab(tab1, Carro.TIPO_CLASSICO.equals(tipoParam));
        actionBar.addTab(tab2, Carro.TIPO_LUXO.equals(tipoParam));
        actionBar.addTab(tab3, Carro.TIPO_ESPORTIVOS.equals(tipoParam));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Volta para o Inicio
                Intent intent = new Intent(this, Main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.menu_sobre:
                View layoutDireita = findViewById(R.id.layoutDireita);
                if (layoutDireita != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    SobreFragmento sobre = new SobreFragmento();
                    ft.replace(R.id.layoutDireita, sobre);
                    ft.commit();
                } else {
                    startActivity(new Intent(this, SobreActivity.class));
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // Listener para trocar a lista de carros ao escolher uma Tab
    private class TabSelecionaCarroListener implements ActionBar.TabListener {
        private Fragment frag;

        public TabSelecionaCarroListener(Fragment frag) {
            this.frag = frag;
        }
        public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
            if (frag != null) {
                // Atualiza o fragment com a lista de carros na esquerda
                // Utiliza a compatibility library
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.replace(R.id.layoutEsquerda, frag, null);
                t.commit();
            }
        }
        public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
            if (frag != null) {
                // Remove utilizando a compatibility library
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.remove(frag);
                t.commit();
            }
        }
        public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
        }
    }
}
