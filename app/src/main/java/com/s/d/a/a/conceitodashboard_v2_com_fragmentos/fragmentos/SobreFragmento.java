package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.fragmentos;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;

import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;

import com.s.d.a.a.androidutils.Utilitaria;

public class SobreFragmento extends  ConceitoDashboardFragmento{
    private static final String URL_SOBRE = "https://developer.android.com/index.html";
    private WebView webview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sobre_fragmento, null);
        webview = (WebView) view.findViewById(R.id.webview);

        WebSettings settings = webview.getSettings();
        // Ativa o javascript na página
        settings.setJavaScriptEnabled(true);

        // Publica a interface para o javascript
        webview.addJavascriptInterface(this, "ConceitoDashboard_V2");

        // Configura o webview
        monitoraWebView(webview);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Abre a URL da página de sobre
        webview.loadUrl(URL_SOBRE);
    }

    private void monitoraWebView(WebView webview) {
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webview, String url, Bitmap favicon) {
                super.onPageStarted(webview, url, favicon);
                // Liga o progress
                ProgressBar progress = (ProgressBar) getView().findViewById(R.id.progress);
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webview, String url) {
                super.onPageFinished(webview, url);
                // Apaga o botão voltar
                if(Utilitaria.isAndroid_3_Tablet(getActivity())) {
                    // Somente apaga o voltar no tablet para demonstrar
                    apagaBotaoVoltar();
                }
                // Desliga o progress
                ProgressBar progress = (ProgressBar) getView().findViewById(R.id.progress);
                progress.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void apagaBotaoVoltar() {
        String jsApagar = "document.getElementById('voltar').style.display='none';";
        webview.loadUrl("javascript:" + jsApagar + ";");
    }

    // Método chamado pela página html no webview
    public void voltar() {
        // Então encerramos a activity para demonstrar
        getActivity().finish();
    }
}
