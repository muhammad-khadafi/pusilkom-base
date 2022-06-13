package com.pusilkom.base.controller;

import com.pusilkom.base.model.Kerjasama;
import com.pusilkom.base.service.KerjasamaService;
import com.pusilkom.base.dto.KerjasamaDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.pusilkom.base.helper.ResponseEntityHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@RestController
public class KerjasamaController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KerjasamaService kerjasamaService;

    @RequestMapping(value = "/kerjasama", method = RequestMethod.GET)
    public ResponseEntity getKerjasama() {
        ResponseEntity responseEntity = null;

        try {
            List<KerjasamaDTO> listKerjasamaDTO = kerjasamaService.getKerjasamaDTO();
            responseEntity = ResponseEntity.ok(listKerjasamaDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/kerjasama", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody KerjasamaDTO kerjasamaDTO) {
        ResponseEntity responseEntity = null;

        try {
            kerjasamaService.updateKerjasama(kerjasamaDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // add
    @RequestMapping(value = "/kerjasama", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody KerjasamaDTO kerjasamaDTO) {
        ResponseEntity responseEntity = null;

        try {
            kerjasamaService.addKerjasama(kerjasamaDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/kerjasama/{id}/{usernameSubmitter}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String usernameSubmitter) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = kerjasamaService.deleteKerjasama(id, usernameSubmitter);
            if (responesResponseEntityHelper.getStatus() == 409) {
                responseEntity = new ResponseEntity<>(responesResponseEntityHelper.getMessage(), HttpStatus.CONFLICT);
            } else
                responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/kerjasama-aktif", method = RequestMethod.GET)
    public ResponseEntity getKerjasamaAktif() {
        ResponseEntity responseEntity = null;

        try {
            List<Kerjasama> listKerjasama = kerjasamaService.getKerjasamaAktif();
            responseEntity = ResponseEntity.ok(listKerjasama);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }


}
