package com.dev.kedaiit.sibooks.model;

public class DataPenerbit {
    public DataPenerbit(){
        this.kode_penerbit = kode_penerbit;
        this.penerbit = penerbit;
    }

    public String getKode_penerbit() {
        return kode_penerbit;
    }

    public void setKode_penerbit(String kode_penerbit) {
        this.kode_penerbit = kode_penerbit;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    String kode_penerbit, penerbit;
}
