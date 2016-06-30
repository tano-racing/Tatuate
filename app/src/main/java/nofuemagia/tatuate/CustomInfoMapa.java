package nofuemagia.tatuate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
        Locales usar = null;
        for (Locales local : mDataSet) {
            if (local.getId() == Integer.parseInt(marker.getSnippet())) {
                usar = local;
                break;
            }
        }

        TextView titulo = (TextView) view.findViewById(R.id.tv_titulo_info);
        titulo.setText(usar.getNombre());

        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
