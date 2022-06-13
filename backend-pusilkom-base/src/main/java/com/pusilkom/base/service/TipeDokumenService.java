package com.pusilkom.base.service;

import com.pusilkom.base.dto.TipeDokumenDTO;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.TipeDokumenMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wida.sari on 2020-08-27 17:45:51.
 */

@Service
@Transactional
public class TipeDokumenService {

    @Autowired
    TipeDokumenMapper tipeDokumenMapper;
    @Autowired
    LogAktivitasService logAktivitasService;

    // get by id
    public TipeDokumen getTipeDokumenById(Integer id) {
        TipeDokumenExample tipeDokumenExample = new TipeDokumenExample();
        tipeDokumenExample.createCriteria().andIdEqualTo(id.intValue());
        List<TipeDokumen> listTipeDokumen = tipeDokumenMapper.selectByExample(tipeDokumenExample);
        return listTipeDokumen.get(0);
    }

    // add
    public void saveTipeDokumen(TipeDokumenDTO tipeDokumenDTO) {
        logAktivitasService.addLog(tipeDokumenDTO.getUsername(), "POST", tipeDokumenDTO.toString(), "/tipe-dokumen");
        tipeDokumenMapper.insert(toTipeDokumen(tipeDokumenDTO));
    }

    // convert
    public TipeDokumen toTipeDokumen(TipeDokumenDTO tipeDokumenDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tipeDokumenDTO, TipeDokumen.class);
    }

    // delete
    public ResponseEntityHelper deleteTipeDokumen(Integer id, String username) {
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        responseEntityHelper = checkingConstraints(id, responseEntityHelper);
        if (responseEntityHelper.getStatus() == 200) {
            //logAktivitasService.addLog(username, "DELETE", id.toString(), "/tipe-dokumen");
            logAktivitasService.addLog(username, "DELETE",
                id.toString(), "/tipe-dokumen/" + id.toString() + "/" + username);

            tipeDokumenMapper.deleteByPrimaryKey(id.intValue());
        }
        return responseEntityHelper;
    }

    private ResponseEntityHelper checkingConstraints(Integer id, ResponseEntityHelper responseEntityHelper) {
        //checking user role
        /*UserwebHasRoleExample userwebHasRoleExample = new UserwebHasRoleExample();
        userwebHasRoleExample.createCriteria().andIdRoleEqualTo(id.intValue());
        List<UserwebHasRole> userwebHasRoles = userwebHasRoleMapper.selectByExample(userwebHasRoleExample);

        Role role = roleMapper.selectByPrimaryKey(id.intValue());*/

        return responseEntityHelper;


    }

    // update
    public void updateTipeDokumen(TipeDokumenDTO tipeDokumenDTO) {
        logAktivitasService.addLog(tipeDokumenDTO.getUsername(), "PUT", tipeDokumenDTO.toString(), "/tipe-dokumen");
        tipeDokumenMapper.updateByPrimaryKeySelective(toTipeDokumen(tipeDokumenDTO));
    }

    // get all
    public List<TipeDokumen> getTipeDokumenList() {
        return tipeDokumenMapper.selectByExample(new TipeDokumenExample());
    }
}
