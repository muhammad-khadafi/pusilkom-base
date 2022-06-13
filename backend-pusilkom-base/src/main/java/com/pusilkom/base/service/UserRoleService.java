package com.pusilkom.base.service;

import com.pusilkom.base.dto.UserRoleDTO;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.UserwebHasRole;
import com.pusilkom.base.model.UserwebHasRoleExample;
import com.pusilkom.base.model.mapper.RoleMapper;
import com.pusilkom.base.model.mapper.UserwebHasRoleMapper;
import com.pusilkom.base.model.mapper.UserwebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad.khadafi on 19/02/2020.
 */

@Service
@Transactional
public class UserRoleService {

    @Autowired
    UserwebHasRoleMapper userwebHasRoleMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserwebMapper userwebMapper;

    @Autowired
    LogAktivitasService logAktivitasService;

    public void saveUserRole(UserRoleDTO userRoleDTO) {
        logAktivitasService.addLog(userRoleDTO.getUsername(), "POST", userRoleDTO.toString(), "/user-role");

        UserwebHasRole userwebHasRole = new UserwebHasRole();
        userwebHasRole.setIdUserweb(userRoleDTO.getIdUserweb());

        for (Long idRole : userRoleDTO.getIdRoles()) {
            userwebHasRole.setIdRole(idRole.intValue());
            userwebHasRoleMapper.insert(userwebHasRole);
        }

    }

    public List<UserRoleDTO> getUserRole() {
        List<UserRoleDTO> userRoleDTOList = new ArrayList<>();
        userRoleDTOList = userwebHasRoleMapper.getUserwebHasRole();

        return formattingList(userRoleDTOList);
    }

    public List<UserRoleDTO> formattingList(List<UserRoleDTO> userRoleDTOList){
        for (UserRoleDTO userRoleDTO :
                userRoleDTOList) {
            String rolesStr = "";
            List<Long> idRoles = new ArrayList<Long>();
            for (int i = 0; i < userRoleDTO.getRoles().size(); i++) {
                rolesStr = rolesStr + (i == 0 ? "" : ", ") + userRoleDTO.getRoles().get(i).getRoleName();
                idRoles.add(userRoleDTO.getRoles().get(i).getId().longValue());
            }
            userRoleDTO.setRolesStr(rolesStr);
            userRoleDTO.setIdRoles(idRoles);
        }

        return userRoleDTOList;
    }

    public void deleteUserRole(Long idUserweb, String username) {
        logAktivitasService.addLog(username, "DELETE", idUserweb.toString(), "/user-role");

        UserwebHasRoleExample userwebHasRoleExample = new UserwebHasRoleExample();
        userwebHasRoleExample.createCriteria().andIdUserwebEqualTo(idUserweb.intValue());
        userwebHasRoleMapper.deleteByExample(userwebHasRoleExample);
    }
    public ResponseEntityHelper deleteUserRoleWithRespEnt(Long idUserweb, String username) {
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

//        responseEntityHelper = checkingRiwayatPenugasan(idUserweb, responseEntityHelper);

        if (responseEntityHelper.getStatus() == 200){
            deleteUserRole(idUserweb, username);
        }

        return responseEntityHelper;
    }

    private ResponseEntityHelper checkingRiwayatPenugasan(Long idUserweb, ResponseEntityHelper responseEntityHelper) {

        Integer riwayatPenugasan = userwebHasRoleMapper.checkingRiwayatPenugasan(idUserweb);

        if(riwayatPenugasan > 0) {
            responseEntityHelper.setMessage("User memiliki riwayat penugasan, tidak dapat dihapus!");
            responseEntityHelper.setStatus(409);
        }

        return responseEntityHelper;

    }

    public void updateUserRole(UserRoleDTO userRoleDTO) {
        logAktivitasService.addLog(userRoleDTO.getUsername(), "PUT", userRoleDTO.toString(), "/user-role");
        deleteUserRole(userRoleDTO.getIdUserweb().longValue(), userRoleDTO.getUsername());
        saveUserRole(userRoleDTO);
    }

    public List<UserRoleDTO> getUserRoleByOrganization(Integer organizationId) {
        List<UserRoleDTO> userRoleDTOList = new ArrayList<>();
        userRoleDTOList = userwebHasRoleMapper.getUserwebHasRoleByOrganization(organizationId);

        return formattingList(userRoleDTOList);

    }

    public List<UserRoleDTO> getUserRole(Integer organizationId, String roleCode) {
        List<UserRoleDTO> userRoleDTOList = new ArrayList<>();
        userRoleDTOList = userwebHasRoleMapper.getUserRoleAll(organizationId, roleCode);

        return formattingList(userRoleDTOList);

    }
}
