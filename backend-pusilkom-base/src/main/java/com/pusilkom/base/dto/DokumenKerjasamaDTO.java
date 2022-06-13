package com.pusilkom.base.dto;

import com.pusilkom.base.model.DokumenKerjasama;

/**
 * Created by muhammad.khadafi on 20/02/2020.
 */
public class DokumenKerjasamaDTO extends DokumenKerjasama {


    private String tipeDokumenStr;
    private String kerjasamaStr;
    private String usernameSubmitter;

    public String getUsernameSubmitter() {
        return usernameSubmitter;
    }

    public void setUsernameSubmitter(String usernameSubmitter) {
        this.usernameSubmitter = usernameSubmitter;
    }

    public String getTipeDokumenStr() {
        return tipeDokumenStr;
    }

    public void setTipeDokumenStr(String tipeDokumenStr) {
        this.tipeDokumenStr = tipeDokumenStr;
    }

    public String getKerjasamaStr() {
        return kerjasamaStr;
    }

    public void setKerjasamaStr(String kerjasamaStr) {
        this.kerjasamaStr = kerjasamaStr;
    }

    @Override
    public String toString() {

        return "{" +
                "id=" + getId() +
                ", idTipeDokumen='" + this.getIdTipeDokumen() + '\'' +
                ", idKerjasama='" + getIdKerjasama() + '\'' +
                ", namaDokumen='" + getNamaDokumen() + '\'' +
                ", namaFile='" + getNamaFile() + '\'' +
                ", tahunDokumen='" + getTahunDokumen() + '\'' +
                ", tipeDokumenStr=" + getTipeDokumenStr() +
                ", kerjasamaStr='" + getKerjasamaStr() + '\'' +
                '}';
    }

}
