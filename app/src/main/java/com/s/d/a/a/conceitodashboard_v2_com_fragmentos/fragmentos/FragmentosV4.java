package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos;

import android.support.v4.app.Fragment;

import com.s.d.a.a.androidutils.Transacao;
import com.s.d.a.a.androidutils.TransacaoFragmento;
import com.s.d.a.a.androidutils.Utilitaria;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;


public class FragmentosV4 extends Fragment {
    private int progressId = R.id.progress;
    protected void alert(int mensagem) {
        Utilitaria.alertDialog(getActivity(), mensagem);
    }

    // Inicia a thread
    public void startTransacao(Transacao transacao) {
        boolean redeOk = Utilitaria.isNetworkAvailable(getActivity());
        if (redeOk) {
            // Inicia a transção
            TransacaoFragmento task = new TransacaoFragmento(this,transacao, progressId);
            task.execute();
        } else {
            // Não existe conexão
            Utilitaria.alertDialog(getActivity(),R.string.erro_conexao_indisponivel);
        }
    }
    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }
}
