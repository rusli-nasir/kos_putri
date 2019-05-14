package com.kos_putri;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.kos_putri.adapter.PenginapanAdapter;
import com.kos_putri.adapter.PenginapanAdapter.customButtonListener;
import com.kos_putri.model.PenginapanModel;
import com.kos_putri.util.JsonParseVolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DaftarPenginapan extends AppCompatActivity implements customButtonListener{

    public Bundle bundle;
    public String link_url = null;
    List<PenginapanModel> penginapanModels;
    JSONArray arr_penginapan;
    ListView listPenginapan;
    PenginapanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_penginapan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = this.getIntent().getExtras();
        Log.d("bundle", bundle != null ? bundle.getString("keyIdKatPenginapan") : "0");
        String param1 = bundle != null ? bundle.getString("keyIdKatPenginapan") : null;

        link_url = getString(R.string.server_url) + "penginapan.php?idkatpenginapan="+param1;
        Log.d("link_url",link_url);
        penginapanModels = new ArrayList<PenginapanModel>();
    }

    private void loadData(JSONObject jsonParams){
        JsonParseVolley jsonParse = new JsonParseVolley(this.getApplicationContext());
        JSONObject jsonObject;

        jsonObject = jsonParse.getJsonURL(link_url);
        int id_penginapan, id_kategori_penginapan, id_icon;
        double latitude,longitude;
        String nama_kategori,icon,nama,fasilitas,harga,status,alamat,telepon,gambar;
        if(penginapanModels != null){
            penginapanModels.clear();
        }

        try {
            arr_penginapan = jsonObject.getJSONArray("penginapan");
            for(int i=0;i<arr_penginapan.length();i++){
                JSONObject ar = arr_penginapan.getJSONObject(i);
                id_penginapan = ar.getInt("id_penginapan");
                id_kategori_penginapan = ar.getInt("id_kategori_penginapan");
                nama_kategori = ar.getString("nama_kategori");
                id_icon = 0;
                icon = null;
                nama = ar.getString("nama");
                fasilitas = ar.getString("fasilitas");
                harga = ar.getString("harga");
                status = ar.getString("status");
                alamat = ar.getString("alamat");
                latitude = ar.getDouble("latitude");
                longitude = ar.getDouble("longitude");
                telepon = ar.getString("telepon");

                gambar = String.format("%s%s", getString(R.string.image_server_url), URLEncoder.encode(ar.getString("gambar")));

                PenginapanModel item = new PenginapanModel(id_penginapan, id_kategori_penginapan, nama_kategori,
                        id_icon, icon, nama, fasilitas, harga, status, alamat, latitude,
                        longitude, telepon, gambar);

                penginapanModels.add(item);

            }
        }catch(JSONException e){
            e.printStackTrace();
        }

        listPenginapan = (ListView)findViewById(R.id.listDP);
        listPenginapan.setAdapter(null);
        adapter = new PenginapanAdapter(this, R.layout.penginapan_list, penginapanModels);
        adapter.setCustomButtonListner(this);
        listPenginapan.setAdapter(adapter);
    }

    @Override
    public void onButtonClickListner(int position, int i, PenginapanModel data) {
        Intent intent = null;
        //Bundle bundle = new Bundle();
        String jenis = bundle.getString("jenis");

        try{

            bundle.clear();
//			if(jenis.equals("jadwal")){
//				intent = new Intent(this, JadwalActivity.class);
//				intent = new Intent(this, Detail_penginapan.class);
//				bundle.putString("keyNamaPenginapan", data.getNama());
            if(jenis.equals("dp")){
                intent = new Intent(this, Detail_penginapan.class);
                bundle.putInt("id_penginapan", data.getId_penginapan());
                bundle.putString("keyNamaPenginapan", data.getNama());
                bundle.putString("keyFasilitas", data.getFasilitas());
                bundle.putString("keyHarga", data.getHarga());
                bundle.putString("keyStatus", data.getStatus());
                bundle.putString("keygambar", data.getGambar());
                bundle.putString("keyAlamat", data.getAlamat());
                bundle.putString("keyTelepon", data.getTelepon());
//				bundle.putString("keyIdIcon", data.getIcon());
                bundle.putDouble("keyLat", data.getLatitude());
                bundle.putDouble("keyLong", data.getLongitude());
            }
        }catch(Exception e){
            Log.d("error", e.getMessage());
            e.printStackTrace();
        }
        Log.d("bundle", bundle.toString());

        //bundle.putString("keyKota", param1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
