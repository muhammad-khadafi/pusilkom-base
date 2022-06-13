package com.pusilkom.base.controller;

import com.pusilkom.base.model.PenanggungJawab;
import com.pusilkom.base.service.PenanggungJawabService;
import com.pusilkom.base.dto.PenanggungJawabDTO;

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
public class PenanggungJawabController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PenanggungJawabService penanggungJawabService;

    @RequestMapping(value = "/penanggung-jawab", method = RequestMethod.GET)
    public ResponseEntity getPenanggungJawab() {
        ResponseEntity responseEntity = null;

        try {
            List<PenanggungJawabDTO> listPenanggungJawabDTO = penanggungJawabService.getPenanggungJawabDTO();
            responseEntity = ResponseEntity.ok(listPenanggungJawabDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/penanggung-jawab/kerjasama/{idKerjasama}", method = RequestMethod.GET)
    public ResponseEntity getPenanggungJawabByIdKerjasama(@PathVariable Integer idKerjasama) {
        ResponseEntity responseEntity = null;

        try {
            List<PenanggungJawabDTO> listPenanggungJawabDTO = penanggungJawabService.getPenanggungJawabByIdKerjasamaDTO(idKerjasama);
            responseEntity = ResponseEntity.ok(listPenanggungJawabDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/penanggung-jawab", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody PenanggungJawabDTO penanggungJawabDTO) {
        ResponseEntity responseEntity = null;

        try {
            penanggungJawabService.updatePenanggungJawab(penanggungJawabDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // add
    @RequestMapping(value = "/penanggung-jawab", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody PenanggungJawabDTO penanggungJawabDTO) {
        ResponseEntity responseEntity = null;

        try {
            penanggungJawabService.addPenanggungJawab(penanggungJawabDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/penanggung-jawab/{id}/{usernameSubmitter}", method = RequestMethod.DELETE)
    public ResponseEntity deletePenanggungJawab(@PathVariable Integer id, @PathVariable String usernameSubmitter) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = penanggungJawabService.deletePenanggungJawab(id, usernameSubmitter);
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

}
