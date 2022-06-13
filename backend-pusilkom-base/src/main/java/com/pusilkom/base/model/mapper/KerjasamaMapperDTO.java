package com.pusilkom.base.model.mapper;

import com.pusilkom.base.model.Kerjasama;
import com.pusilkom.base.model.KerjasamaExample;

import java.util.List;

/**
 * Created by wida.sari on 27 ags 2020.
 */
public interface KerjasamaMapperDTO extends BaseMapper<Kerjasama, Integer, KerjasamaExample> {
    List<Kerjasama> getKerjasamaAktif();
}
