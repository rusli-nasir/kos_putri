package com.kos_putri.model;

public class PenginapanModel {

    private int id_penginapan;
    private int id_kategori_penginapan;
    private String nama_kategori;
    private int id_icon;
    private String icon;
    private String nama;
    private String fasilitas;
    private String harga;
    private String status;
    private String alamat;
    private double latitude;
    private double longitude;
    private String telepon;
    private String gambar;

    public PenginapanModel(int id_penginapan,
                           int id_kategori_penginapan,
                           String nama_kategori,
                           int id_icon,
                           String icon,
                           String nama,
                           String fasilitas,
                           String harga,
                           String status,
                           String alamat,
                           double latitude,
                           double longitude,
                           String telepon,
                           String gambar){
        super();
        this.id_penginapan = id_penginapan;
        this.id_kategori_penginapan = id_kategori_penginapan;
        this.nama_kategori = nama_kategori;
        this.id_icon = id_icon;
        this.icon = icon;
        this.nama = nama;
        this.fasilitas = fasilitas;
        this.harga = harga;
        this.status = status;
        this.alamat = alamat;
        this.latitude = latitude;
        this.longitude = longitude;
        this.telepon = telepon;
        this.gambar = gambar;
    }

    public int getId_penginapan() {
        return id_penginapan;
    }

    public void setId_penginapan(int id_penginapan) {
        this.id_penginapan = id_penginapan;
    }

    public int getId_kategori_penginapan() {
        return id_kategori_penginapan;
    }

    public void setId_kategori_penginapan(int id_kategori_penginapan) {
        this.id_kategori_penginapan = id_kategori_penginapan;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public int getId_icon() {
        return id_icon;
    }

    public void setId_icon(int id_icon) {
        this.id_icon = id_icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
