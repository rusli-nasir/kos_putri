package com.kos_putri.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import com.kos_putri.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class LbsLink {

    private String urlta, img_link;

    public LbsLink(Context ctx, String LbsUrl){
        this.urlta = ctx.getResources().getString(R.string.server_url) + LbsUrl;
    }

    public String getUrl(){
        return urlta;
    }

    public Bitmap loadImageUrl(String url, BitmapFactory.Options options){

        Bitmap bmp = null;
        URL img_url = null;
        try {
            Log.d("url", url);
            img_url = new URL(url);
            Rect rect = new Rect();
            bmp = BitmapFactory.decodeStream(OpenHttpConnection(url),rect,options);
            return bmp;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private InputStream OpenHttpConnection(String strURL) throws IOException{
        InputStream inputStream = null;
        URL url = new URL(strURL);
        URLConnection conn = url.openConnection();


        try{
            HttpURLConnection httpConn = (HttpURLConnection)conn;
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
            }
        }

        catch (Exception ex)
        {
            Log.e("error",ex.toString());
        }
        return inputStream;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
