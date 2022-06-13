package com.pusilkom.base.dto;

import com.pusilkom.base.model.Kerjasama;

/**
 * Created by muhammad.khadafi on 20/02/2020.
 */
public class KerjasamaDTO extends Kerjasama {

    private String tipeKerjasamaStr;
    private String pihakPertamaStr;
    private String pihakKeduaStr;
    private String usernameSubmitter;

    public String getUsernameSubmitter() {
        return usernameSubmitter;
    }

    public void setUsernameSubmitter(String usernameSubmitter) {
        this.usernameSubmitter = usernameSubmitter;
    }

    public String getTipeKerjasamaStr() {
        return tipeKerjasamaStr;
    }

    public void setTipeKerjasamaStr(String tipeKerjasamaStr) {
        this.tipeKerjasamaStr = tipeKerjasamaStr;
    }

    public String getPihakPertamaStr() {
        return pihakPertamaStr;
    }

    public void setPihakPertamaStr(String pihakPertamaStr) {
        this.pihakPertamaStr = pihakPertamaStr;
    }

    public String getPihakKeduaStr() {
        return pihakKeduaStr;
    }

    public void setPihakKeduaStr(String pihakKeduaStr) {
        this.pihakKeduaStr = pihakKeduaStr;
    }

    @Override
    public String toString() {

        return "{" +
                "id=" + getId() +
                ", idTipeKerjasama='" + this.getIdTipeKerjasama() + '\'' +
                ", idPihakPertama='" + getIdPihakPertama() + '\'' +
                ", idPihakKedua='" + getIdPihakKedua() + '\'' +
                ", judul='" + getJudul() + '\'' +
                ", deskripsi='" + getDeskripsi() + '\'' +
                ", tanggal_mulai='" + getTanggalMulai() + '\'' +
                ", tanggal_selesai='" + getTanggalSelesai() + '\'' +
                ", tanggal_perjanjian='" + getTanggalPerjanjian() + '\'' +
                ", tipeKerjasamaStr=" + getTipeKerjasamaStr() +
                ", pihakPertamaStr='" + getPihakPertamaStr() + '\'' +
                ", pihakKeduaStr='" + getPihakKeduaStr() + '\'' +
                '}';
    }

}
