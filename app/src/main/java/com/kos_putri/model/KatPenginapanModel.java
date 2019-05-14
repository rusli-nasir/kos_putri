package com.kos_putri.model;

public class KatPenginapanModel {

    private int id_kategori_penginapan;
    private String nama_kategori;
    private String keterangan;

    public KatPenginapanModel(int id_kategori_penginapan,String nama_kategori,String keterangan ){
        super();
        this.id_kategori_penginapan = id_kategori_penginapan;
        this.nama_kategori = nama_kategori;
        this.keterangan = keterangan;
    }

    public void setId_kategori_penginapan(int id_kategori_penginapan) {
        this.id_kategori_penginapan = id_kategori_penginapan;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


    public int getId_kategori_penginapan() {
        return id_kategori_penginapan;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
