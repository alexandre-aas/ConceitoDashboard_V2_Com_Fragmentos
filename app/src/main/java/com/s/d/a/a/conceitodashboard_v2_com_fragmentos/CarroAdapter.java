package com.s.d.a.a.conceitodashboard_v2_com_fragmentos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.s.d.a.a.androidutils.DownloadImagemUtil;

import java.util.List;

public class CarroAdapter extends BaseAdapter {

    protected static final String TAG = "ConceitoDashboard";
    private LayoutInflater inflater;
    private final List<Carro> carros;
    private final Activity context;
    private DownloadImagemUtil downloader;

    public CarroAdapter(Activity context, List<Carro> carros) {
        this.context = context;
        this.carros = carros;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //downloader = new DownloadImagemUtil(context);

        // Recupera o objeto global da aplicação
        AplicacaoDashboard application = (AplicacaoDashboard) context.getApplication();
        // Utiliza este objeto para recuperar a classe que faz o download de imagens
        downloader = application.getDownloadImagemUtil();
    }

    @Override
    public int getCount() {
        return carros != null ? carros.size() : 0;
    }

    @Override
    public Object getItem(int posicao) {
        return carros != null ? carros.get(posicao) : null;
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup parent) {

        ViewHolder holder = null;

        if (view == null) {
            // Nao existe a View no cache para esta linha então cria um novo
            holder = new ViewHolder();
            // Busca o layout para cada carro com a foto
            int layout = R.layout.carro_item;
            view = inflater.inflate(layout, null);
            view.setTag(holder);
            holder.tNome = (TextView) view.findViewById(R.id.tNome);
            holder.tDesc = (TextView) view.findViewById(R.id.tDesc);
            holder.imgFoto = (ImageView) view.findViewById(R.id.img);
            holder.progress = (ProgressBar) view.findViewById(R.id.progress);
        } else {
            // Ja existe no cache, bingo entao pega!
            holder = (ViewHolder) view.getTag();
        }
        holder.imgFoto.setImageBitmap(null);
        Carro c = carros.get(posicao);
        // Agora que temos a view atualiza os valores
        holder.tNome.setText(c.nome);

        //Orientação Paisagem, mostra a descrição do veiculo na lista
        if (holder.tDesc != null){
            holder.tDesc.setText(c.desc);
        }

        downloader.download(context, c.urlFoto, holder.imgFoto, holder.progress);

        return view;
    }

    // Design Patter "ViewHolder" para Android
    static class ViewHolder {
        TextView tNome;
        TextView tDesc;
        ImageView imgFoto;
        ProgressBar progress;
    }
}
