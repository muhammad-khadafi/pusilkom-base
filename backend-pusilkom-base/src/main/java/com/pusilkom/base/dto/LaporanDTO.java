package com.pusilkom.base.dto;

/**
 * Created by muhammad.khadafi on 21/02/2020.
 */
public class LaporanDTO {

    private String grouping;

    private String periode;

    private Integer jumlah;

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
