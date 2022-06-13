package com.pusilkom.base.dto;

import com.pusilkom.base.model.TipeDokumen;

/**
 * Created by muhammad.khadafi on 11/03/2020.
 */
public class TipeDokumenDTO extends TipeDokumen {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", nama='" + getNama() + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}
