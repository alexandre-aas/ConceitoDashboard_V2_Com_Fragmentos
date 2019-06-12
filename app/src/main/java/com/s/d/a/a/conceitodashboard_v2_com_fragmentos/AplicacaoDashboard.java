package com.s.d.a.a.conceitodashboard_v2_com_fragmentos;

import android.app.Application;
import android.util.Log;

import com.s.d.a.a.androidutils.DownloadImagemUtil;

public class AplicacaoDashboard extends Application {
    private static final String TAG = "AplicacaoDashboard";
    // Singleton
    private static AplicacaoDashboard instance = null;
    // Variáveis
    private DownloadImagemUtil downloader;

    public static AplicacaoDashboard getInstance() {
        if (instance == null)
            throw new IllegalStateException("Configure a aplicação no AndroidManifest.xml");
        return instance;
    }
    @Override
    public void onCreate() {
        Log.i(TAG, "ContextApplication.onCreate()");
        downloader = new DownloadImagemUtil(this);
        // Salva a instância para termos acesso como Singleton
        instance = this;
    }

    public DownloadImagemUtil getDownloadImagemUtil() {
        return downloader;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i(TAG, "ContextApplication.onTerminate()");
        downloader = null;
    }
}
