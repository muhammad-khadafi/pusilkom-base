package com.pusilkom.base.service;

import com.pusilkom.base.dto.LaporanDTO;
import com.pusilkom.base.model.mapper.LaporanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
/**
 * Created by muhammad.khadafi on 18/03/2020.
 */

@Service
@Transactional
public class LaporanService {

    @Autowired
    LaporanMapper laporanMapper;

    public List<LaporanDTO> getLaporanPerTipeKerjasama() {
        return laporanMapper.getLaporanPerTipeKerjasama();
    }

    public List<LaporanDTO> getLaporanPerTipeInstitusi() {
        return laporanMapper.getLaporanPerTipeInstitusi();
    }

    public List<LaporanDTO> getLaporanPerNegara() {
        return laporanMapper.getLaporanPerNegara();
    }

    public List<LaporanDTO> getLaporanPerRange(){
        ArrayList<LaporanDTO> listLaporan = new ArrayList<LaporanDTO>(4);
        LaporanDTO laporan1 = new LaporanDTO();
        laporan1.setGrouping("1 - 250.000.000");
        laporan1.setPeriode("2020");
        laporan1.setJumlah(2);

        LaporanDTO laporan2 = new LaporanDTO();
        laporan2.setGrouping("250.000.001 - 500.000.000");
        laporan2.setPeriode("2020");
        laporan2.setJumlah(2);

        LaporanDTO laporan3 = new LaporanDTO();
        laporan3.setGrouping("500.000.001 - 1.000.000.000");
        laporan3.setPeriode("2020");
        laporan3.setJumlah(2);

        LaporanDTO laporan4 = new LaporanDTO();
        laporan4.setGrouping("> 1.000.000.000");
        laporan4.setPeriode("2020");
        laporan4.setJumlah(2);

        listLaporan.add(0,laporan1);
        listLaporan.add(1,laporan2);
        listLaporan.add(2,laporan3);
        listLaporan.add(3,laporan4);

        return listLaporan;
    }
}
