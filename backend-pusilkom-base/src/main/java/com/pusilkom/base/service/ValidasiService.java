package com.pusilkom.base.service;

import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.*;
import com.pusilkom.base.util.DebugUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pusilz on 12/05/2020.
 */
@Service
@Transactional
public class ValidasiService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DebugUtil d;

    @Autowired
    OrganisasiMapper organisasiMapper;


    @Autowired
    UserwebMapper userwebMapper;

    @Autowired
    UserwebHasRoleMapper userwebHasRoleMapper;


    public Boolean checkUserIsKapusPPM(Integer userId, String roleCode) {
        Boolean isKapus, isPoltekkes;
        isKapus = isPoltekkes = false;

        //check roleCode = KAPUS_PPM
        if (roleCode.equalsIgnoreCase("kapus_ppm"))
            isKapus = true;

        //check user berasal dari poltekkes
        Userweb user = userwebMapper.selectByPrimaryKey(userId);
        Organisasi userOrg = organisasiMapper.selectByPrimaryKey(user.getIdInstitusi());
        if (userOrg.getIsPoltekkes())
            isPoltekkes = true;

        return (isKapus && isPoltekkes);
    }

    public Boolean checkUserIsPengelolaPusat(Integer userId, String roleCode) {
        Boolean isPengelolaPusat, isPpsdm;
        isPengelolaPusat = isPpsdm = false;

        //check roleCode = PENGELOLA_PUSAT
        if (roleCode.equalsIgnoreCase("pengelola_pusat"))
            isPengelolaPusat = true;

        //check user berasal dari PPSDM
        Userweb user = userwebMapper.selectByPrimaryKey(userId);
        Organisasi userOrg = organisasiMapper.selectByPrimaryKey(user.getIdInstitusi());
        if (userOrg.getId() == 1)
            isPpsdm = true;

        return (isPengelolaPusat && isPpsdm);
    }

    public Boolean checkUserIsOperatorPpm(Integer userId, String roleCode) {
        Boolean isOperatorPpm, isPoltekkes;
        isOperatorPpm = isPoltekkes = false;

        //check roleCode = OPERATOR_PPM
        if (roleCode.equalsIgnoreCase("operator_ppm"))
            isOperatorPpm = true;

        //check user berasal dari poltekkes
        Userweb user = userwebMapper.selectByPrimaryKey(userId);
        Organisasi userOrg = organisasiMapper.selectByPrimaryKey(user.getIdInstitusi());
        if (userOrg.getIsPoltekkes())
            isPoltekkes = true;

        return (isOperatorPpm && isPoltekkes);
    }


    //Validasi role, user berasal dari ppsdm
    public Boolean checkUsernameIsUnique(String username) {
        Boolean isUnique = true;
        UserwebExample ex = new UserwebExample();
        ex.createCriteria().andUsernameLikeInsensitive(username);
        List<Userweb> userweb = userwebMapper.selectByExample(ex);
        if (userweb.size() > 0)
            isUnique = false;

        return isUnique;
    }

}
