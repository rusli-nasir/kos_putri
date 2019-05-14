package com.kos_putri.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.kos_putri.R;

import java.io.IOException;
import java.net.URL;

public class LbsLink {

    private String urlta, img_link;

    public LbsLink(Context ctx, String LbsUrl){
        this.urlta = ctx.getResources().getString(R.string.server_url) + LbsUrl;
    }

    public String getUrl(){
        return urlta;
    }

    public Bitmap loadImageUrl(String url){

        Bitmap bmp = null;
        URL img_url = null;
        try {
            Log.d("url", url);
            img_url = new URL(url);
            bmp = BitmapFactory.decodeStream(img_url.openConnection().getInputStream());
            return bmp;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
