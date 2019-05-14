package com.kos_putri.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.util.Log;

import com.kos_putri.model.RSSFeed;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLServerLoader {

    ProgressDialog pdLoading;
    private Context ctx;
    private RSSFeed myRssFeed = null;
    private String link_url;

    public XMLServerLoader(Context ctx,String url) {
        this.ctx = ctx;
        this.link_url = url;
        XmlLoader();
    }

    private void XmlLoader(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        LbsLink linkurl;
        String SERVER_URL;
        try {
            linkurl = new LbsLink(ctx.getApplicationContext(), this.link_url);
            //linkurl = new lbslink(ctx.getApplicationContext(), "katgereja.php");
            Log.d("linkurl", linkurl.getUrl());
            SERVER_URL = linkurl.getUrl();
            URL rssUrl = new URL(SERVER_URL);
            Log.d("rssUrl", rssUrl.getContent().toString());
            SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();

            SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
            XMLReader myXMLReader = mySAXParser.getXMLReader();
            RSSHandler myRSSHandler = new RSSHandler();
            myXMLReader.setContentHandler(myRSSHandler);
            InputSource myInputSource = new InputSource(rssUrl.openStream());
            myXMLReader.parse(myInputSource);
//			Log.d("myInputSource", myRSSHandler.get);
            setMyRssFeed(myRSSHandler.getFeed());
			Log.d("myRssFeedList", myRssFeed.toString());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (NetworkOnMainThreadException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void setMyRssFeed(RSSFeed myRssFeed) {
        this.myRssFeed = myRssFeed;
    }

    public RSSFeed getMyRssFeed() {
        return myRssFeed;
    }

}
