package com.kos_putri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnPT = (ImageButton) findViewById(R.id.btnPT);
        ImageButton btnLP = (ImageButton) findViewById(R.id.btnLP);
        ImageButton btnHelp = (ImageButton) findViewById(R.id.btnHelp);
        ImageButton btnExit = (ImageButton) findViewById(R.id.btnExit);

        btnPT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Intent iPT = new Intent (Home.this, Map_Radius.class);
//                startActivity(iPT);
                Intent iHelp = new Intent (Home.this, Penginapan.class);
                startActivity(iHelp);

            }
        });

        btnLP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Intent iLP = new Intent (Home.this, KatPenginapan.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("jenis", "lp");
//                iLP.putExtras(bundle);
//                startActivity(iLP);

            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent iHelp = new Intent (Home.this, Help.class);
                startActivity(iHelp);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent iExit = new Intent (Intent.ACTION_MAIN);
                iExit.addCategory(Intent.CATEGORY_HOME);
                iExit.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(iExit);

            }
        });
    }
}
