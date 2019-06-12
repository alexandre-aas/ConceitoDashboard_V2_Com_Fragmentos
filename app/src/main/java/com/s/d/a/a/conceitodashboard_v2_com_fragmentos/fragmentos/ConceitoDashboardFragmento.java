package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.s.d.a.a.androidutils.Utilitaria;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities.ListaCarrosActivity;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities.SobreActivity;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities.v11.ListaCarrosTabletActivity;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.dominio.Carro;


public class ConceitoDashboardFragmento extends FragmentosV4 implements View.OnClickListener {
    private Button btEsportivos;
    private Button btClassicos;
    private Button btLuxo;
    private Button btSobre;

    public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_conceito_dashboard, null);
        btEsportivos = view.findViewById(R.id.btEsportivos);
        btEsportivos.setOnClickListener(this);
        btClassicos = view.findViewById(R.id.btClassicos);
        btClassicos.setOnClickListener(this);
        btLuxo = view.findViewById(R.id.btLuxo);
        btLuxo.setOnClickListener(this);
        btSobre = view.findViewById(R.id.btSobre);
        btSobre.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        Context context = getActivity();
        boolean redeOk = Utilitaria.isNetworkAvailable(context);

        if (redeOk) {
            // Verifica se é tablet com Android 3.x ou superior
            boolean android3 = Utilitaria.isAndroid_3();
            // Cria uma Intent com telas diferentes para smartphone e tablet
            Intent intent = new Intent(context,android3 ? ListaCarrosTabletActivity.class : ListaCarrosActivity.class);
            if (v == btEsportivos) {
                intent.putExtra(Carro.TIPO, Carro.TIPO_ESPORTIVOS);
                startActivity(intent);
            } else if (v == btClassicos) {
                intent.putExtra(Carro.TIPO, Carro.TIPO_CLASSICO);
                startActivity(intent);
            } else if (v == btLuxo) {
                intent.putExtra(Carro.TIPO, Carro.TIPO_LUXO);
                startActivity(intent);
            } else if (v == btSobre) {
                // No tablet 3.x pode ser que o FragmentoSobre já esteja na tela
                View layoutFragSobre = getActivity().findViewById(R.id.layoutFragSobre);
                if (layoutFragSobre == null) {
                    startActivity(new Intent(context, SobreActivity.class));
                }
            }
        } else {
            Utilitaria.alertDialog(context,R.string.erro_conexao_indisponivel);
        }
    }

}
