package com.pusilkom.base.dto;

import com.pusilkom.base.model.Negara;

/**
 * Created by sumiati hutagalung on 31/08/2020.
 */
public class NegaraDTO extends Negara {

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
