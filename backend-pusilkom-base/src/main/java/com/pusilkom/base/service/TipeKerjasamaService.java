package com.pusilkom.base.service;


import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.dto.TipeKerjasamaDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.TipeKerjasamaMapper;
import com.pusilkom.base.model.mapper.UserwebHasRoleMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Edited by sumiati.hutagalung on 2020-08-31
 */

@Service
@Transactional
public class TipeKerjasamaService {

    @Autowired
    TipeKerjasamaMapper tipeKerjasamaMapper;
    @Autowired
    LogAktivitasService logAktivitasService;


    public List<TipeKerjasama> getTipeKerjasamaList() {
        return tipeKerjasamaMapper.selectByExample(new TipeKerjasamaExample());
    }

    public void saveTipeKerjasama(TipeKerjasamaDTO tipeKerjasamaDTO) {
        logAktivitasService.addLog(tipeKerjasamaDTO.getUsername(), "POST", tipeKerjasamaDTO.toString(), "/tipe-kerjasama");
        tipeKerjasamaMapper.insert(tipeKerjasamaDTO);
    }

      public void updateTipeKerjasama(TipeKerjasamaDTO tipeKerjasamaDTO) {
        logAktivitasService.addLog(tipeKerjasamaDTO.getUsername(), "PUT", tipeKerjasamaDTO.toString(), "/tipe-kerjasama");

          tipeKerjasamaMapper.updateByPrimaryKeySelective(tipeKerjasamaDTO);
    }

    public ResponseEntityHelper deleteTipeKerjasama(Long id, String username) {
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/tipe-kerjasama");
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        tipeKerjasamaMapper.deleteByPrimaryKey(id.intValue());

        return responseEntityHelper;
    }
}
