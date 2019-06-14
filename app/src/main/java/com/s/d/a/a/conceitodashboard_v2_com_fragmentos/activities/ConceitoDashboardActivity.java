package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.s.d.a.a.androidutils.OrientacaoScreeen;
import com.s.d.a.a.androidutils.Transacao;
import com.s.d.a.a.androidutils.TransacaoTask;
import com.s.d.a.a.androidutils.Utilitaria;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;


public class ConceitoDashboardActivity extends FragmentActivity {
    private TransacaoTask task;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Fixa na horizontal somente se for um tablet
        if (Utilitaria.isAndroid_3_Tablet(this)) {
            OrientacaoScreeen.setOrientacaoHorizontal(this);
        }
        // Ativa a cor de fundo na ActionBar
        if(Utilitaria.isAndroid_3()) {
			//ActionBarUtil.setBackgroundImage(this);
        }
    }
    protected void alert(int mensagem) {
        Utilitaria.alertDialog(this, mensagem);
    }
    // Inicia a thread
    public void startTransacao(Transacao transacao) {
        boolean redeOk = Utilitaria.isNetworkAvailable(this);
        if (redeOk) {
            // Inicia a transção
            task = new TransacaoTask(this, transacao, R.string.aguarde);
            task.execute();
        } else {
            // Não existe conexão
            Utilitaria.alertDialog(this, R.string.erro_conexao_indisponivel);
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
}
