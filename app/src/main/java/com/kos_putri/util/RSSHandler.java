package com.kos_putri.util;

import com.kos_putri.model.RSSFeed;
import com.kos_putri.model.RSSItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler {

    final int state_unknown = 0;
    final int state_title = 1;
    final int state_description = 9;
    final int state_link = 3;
    final int state_pubdate = 4;
    final int state_category = 5;
    final int state_lat = 6;
    final int state_longi = 7;
    final int state_ikon = 8;
    final int state_nmpenginapan = 2;
    int currentState = state_unknown;

    RSSFeed feed;
    RSSItem item;

    boolean itemFound = false;

    public RSSHandler(){

    }

    public RSSFeed getFeed(){
        return feed;
    }

    @Override
    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
        feed = new RSSFeed();
        item = new RSSItem();
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub

        if (localName.equalsIgnoreCase("item")){
            itemFound = true;
            item = new RSSItem();
            currentState = state_unknown;
        }
        else if (localName.equalsIgnoreCase("title")){
            currentState = state_title;
        }
        else if (localName.equalsIgnoreCase("description")){
            currentState = state_description;
        }
        else if (localName.equalsIgnoreCase("link")){
            currentState = state_link;
        }
        else if (localName.equalsIgnoreCase("pubDate")){
            currentState = state_pubdate;
        }
        else if (localName.equalsIgnoreCase("category")){
            currentState = state_category;
        }
        else if (localName.equalsIgnoreCase("lat")){
            currentState = state_lat;
        }
        else if (localName.equalsIgnoreCase("longi")){
            currentState = state_longi;
        }
        else if (localName.equalsIgnoreCase("ikon")){
            currentState = state_ikon;
        }
        else if (localName.equalsIgnoreCase("nmlokasi")){
            currentState = state_nmpenginapan;
        }
        else{
            currentState = state_unknown;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO Auto-generated method stub
        if (localName.equalsIgnoreCase("item")){
            feed.addItem(item);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // TODO Auto-generated method stub

        String strCharacters = new String(ch,start,length);

        if (itemFound==true){
            // "item" tag found, it's item's parameter
            switch(currentState){
                case state_title:
                    item.setTitle(strCharacters);
                    break;
                case state_description:
                    item.setDescription(strCharacters);
                    break;
                case state_link:
                    item.setLink(strCharacters);
                    break;
                case state_pubdate:
                    item.setPubDate(strCharacters);
                    break;
                case state_category:
                    item.setCategory(strCharacters);
                    break;
                case state_lat:
                    item.setLat(strCharacters);
                    break;
                case state_longi:
                    item.setLongi(strCharacters);
                    break;
                case state_ikon:
                    item.setIkon(strCharacters);
                    break;
                case state_nmpenginapan:
                    item.setNmpenginapan(strCharacters);
                    break;
                default:
                    break;
            }
        }
        else{
            // not "item" tag found, it's feed's parameter
            switch(currentState){
                case state_title:
                    feed.setTitle(strCharacters);
                    break;
                case state_description:
                    feed.setDescription(strCharacters);
                    break;
                case state_link:
                    feed.setLink(strCharacters);
                    break;
                case state_pubdate:
                    feed.setPubDate(strCharacters);
                    break;
                case state_category:
                    feed.setCategory(strCharacters);
                    break;
                case state_lat:
                    feed.setLat(strCharacters);
                    break;
                case state_longi:
                    feed.setLongi(strCharacters);
                    break;
                case state_ikon:
                    feed.setIkon(strCharacters);
                    break;
                case state_nmpenginapan:
                    feed.setNmpenginapan(strCharacters);
                    break;
                default:
                    break;
            }
        }

        currentState = state_unknown;
    }

}
