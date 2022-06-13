package com.pusilkom.base.dto;

import com.pusilkom.base.model.Institusi;

import java.util.Arrays;

/**
 * Created by muhammad.khadafi on 20/02/2020.
 */
public class InstitusiDTO extends Institusi {

    private String parentInstitusiStr;
    private String negaraStr;
    private String tipeInstitusiStr;
    private String usernameSubmitter;

    public String getUsernameSubmitter() {
        return usernameSubmitter;
    }

    public void setUsernameSubmitter(String usernameSubmitter) {
        this.usernameSubmitter = usernameSubmitter;
    }

    public String getParentInstitusiStr() {
        return parentInstitusiStr;
    }

    public void setParentInstitusiStr(String parentInstitusiStr) {
        this.parentInstitusiStr = parentInstitusiStr;
    }

    public String getNegaraStr() {
        return negaraStr;
    }

    public void setNegaraStr(String negaraStr) {
        this.negaraStr = negaraStr;
    }

    public String getTipeInstitusiStr() {
        return tipeInstitusiStr;
    }

    public void setTipeInstitusiStr(String tipeInstitusiStr) {
        this.tipeInstitusiStr = tipeInstitusiStr;
    }

    @Override
    public String toString() {

        return "{" +
                "id=" + getId() +
                ", idParentInstitusi='" + getIdParentInstitusi() + '\'' +
                ", idNegara='" + getIdNegara() + '\'' +
                ", idTipeInstitusi='" + getIdTipeInstitusi() + '\'' +
                ", kodeSso='" + getKodeSso() + '\'' +
                ", nama='" + getNama() + '\'' +
                ", alamat='" + getAlamat() + '\'' +
                ", isUnhan=" + getIsUnhan() +
                ", parentInstitusiStr=" + getParentInstitusiStr() +
                ", negaraStr='" + getNegaraStr() + '\'' +
                ", tipeInstitusiStr='" + getTipeInstitusiStr() + '\'' +
                '}';
    }

}
