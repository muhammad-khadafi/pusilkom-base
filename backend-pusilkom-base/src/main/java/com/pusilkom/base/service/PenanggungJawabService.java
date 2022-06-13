package com.pusilkom.base.service;
import com.pusilkom.base.dto.PenanggungJawabDTO;

import com.pusilkom.base.model.PenanggungJawab;
import com.pusilkom.base.model.PenanggungJawabExample;
import com.pusilkom.base.model.mapper.PenanggungJawabMapper;

import com.pusilkom.base.model.Kerjasama;
import com.pusilkom.base.model.KerjasamaExample;
import com.pusilkom.base.model.mapper.KerjasamaMapper;

import com.pusilkom.base.model.Institusi;
import com.pusilkom.base.model.InstitusiExample;
import com.pusilkom.base.model.mapper.InstitusiMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pusilkom.base.helper.ResponseEntityHelper;


import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class PenanggungJawabService {

    @Autowired
    LogAktivitasService logAktivitasService;

    @Autowired
    KerjasamaMapper kerjasamaMapper;

    @Autowired
    InstitusiMapper institusiMapper;


    @Autowired
    PenanggungJawabMapper penanggungJawabMapper;

    public List<PenanggungJawabDTO> getPenanggungJawabDTO() {
      List<PenanggungJawab> listPenanggungJawab = penanggungJawabMapper.selectByExample(new PenanggungJawabExample());
      List<PenanggungJawabDTO> listPenanggungJawabDTO = new ArrayList<>();

      for (PenanggungJawab penanggungJawab : listPenanggungJawab){
        PenanggungJawabDTO penanggungJawabDTO = this.toPenanggungJawabDTO(penanggungJawab);

        if(penanggungJawabDTO.getIdKerjasama() != null){
          penanggungJawabDTO.setKerjasamaStr(kerjasamaMapper.selectByPrimaryKey(penanggungJawab.getIdKerjasama()).getJudul());
        }

        if(penanggungJawabDTO.getIdInstitusi() != null){
          penanggungJawabDTO.setInstitusiStr(institusiMapper.selectByPrimaryKey(penanggungJawab.getIdInstitusi()).getNama());
        }

        listPenanggungJawabDTO.add(penanggungJawabDTO);
      }

      return listPenanggungJawabDTO;
    }

    public List<PenanggungJawabDTO> getPenanggungJawabByIdKerjasamaDTO(Integer idKerjasama) {
      PenanggungJawabExample penanggungJawabExample = new PenanggungJawabExample();
      penanggungJawabExample.createCriteria().andIdKerjasamaEqualTo(idKerjasama);
      List<PenanggungJawab> listPenanggungJawab = penanggungJawabMapper.selectByExample(penanggungJawabExample);
      List<PenanggungJawabDTO> listPenanggungJawabDTO = new ArrayList<>();

      for (PenanggungJawab penanggungJawab : listPenanggungJawab){
        PenanggungJawabDTO penanggungJawabDTO = this.toPenanggungJawabDTO(penanggungJawab);

        if(penanggungJawabDTO.getIdKerjasama() != null){
          penanggungJawabDTO.setKerjasamaStr(kerjasamaMapper.selectByPrimaryKey(penanggungJawab.getIdKerjasama()).getJudul());
        }

        if(penanggungJawabDTO.getIdInstitusi() != null){
          penanggungJawabDTO.setInstitusiStr(institusiMapper.selectByPrimaryKey(penanggungJawab.getIdInstitusi()).getNama());
        }

        listPenanggungJawabDTO.add(penanggungJawabDTO);
      }

      return listPenanggungJawabDTO;
    }

    // update
    public void updatePenanggungJawab(PenanggungJawabDTO penanggungJawabDTO) {
        logAktivitasService.addLog(penanggungJawabDTO.getUsernameSubmitter(), "PUT", penanggungJawabDTO.toString(), "/penanggung-jawab");
        penanggungJawabMapper.updateByPrimaryKeySelective(toPenanggungJawab(penanggungJawabDTO));
    }

    // add
    public void addPenanggungJawab(PenanggungJawabDTO penanggungJawabDTO) {
        logAktivitasService.addLog(penanggungJawabDTO.getUsernameSubmitter(), "POST", penanggungJawabDTO.toString(), "/penanggung-jawab");
        penanggungJawabMapper.insert(toPenanggungJawab(penanggungJawabDTO));
    }

    // delete
    public ResponseEntityHelper deletePenanggungJawab(Integer id, String usernameSubmitter) {
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        //responseEntityHelper = checkingConstraints(id, responseEntityHelper);

        if (responseEntityHelper.getStatus() == 200) {
            penanggungJawabMapper.deleteByPrimaryKey(Integer.parseInt(id.toString()));
            logAktivitasService.addLog(usernameSubmitter, "DELETE",
                id.toString(), "/penanggung-jawab/" + id.toString() + "/" + usernameSubmitter);
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
    public PenanggungJawab toPenanggungJawab(PenanggungJawabDTO penanggungJawabDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(penanggungJawabDTO, PenanggungJawab.class);
    }

    public PenanggungJawabDTO toPenanggungJawabDTO(PenanggungJawab penanggungJawab) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(penanggungJawab, PenanggungJawabDTO.class);
    }


}
