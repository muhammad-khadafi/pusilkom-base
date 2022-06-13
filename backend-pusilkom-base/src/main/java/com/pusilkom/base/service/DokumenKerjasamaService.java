package com.pusilkom.base.service;

import com.pusilkom.base.dto.DokumenKerjasamaDTO;

import com.pusilkom.base.model.DokumenKerjasama;
import com.pusilkom.base.model.DokumenKerjasamaExample;
import com.pusilkom.base.model.mapper.DokumenKerjasamaMapper;

import com.pusilkom.base.model.mapper.KerjasamaMapper;

import com.pusilkom.base.model.mapper.TipeDokumenMapper;

import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pusilkom.base.helper.ResponseEntityHelper;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class DokumenKerjasamaService {

    @Autowired
    LogAktivitasService logAktivitasService;

    @Autowired
    DokumenKerjasamaMapper dokumenKerjasamaMapper;

    @Autowired
    KerjasamaMapper kerjasamaMapper;

    @Autowired
    TipeDokumenMapper tipeDokumenMapper;

    public List<DokumenKerjasama> getDokumenKerjasama() {
        return dokumenKerjasamaMapper.selectByExample(new DokumenKerjasamaExample());
    }

    public DokumenKerjasama getDokumenKerjasamaById(Integer id) {
        return dokumenKerjasamaMapper.selectByPrimaryKey(id);
    }

    public List<DokumenKerjasamaDTO> getDokumenKerjasamaDTO() {
      List<DokumenKerjasama> listDokumenKerjasama = dokumenKerjasamaMapper.selectByExample(new DokumenKerjasamaExample());
      List<DokumenKerjasamaDTO> listDokumenKerjasamaDTO = new ArrayList<>();

      for (DokumenKerjasama dokumenKerjasama : listDokumenKerjasama){
        DokumenKerjasamaDTO dokumenKerjasamaDTO = this.toDokumenKerjasamaDTO(dokumenKerjasama);

        if(dokumenKerjasamaDTO.getIdTipeDokumen() != null){
          dokumenKerjasamaDTO.setTipeDokumenStr(tipeDokumenMapper.selectByPrimaryKey(dokumenKerjasamaDTO.getIdTipeDokumen()).getNama());
        }

        if(dokumenKerjasamaDTO.getIdKerjasama() != null){
          dokumenKerjasamaDTO.setKerjasamaStr(kerjasamaMapper.selectByPrimaryKey(dokumenKerjasamaDTO.getIdKerjasama()).getJudul());
        }

        listDokumenKerjasamaDTO.add(dokumenKerjasamaDTO);
      }

      return listDokumenKerjasamaDTO;
    }

    // update
    public void updateDokumenKerjasama(DokumenKerjasamaDTO dokumenKerjasamaDTO) {
        logAktivitasService.addLog(dokumenKerjasamaDTO.getUsernameSubmitter(), "PUT", dokumenKerjasamaDTO.toString(), "/dokumen-kerjasama");
        dokumenKerjasamaMapper.updateByPrimaryKeySelective(toDokumenKerjasama(dokumenKerjasamaDTO));
    }

    // add
    public void addDokumenKerjasama(DokumenKerjasamaDTO dokumenKerjasamaDTO) {
        logAktivitasService.addLog(dokumenKerjasamaDTO.getUsernameSubmitter(), "POST", dokumenKerjasamaDTO.toString(), "/dokumen-kerjasama");
        dokumenKerjasamaMapper.insert(toDokumenKerjasama(dokumenKerjasamaDTO));
    }

    // delete
    public ResponseEntityHelper deleteDokumenKerjasama(Integer id, String usernameSubmitter) {
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        //responseEntityHelper = checkingConstraints(id, responseEntityHelper);

        if (responseEntityHelper.getStatus() == 200) {
            dokumenKerjasamaMapper.deleteByPrimaryKey(Integer.parseInt(id.toString()));
            logAktivitasService.addLog(usernameSubmitter, "DELETE",
                id.toString(), "/dokumen-kerjasama/" + id.toString() + "/" + usernameSubmitter);
        }

        return responseEntityHelper;
    }

    // check constraints before delete
  //  private ResponseEntityHelper checkingConstraints(Integer id, ResponseEntityHelper responseEntityHelper) {
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
  //      return responseEntityHelper;

//    }

    // convert
    public DokumenKerjasama toDokumenKerjasama(DokumenKerjasamaDTO dokumenKerjasamaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dokumenKerjasamaDTO, DokumenKerjasama.class);
    }

    public DokumenKerjasamaDTO toDokumenKerjasamaDTO(DokumenKerjasama dokumenKerjasama) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dokumenKerjasama, DokumenKerjasamaDTO.class);
    }


    public List<DokumenKerjasamaDTO> getDokumenKerjasamaDTO(Integer idKerjasama) {
        DokumenKerjasamaExample dokumenKerjasamaExample = new DokumenKerjasamaExample();
        dokumenKerjasamaExample.createCriteria().andIdKerjasamaEqualTo(idKerjasama);
        List<DokumenKerjasama> listDokumenKerjasama = dokumenKerjasamaMapper.selectByExample(dokumenKerjasamaExample);
        List<DokumenKerjasamaDTO> listDokumenKerjasamaDTO = new ArrayList<>();

        for (DokumenKerjasama dokumenKerjasama : listDokumenKerjasama){
            DokumenKerjasamaDTO dokumenKerjasamaDTO = this.toDokumenKerjasamaDTO(dokumenKerjasama);

            if(dokumenKerjasamaDTO.getIdTipeDokumen() != null){
                dokumenKerjasamaDTO.setTipeDokumenStr(tipeDokumenMapper.selectByPrimaryKey(dokumenKerjasamaDTO.getIdTipeDokumen()).getNama());
            }

            if(dokumenKerjasamaDTO.getIdKerjasama() != null){
                dokumenKerjasamaDTO.setKerjasamaStr(kerjasamaMapper.selectByPrimaryKey(dokumenKerjasamaDTO.getIdKerjasama()).getJudul());
            }

            listDokumenKerjasamaDTO.add(dokumenKerjasamaDTO);
        }

        return listDokumenKerjasamaDTO;

    }

    public void deleteFile(Integer id) {
        DokumenKerjasama dokumenKerjasama = dokumenKerjasamaMapper.selectByPrimaryKey(id);
        if (dokumenKerjasama.getFilePath() != null) {
            File file = new File(dokumenKerjasama.getFilePath());
            file.delete();
        }
    }

    public String getFile(Integer id) {
        DokumenKerjasama dokumenKerjasama = dokumenKerjasamaMapper.selectByPrimaryKey(id);
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(dokumenKerjasama.getFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedFile = "data:application/pdf;base64," + Base64.getEncoder().encodeToString(fileContent);
        return encodedFile;
    }
}
