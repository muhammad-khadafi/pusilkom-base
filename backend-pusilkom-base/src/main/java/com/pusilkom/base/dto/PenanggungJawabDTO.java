package com.pusilkom.base.dto;

import com.pusilkom.base.model.PenanggungJawab;

import java.util.Arrays;

/**
 * Created by muhammad.khadafi on 20/02/2020.
 */
public class PenanggungJawabDTO extends PenanggungJawab {

    private String kerjasamaStr;
    private String institusiStr;

    private String usernameSubmitter;

    public String getUsernameSubmitter() {
        return usernameSubmitter;
    }

    public void setUsernameSubmitter(String usernameSubmitter) {
        this.usernameSubmitter = usernameSubmitter;
    }

    public String getKerjasamaStr() {
        return kerjasamaStr;
    }

    public void setKerjasamaStr(String kerjasamaStr) {
        this.kerjasamaStr = kerjasamaStr;
    }

    public String getInstitusiStr() {
        return institusiStr;
    }

    public void setInstitusiStr(String institusiStr) {
        this.institusiStr = institusiStr;
    }

    @Override
    public String toString() {

        return "{" +
                "id=" + getId() +
                ", idKerjasama='" + getIdKerjasama() + '\'' +
                ", idInstitusi='" + getIdInstitusi() + '\'' +
                ", nama='" + getNama() + '\'' +
                ", alamat='" + getAlamat() + '\'' +
                ", jabatan=" + getJabatan() +
                ", email=" + getEmail() +
                ", kerjasamaStr=" + getKerjasamaStr() +
                ", institusiStr='" + getInstitusiStr() + '\'' +
                '}';
    }

}
