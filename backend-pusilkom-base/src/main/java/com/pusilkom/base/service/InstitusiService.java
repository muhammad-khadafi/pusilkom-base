package com.pusilkom.base.service;
import com.pusilkom.base.dto.InstitusiDTO;

import com.pusilkom.base.model.Institusi;
import com.pusilkom.base.model.InstitusiExample;
import com.pusilkom.base.model.mapper.InstitusiMapper;

import com.pusilkom.base.model.Negara;
import com.pusilkom.base.model.NegaraExample;
import com.pusilkom.base.model.mapper.NegaraMapper;

import com.pusilkom.base.model.TipeInstitusi;
import com.pusilkom.base.model.TipeInstitusiExample;
import com.pusilkom.base.model.mapper.TipeInstitusiMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pusilkom.base.helper.ResponseEntityHelper;


import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class InstitusiService {

    @Autowired
    LogAktivitasService logAktivitasService;

    @Autowired
    InstitusiMapper institusiMapper;

    @Autowired
    NegaraMapper negaraMapper;

    @Autowired
    TipeInstitusiMapper tipeInstitusiMapper;

    public List<Institusi> getInstitusi() {
        return institusiMapper.selectByExample(new InstitusiExample());
    }

    public List<InstitusiDTO> getInstitusiDTO() {
      List<Institusi> listInstitusi = institusiMapper.selectByExample(new InstitusiExample());
      List<InstitusiDTO> listInstitusiDTO = new ArrayList<>();

      for (Institusi institusi : listInstitusi){
        InstitusiDTO institusiDTO = this.toInstitusiDTO(institusi);

        if(institusiDTO.getIdNegara() != null){
          institusiDTO.setNegaraStr(negaraMapper.selectByPrimaryKey(institusi.getIdNegara()).getNama());
        }

        if(institusiDTO.getIdParentInstitusi() != null){
          institusiDTO.setParentInstitusiStr(institusiMapper.selectByPrimaryKey(institusi.getIdParentInstitusi()).getNama());
        }

        if(institusiDTO.getIdTipeInstitusi() != null){
          institusiDTO.setTipeInstitusiStr(tipeInstitusiMapper.selectByPrimaryKey(institusi.getIdTipeInstitusi()).getNama());
        }

        listInstitusiDTO.add(institusiDTO);
      }

      return listInstitusiDTO;
    }

    // update
    public void updateInstitusi(InstitusiDTO institusiDTO) {
        logAktivitasService.addLog(institusiDTO.getUsernameSubmitter(), "PUT", institusiDTO.toString(), "/institusi");
        institusiMapper.updateByPrimaryKeySelective(toInstitusi(institusiDTO));
    }

    // add
    public void addInstitusi(InstitusiDTO institusiDTO) {
        logAktivitasService.addLog(institusiDTO.getUsernameSubmitter(), "POST", institusiDTO.toString(), "/institusi");
        institusiMapper.insert(toInstitusi(institusiDTO));
    }

    // delete
    public ResponseEntityHelper deleteInstitusi(Integer id, String usernameSubmitter) {
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        //responseEntityHelper = checkingConstraints(id, responseEntityHelper);

        if (responseEntityHelper.getStatus() == 200) {
            institusiMapper.deleteByPrimaryKey(Integer.parseInt(id.toString()));
            logAktivitasService.addLog(usernameSubmitter, "DELETE",
                id.toString(), "/institusi/" + id.toString() + "/" + usernameSubmitter);
        }

        return responseEntityHelper;
    }

    // check constraints before delete
    private ResponseEntityHelper checkingConstraints(Integer id, ResponseEntityHelper responseEntityHelper) {
        // checking reviewer skema
//        ReviewerSkemaExample reviewerSkemaExample = new ReviewerSkemaExample();
//        reviewerSkemaExample.createCriteria().andIdPemberiTugasEqualTo(id.intValue());
//        List<ReviewerSkema> reviewerSkema = reviewerSkemaMapper.selectByExample(reviewerSkemaExample);

/*        if (//!reviewerSkema.isEmpty() ||
                    !userwebHasRoles.isEmpty()
//            || !komentars.isEmpty()
        ) {
            responseEntityHelper.setMessage("Data digunakan sebagai referensi oleh data lain");
            responseEntityHelper.setStatus(409);
        }
*/
        return responseEntityHelper;

    }

    // convert
    public Institusi toInstitusi(InstitusiDTO institusiDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(institusiDTO, Institusi.class);
    }

    public InstitusiDTO toInstitusiDTO(Institusi institusi) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(institusi, InstitusiDTO.class);
    }


}
