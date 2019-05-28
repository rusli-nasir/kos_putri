package com.kos_putri;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kos_putri.model.PenginapanModel;
import com.squareup.picasso.Picasso;

public class Detail_penginapan extends AppCompatActivity {

    public Bundle bundle;
    public PenginapanModel penginapanModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penginapan);

        Intent intent = getIntent();
        bundle = intent.getExtras();
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ImageView imgProfile = (ImageView)findViewById(R.id.imgProfil);
        TextView tvNama = (TextView) findViewById(R.id.tvNama);
        TextView tvFasilitas = (TextView) findViewById(R.id.tvFasilitas);
        TextView tvHarga = (TextView) findViewById(R.id.tvHarga);
        TextView tvStatus = (TextView) findViewById(R.id.tvStatus);
        TextView tvAlamat = (TextView) findViewById(R.id.tvAlamat);
        TextView tvTlp = (TextView) findViewById(R.id.tvTlp);
        Button btnRute = (Button) findViewById(R.id.btnRute);

//        bundle = this.getIntent().getExtras();
//            penginapanModel.setId_penginapan(bundle.getInt("id_penginapan"));
//            penginapanModel.setId_kategori_penginapan(bundle.getInt("id_kategori_penginapan"));
//            penginapanModel.setNama_kategori(bundle.getString("nama_kategori"));
//            penginapanModel.setNama(bundle.getString("nama"));
//            penginapanModel.setFasilitas(bundle.getString("fasilitas"));
//            penginapanModel.setHarga(bundle.getString("harga"));
//            penginapanModel.setStatus(bundle.getString("status"));
//            penginapanModel.setAlamat(bundle.getString("alamat"));
//            penginapanModel.setLatitude(bundle.getDouble("latitude"));
//            penginapanModel.setLongitude(bundle.getDouble("longitude"));
//            penginapanModel.setTelepon(bundle.getString("telepon"));
//            penginapanModel.setGambar(bundle.getString("gambar"));

//        Log.d("IdPenginapan", getIntent().getDataString());

//        penginapanModel.setId_penginapan(getIntent().getIntExtra("id_penginapan",0));
//        penginapanModel.setId_kategori_penginapan(getIntent().getIntExtra("id_kategori_penginapan",0));
//        penginapanModel.setNama_kategori(getIntent().getStringExtra("nama_kategori"));
//        penginapanModel.setNama(getIntent().getStringExtra("nama"));
//        penginapanModel.setFasilitas(getIntent().getStringExtra("fasilitas"));
//        penginapanModel.setHarga(getIntent().getStringExtra("harga"));
//        penginapanModel.setStatus(getIntent().getStringExtra("status"));
//        penginapanModel.setAlamat(getIntent().getStringExtra("alamat"));
//        penginapanModel.setLatitude(getIntent().getDoubleExtra("latitude",0));
//        penginapanModel.setLongitude(getIntent().getDoubleExtra("longitude",0));
//        penginapanModel.setTelepon(getIntent().getStringExtra("telepon"));
//        penginapanModel.setGambar(getIntent().getStringExtra("gambar"));
        setTitle(String.format("Penginapan %s", getIntent().getStringExtra("nama")));
        tvNama.setText(getIntent().getStringExtra("nama"));
        tvFasilitas.setText(getIntent().getStringExtra("fasilitas"));
        tvHarga.setText(getIntent().getStringExtra("harga"));
        tvStatus.setText(getIntent().getStringExtra("status"));
        tvAlamat.setText(getIntent().getStringExtra("alamat"));
        tvTlp.setText(getIntent().getStringExtra("telepon"));

        Picasso.get().load(getIntent().getStringExtra("gambar")).into(imgProfile);

        btnRute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Detail_penginapan.this,MapRute.class);
                Bundle bundle = new Bundle();

                bundle.putInt("id_penginapan", getIntent().getIntExtra("id_penginapan",0));
                bundle.putString("nama", getIntent().getStringExtra("nama"));
                bundle.putDouble("latitude", getIntent().getDoubleExtra("latitude",0));
                bundle.putDouble("longitude", getIntent().getDoubleExtra("longitude",0));
                newIntent.putExtras(bundle);
                startActivityForResult(newIntent, 0);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
