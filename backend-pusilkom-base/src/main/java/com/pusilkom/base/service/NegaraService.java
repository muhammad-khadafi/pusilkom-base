package com.pusilkom.base.service;


import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.dto.NegaraDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.NegaraMapper;
import com.pusilkom.base.model.mapper.UserwebHasRoleMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Edited by sumiati.hutagalung on 2020-08-28 08:50:51.
 */

@Service
@Transactional
public class NegaraService {

    @Autowired
    NegaraMapper negaraMapper;
    @Autowired
    LogAktivitasService logAktivitasService;


    public List<Negara> getNegaraList() {
        return negaraMapper.selectByExample(new NegaraExample());
    }

    public void saveNegara(NegaraDTO negaraDTO) {
        logAktivitasService.addLog(negaraDTO.getUsername(), "POST", negaraDTO.toString(), "/negara");
        negaraMapper.insert(negaraDTO);
    }

    public void updateNegara(NegaraDTO negaraDTO) {
        logAktivitasService.addLog(negaraDTO.getUsername(), "PUT", negaraDTO.toString(), "/negara");

        negaraMapper.updateByPrimaryKeySelective(negaraDTO);
    }

    public ResponseEntityHelper deleteNegara(Long id, String username) {
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/negara");
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        negaraMapper.deleteByPrimaryKey(id.intValue());

        return responseEntityHelper;
    }

}
