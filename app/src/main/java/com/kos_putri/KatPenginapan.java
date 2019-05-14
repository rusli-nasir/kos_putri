package com.kos_putri;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kos_putri.model.RSSFeed;
import com.kos_putri.model.RSSItem;
import com.kos_putri.util.JsonParseVolley;
import com.kos_putri.util.LbsLink;
import com.kos_putri.util.XMLServerLoader;

import org.json.JSONObject;

public class KatPenginapan extends ListActivity {

    public LbsLink linkurl;
    String SERVER_URL;
    private RSSFeed myRssFeed = null;
    private ProgressDialog pDialog;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kat_penginapan);
        JsonParseVolley jsonParse = new JsonParseVolley(this.getApplicationContext());
        SERVER_URL = getString(R.string.server_url) + "katpenginapan.php";
//        Log.d("SERVER_URL",SERVER_URL);
//        JSONObject jsonObject;
//        jsonObject =  jsonParse.getJsonURL(SERVER_URL);
//        Log.d("jsonObject",jsonObject.toString());
        ArrayAdapter<RSSItem> adapter;

        bundle = this.getIntent().getExtras();
        XMLServerLoader xmlServerLoader = new XMLServerLoader(KatPenginapan.this, "katpenginapan.php");
        Log.d("xmlServerLoader",xmlServerLoader.toString());
        myRssFeed = xmlServerLoader.getMyRssFeed();

        if (myRssFeed != null){
            TextView feedTitle = (TextView)findViewById(R.id.feedtitle);
            feedTitle.setText(myRssFeed.getTitle());
            adapter = new	ArrayAdapter<RSSItem>(this, android.R.layout.simple_list_item_1,myRssFeed.getList());
            setListAdapter(adapter);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, DaftarPenginapan.class);
        Log.d("ID", myRssFeed.getItem(position).getCategory());
        bundle.putString("keyIdKatPenginapan", myRssFeed.getItem(position).getCategory());

        intent.putExtras(bundle);
        startActivity(intent);
    }
}
