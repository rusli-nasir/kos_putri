package com.kos_putri.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kos_putri.R;
import com.kos_putri.model.PenginapanModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PenginapanAdapter extends ArrayAdapter<PenginapanModel> {

    private Context context;
    private customButtonListener cButtonListener;
    private String img_url;

    public interface customButtonListener {
        public void onButtonClickListner(int position,int i, PenginapanModel data);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.cButtonListener = listener;
    }

    public PenginapanAdapter(Context context, int resource, List<PenginapanModel> items) {
        super(context, resource, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView txt_id_penginapan;
        TextView txt_nama_penginapan;
        TextView txt_alamat;
        TextView txt_lat;
        TextView txt_long;
        //TextView txt_harga;
        ImageView icon_gambar;
        ImageButton btn_dtl_penginapan;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        String image_path = null;
        final PenginapanModel rssItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) this.context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            Log.w("convertView","null");
            convertView = mInflater.inflate(R.layout.penginapan_list, null);
            holder = new ViewHolder();
            holder.txt_id_penginapan = (TextView) convertView.findViewById(R.id.txt_id_penginapan);
            holder.txt_nama_penginapan = (TextView) convertView.findViewById(R.id.txt_nama_penginapan);
            holder.txt_alamat = (TextView) convertView.findViewById(R.id.txt_alamat);
            holder.txt_lat = (TextView) convertView.findViewById(R.id.txt_lat);
            holder.txt_long = (TextView) convertView.findViewById(R.id.txt_long);
            holder.icon_gambar = (ImageView) convertView.findViewById(R.id.icon_penginapan);
            holder.btn_dtl_penginapan = (ImageButton) convertView.findViewById(R.id.btn_detil);
//
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();

            holder.txt_id_penginapan.setText(String.valueOf(rssItem != null ? rssItem.getId_penginapan() : 0));
            holder.txt_nama_penginapan.setText(rssItem != null ? rssItem.getNama() : null);
            holder.txt_alamat.setText(rssItem != null ? rssItem.getAlamat() : null);
            holder.txt_lat.setText(String.valueOf(rssItem != null ? rssItem.getLatitude() : 0));
            holder.txt_long.setText(String.valueOf(rssItem != null ? rssItem.getLongitude() : 0));

            image_path = rssItem != null ? rssItem.getGambar() : null;
            Log.d("img_path1", image_path);
            if(image_path != null){
                if (this.loadImageUrl(image_path) != null) {
                    holder.icon_gambar.setImageBitmap(this.loadImageUrl(image_path));
                }
            }
        }

        holder.btn_dtl_penginapan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (cButtonListener != null) {
                    int id_penginapan = rssItem != null ? rssItem.getId_penginapan() : 0;
                    cButtonListener.onButtonClickListner(position,id_penginapan,rssItem);
//                Toast.makeText(context, "Pesanan " + pengobatanModel.getNama_produk() + " ditambahkan",
//                	Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    private Bitmap loadImageUrl(String url){

        Bitmap bmp = null;
        URL img_url = null;
        try {
            img_url = new URL(url);
            bmp = BitmapFactory.decodeStream(img_url.openConnection().getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bmp;
    }
}
