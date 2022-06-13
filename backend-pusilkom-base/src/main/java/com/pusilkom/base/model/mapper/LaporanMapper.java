package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.LaporanDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by muhammad.khadafi on 26/09/2019.
 */
public interface LaporanMapper {

    List<LaporanDTO> getLaporanPerTipeKerjasama();
    List<LaporanDTO> getLaporanPerTipeInstitusi();
    List<LaporanDTO> getLaporanPerNegara();
}
