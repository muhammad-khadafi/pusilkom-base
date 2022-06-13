package com.pusilkom.base.controller;

import com.pusilkom.base.model.Institusi;
import com.pusilkom.base.service.InstitusiService;
import com.pusilkom.base.dto.InstitusiDTO;

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
public class InstitusiController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InstitusiService institusiService;

    @RequestMapping(value = "/institusi", method = RequestMethod.GET)
    public ResponseEntity getInstitusi() {
        ResponseEntity responseEntity = null;

        try {
            List<InstitusiDTO> listInstitusiDTO = institusiService.getInstitusiDTO();
            responseEntity = ResponseEntity.ok(listInstitusiDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/institusi", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody InstitusiDTO institusiDTO) {
        ResponseEntity responseEntity = null;

        try {
            institusiService.updateInstitusi(institusiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // add
    @RequestMapping(value = "/institusi", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody InstitusiDTO institusiDTO) {
        ResponseEntity responseEntity = null;

        try {
            institusiService.addInstitusi(institusiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/institusi/{id}/{usernameSubmitter}", method = RequestMethod.DELETE)
    public ResponseEntity deleteInstitusi(@PathVariable Integer id, @PathVariable String usernameSubmitter) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = institusiService.deleteInstitusi(id, usernameSubmitter);
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
