package nofuemagia.tatuate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import nofuemagia.tatuate.model.Locales;

/**
 * Created by jlionti on 30/06/2016. No Fue Magia
 */
public class CustomInfoMapa implements GoogleMap.InfoWindowAdapter {

    private final Context mContext;
    private final View view;
    private final List<Locales> mDataSet;

    public CustomInfoMapa(Context context, List<Locales> dataset) {
        mContext = context;
        mDataSet = dataset;
        view = LayoutInflater.from(mContext).inflate(R.layout.custom_info_mapa, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        System.out.println(marker.getSnippet());
        Locales local = mDataSet.get(Integer.parseInt(marker.getSnippet()));

        TextView titulo = (TextView) view.findViewById(R.id.tv_titulo_info);
        titulo.setText(local.getNombre());

        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
