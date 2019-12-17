package com.dev.kedaiit.sibooks.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataHome implements Parcelable {

        public DataHome(){

            this.kode_buku = kode_buku;
            this.judul = judul;
            this.cover = cover;
            this.penulis = penulis;
            this.kode_kategori = kode_kategori;
            this.kode_penerbit = kode_penerbit;
    }

    public String getKode_buku() {
        return kode_buku;
    }

    public void setKode_buku(String kode_buku) {
        this.kode_buku = kode_buku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getKode_kategori() {
        return kode_kategori;
    }

    public void setKode_kategori(String kode_kategori) {
        this.kode_kategori = kode_kategori;
    }

    public String getKode_penerbit() {
        return kode_penerbit;
    }

    public void setKode_penerbit(String kode_penerbit) {
        this.kode_penerbit = kode_penerbit;
    }

    String kode_buku,judul, penulis, cover, kode_kategori, kode_penerbit;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kode_buku);
        dest.writeString(this.judul);
        dest.writeString(this.penulis);
        dest.writeString(this.cover);
        dest.writeString(this.kode_kategori);
        dest.writeString(this.kode_penerbit);
    }

    protected DataHome(Parcel in) {
        this.kode_buku = in.readString();
        this.judul = in.readString();
        this.penulis = in.readString();
        this.cover = in.readString();
        this.kode_kategori = in.readString();
        this.kode_penerbit = in.readString();
    }

    public static final Creator<DataHome> CREATOR = new Creator<DataHome>() {
        @Override
        public DataHome createFromParcel(Parcel source) {
            return new DataHome(source);
        }

        @Override
        public DataHome[] newArray(int size) {
            return new DataHome[size];
        }
    };
}
