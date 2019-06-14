package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos.v11;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.s.d.a.a.androidutils.Utilitaria;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.dominio.Carro;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.dominio.CarroAdapter;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos.ListaCarrosFragmento;

import java.util.ArrayList;
import java.util.List;

public class ListaCarrosTabletFragmento extends ListaCarrosFragmento {
    private Menu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Informa que este Fragment deseja adicionar itens de menu na ActionBar
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        inflater.inflate(R.menu.menu_actionbar_fragmento_lista, menu);
        MenuItem item = menu.findItem(R.id.menu_busca);
        SearchView sv = new SearchView(getActivity());
        sv.setOnQueryTextListener(new FiltroListener());
        item.setActionView(sv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_atualizar:
                atualizarViewMenuAtualizar(true);
                startTransacao(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void atualizarViewMenuAtualizar(boolean atualizar) {
        if (menu != null) {
            final MenuItem item = menu.findItem(R.id.menu_atualizar);
            if (item != null) {
                if (atualizar) {
                    item.setActionView(R.layout.barra_e_progresso);
                } else {
                    item.setActionView(null);
                }
            }
        }
    }
    @Override
    public void atualizarView() {
        super.atualizarView();
        atualizarViewMenuAtualizar(false);
    }

    // Listener para filtrar os carros
    class FiltroListener implements OnQueryTextListener {
        @Override
        public boolean onQueryTextChange(String textoParcial) {
            Log.i("livroandroid", "onQueryTextChange: " + textoParcial);
            if ("".equals(textoParcial)) {
                // Se vazio volta a lista original
                atualizarView();
            }
            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String textoFinal) {
            Log.i("livroandroid", "onQueryTextSubmit: " + textoFinal);
            if (carros != null) {
                List<Carro> list = new ArrayList<Carro>();
                for (Carro carro : carros) {
                    boolean contains = carro.nome.toUpperCase().contains(textoFinal.toUpperCase());
                    if (contains) {
                        list.add(carro);
                    }
                }
                // Exibe no ListView um Adapter com apenas a lista que fez o
                // filtro
                listView.setAdapter(new CarroAdapter(getActivity(), list));
                Utilitaria.closeVirtualKeyboard(getActivity(), listView);
            }
            return false;
        }
    }
}
