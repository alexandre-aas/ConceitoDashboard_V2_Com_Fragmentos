package com.s.d.a.a.conceitodashboard;

import android.app.Activity;

import com.s.d.a.a.androidutils.Transacao;
import com.s.d.a.a.androidutils.TransacaoTask;

import static com.s.d.a.a.androidutils.Utilitaria.alertDialog;
import static com.s.d.a.a.androidutils.Utilitaria.isNetworkAvailable;

public class ExecutarTransacoes extends Activity {
    protected void alert(int mensagem){
        alertDialog(this, mensagem);

    }
    // Inicia a thread
    public void startTransacao(Transacao transacao) {
        boolean redeOk = isNetworkAvailable(this);
        if(redeOk) {
            // Inicia a transção
            TransacaoTask task = new TransacaoTask(this, transacao , R.string.aguarde);
            task.execute();
        } else {
            // Não existe conexão
            alertDialog(this, R.string.erro_conexao_indisponivel);
        }
    }
}
