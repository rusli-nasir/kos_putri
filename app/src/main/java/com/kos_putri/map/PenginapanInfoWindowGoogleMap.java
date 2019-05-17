package com.kos_putri.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.kos_putri.R;
import com.kos_putri.model.PenginapanModel;
import com.kos_putri.util.LbsLink;

public class PenginapanInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public PenginapanInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }



    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.custom_info_contents_penginapan, null);

        TextView txtNamaPenginapan = view.findViewById(R.id.txt_nama_penginapan);
        TextView txtAlamat = view.findViewById(R.id.txt_alamat);
        ImageView pic = view.findViewById(R.id.pic);

        TextView txtKatPenginapan = view.findViewById(R.id.tvTxtKatPenginapan);
        TextView txtFasilitas = view.findViewById(R.id.tvFasilitas);
        TextView txtHarga = view.findViewById(R.id.tvHarga);
        TextView txtStatus = view.findViewById(R.id.tvStatus);
        TextView txtNoTelp = view.findViewById(R.id.tvTlp);

        txtNamaPenginapan.setText(String.format("Penginapan %s", marker.getTitle()));
        txtAlamat.setText(marker.getSnippet());

        PenginapanModel penginapanModel = (PenginapanModel) marker.getTag();
        if(penginapanModel != null){
            LbsLink lbsLink = new LbsLink(context,penginapanModel.getGambar());
            txtKatPenginapan.setText(penginapanModel.getNama_kategori());
            txtFasilitas.setText(penginapanModel.getFasilitas());
            txtHarga.setText(penginapanModel.getHarga());
            txtStatus.setText(penginapanModel.getStatus());
            txtNoTelp.setText(penginapanModel.getTelepon());
            if(penginapanModel.getGambar() != null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                options.inSampleSize = LbsLink.calculateInSampleSize(options, 500,500);

                Bitmap img = lbsLink.loadImageUrl(penginapanModel.getGambar(),options);
                pic.setImageBitmap(img);
            }
        }
        return view;
    }
}
