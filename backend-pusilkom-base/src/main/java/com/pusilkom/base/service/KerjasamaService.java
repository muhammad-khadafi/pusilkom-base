package com.pusilkom.base.service;

import com.pusilkom.base.dto.KerjasamaDTO;

import com.pusilkom.base.model.Kerjasama;
import com.pusilkom.base.model.KerjasamaExample;
import com.pusilkom.base.model.mapper.KerjasamaMapper;

import com.pusilkom.base.model.mapper.KerjasamaMapperDTO;

import com.pusilkom.base.model.mapper.InstitusiMapper;

import com.pusilkom.base.model.mapper.TipeKerjasamaMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pusilkom.base.helper.ResponseEntityHelper;

import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class KerjasamaService {

    @Autowired
    LogAktivitasService logAktivitasService;

    @Autowired
    KerjasamaMapper kerjasamaMapper;

    @Autowired
    InstitusiMapper institusiMapper;

    @Autowired
    TipeKerjasamaMapper tipeKerjasamaMapper;

    @Autowired
    KerjasamaMapperDTO kerjasamaMapperDTO;

    public List<Kerjasama> getKerjasama() {
        return kerjasamaMapper.selectByExample(new KerjasamaExample());
    }

    public List<KerjasamaDTO> getKerjasamaDTO() {
      List<Kerjasama> listKerjasama = kerjasamaMapper.selectByExample(new KerjasamaExample());
      List<KerjasamaDTO> listKerjasamaDTO = new ArrayList<>();

      for (Kerjasama kerjasama : listKerjasama){
        KerjasamaDTO kerjasamaDTO = this.toKerjasamaDTO(kerjasama);

        if(kerjasamaDTO.getIdTipeKerjasama() != null){
          kerjasamaDTO.setTipeKerjasamaStr(tipeKerjasamaMapper.selectByPrimaryKey(kerjasamaDTO.getIdTipeKerjasama()).getNama());
        }

        if(kerjasamaDTO.getIdPihakPertama() != null){
          kerjasamaDTO.setPihakPertamaStr(institusiMapper.selectByPrimaryKey(kerjasamaDTO.getIdPihakPertama()).getNama());
        }

        if(kerjasamaDTO.getIdPihakKedua() != null){
          kerjasamaDTO.setPihakKeduaStr(institusiMapper.selectByPrimaryKey(kerjasamaDTO.getIdPihakKedua()).getNama());
        }

        listKerjasamaDTO.add(kerjasamaDTO);
      }

      return listKerjasamaDTO;
    }

    // update
    public void updateKerjasama(KerjasamaDTO kerjasamaDTO) {
        logAktivitasService.addLog(kerjasamaDTO.getUsernameSubmitter(), "PUT", kerjasamaDTO.toString(), "/kerjasama");
        kerjasamaMapper.updateByPrimaryKeySelective(toKerjasama(kerjasamaDTO));
    }

    // add
    public void addKerjasama(KerjasamaDTO kerjasamaDTO) {
        logAktivitasService.addLog(kerjasamaDTO.getUsernameSubmitter(), "POST", kerjasamaDTO.toString(), "/kerjasama");
        kerjasamaMapper.insert(toKerjasama(kerjasamaDTO));
    }

    // delete
    public ResponseEntityHelper deleteKerjasama(Integer id, String usernameSubmitter) {
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        //responseEntityHelper = checkingConstraints(id, responseEntityHelper);

        if (responseEntityHelper.getStatus() == 200) {
            kerjasamaMapper.deleteByPrimaryKey(Integer.parseInt(id.toString()));
            logAktivitasService.addLog(usernameSubmitter, "DELETE",
                id.toString(), "/kerjasama/" + id.toString() + "/" + usernameSubmitter);
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
    public Kerjasama toKerjasama(KerjasamaDTO kerjasamaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(kerjasamaDTO, Kerjasama.class);
    }

    public KerjasamaDTO toKerjasamaDTO(Kerjasama kerjasama) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(kerjasama, KerjasamaDTO.class);
    }

    public List<Kerjasama> getKerjasamaAktif(){
        List<Kerjasama> listKerjasama = null;
        listKerjasama = kerjasamaMapperDTO.getKerjasamaAktif();
        return listKerjasama;
    }
}
