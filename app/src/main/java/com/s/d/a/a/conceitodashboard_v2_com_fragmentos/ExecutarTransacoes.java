package com.s.d.a.a.conceitodashboard_v2_com_fragmentos;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import com.s.d.a.a.androidutils.Transacao;
import com.s.d.a.a.androidutils.TransacaoTask;

import static com.s.d.a.a.androidutils.Utilitaria.alertDialog;
import static com.s.d.a.a.androidutils.Utilitaria.isNetworkAvailable;

public class ExecutarTransacoes extends FragmentActivity {
    private TransacaoTask task;
    protected void alert(int mensagem) {
        alertDialog(this, mensagem);
    }
    // Inicia a thread
    public void startTransacao(Transacao transacao) {
        boolean redeOk = isNetworkAvailable(this);
        if (redeOk) {
            // Inicia a transção
            task = new TransacaoTask(this, transacao,R.string.aguarde);
            task.execute();
        } else {
            // Não existe conexão
            alertDialog(this, R.string.erro_conexao_indisponivel);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(task != null) {
            boolean executando = task.getStatus().equals(AsyncTask.Status.RUNNING);
            if(executando) {
                task.cancel(true);
                task.fecharProgress();
            }
        }
    }


    //Primeira versão
    /** protected void alert(int mensagem){
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
    } */
}
