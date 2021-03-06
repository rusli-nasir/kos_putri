package com.kos_putri.model;

import java.util.List;
import java.util.Vector;

public class RSSFeed {

    private String id = null;
    private String title = null;
    private String description = null;
    private String link = null;
    private String pubDate = null;
    private String category = null;
    private String lat = null;
    private String longi = null;
    private String ikon = null;
    private String nmpenginapan = null;

    private List<RSSItem> itemList;

    public RSSFeed(){
        itemList = new Vector<RSSItem>(0);
    }

    public void addItem(RSSItem item){
        itemList.add(item);
    }

    public RSSItem getItem(int location){
        return itemList.get(location);
    }

    public List<RSSItem> getList(){
        return itemList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getIkon() {
        return ikon;
    }

    public void setIkon(String ikon) {
        this.ikon = ikon;
    }

    public String getNmpenginapan() {
        return nmpenginapan;
    }

    public void setNmpenginapan(String nmpenginapan) {
        this.nmpenginapan = nmpenginapan;
    }
}
