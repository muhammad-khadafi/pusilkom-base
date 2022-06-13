package com.pusilkom.base.service;


import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.dto.TipeInstitusiDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.TipeInstitusiMapper;
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
public class TipeInstitusiService {

    @Autowired
    TipeInstitusiMapper tipeInstitusiMapper;
    @Autowired
    LogAktivitasService logAktivitasService;


    public List<TipeInstitusi> getTipeInstitusiList() {

        return tipeInstitusiMapper.selectByExample(new TipeInstitusiExample());
    }

        public void saveTipeInstitusi(TipeInstitusiDTO tipeInstitusiDTO) {
             logAktivitasService.addLog(tipeInstitusiDTO.getUsername(), "POST", tipeInstitusiDTO.toString(), "/tipe-institusi");
             tipeInstitusiMapper.insert(tipeInstitusiDTO);
         }

         public void updateTipeInstitusi(TipeInstitusiDTO tipeInstitusiDTO) {
             logAktivitasService.addLog(tipeInstitusiDTO.getUsername(), "PUT", tipeInstitusiDTO.toString(), "/tipe-institusi");

             tipeInstitusiMapper.updateByPrimaryKeySelective(tipeInstitusiDTO);
         }

         public ResponseEntityHelper deleteTipeInstitusi(Long id, String username) {
             logAktivitasService.addLog(username, "DELETE", id.toString(), "/tipe-institusi");
             ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
             responseEntityHelper.setStatus(200);
             responseEntityHelper.setMessage("OK");

             tipeInstitusiMapper.deleteByPrimaryKey(id.intValue());

             return responseEntityHelper;
         }

}
