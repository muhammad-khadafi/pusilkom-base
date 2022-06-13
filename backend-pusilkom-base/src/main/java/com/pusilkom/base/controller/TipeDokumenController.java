package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.dto.TipeDokumenDTO;
import com.pusilkom.base.dto.MenuGroup;
import com.pusilkom.base.dto.Response;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.TipeDokumen;
import com.pusilkom.base.service.TipeDokumenService;
import com.pusilkom.base.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@RestController
public class TipeDokumenController {

    @Autowired
    TipeDokumenService tipeDokumenService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    // get all
    @RequestMapping(value = "/tipe-dokumen", method = RequestMethod.GET)
    public ResponseEntity getTipeDokumen(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<TipeDokumen> tipeDokumenList = tipeDokumenService.getTipeDokumenList();
            responseEntity = ResponseEntity.ok(tipeDokumenList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // get one
    @RequestMapping(value = "/tipe-dokumen/{id}", method = RequestMethod.GET)
    public ResponseEntity getTipeDokumen(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            TipeDokumen tipeDokumen = tipeDokumenService.getTipeDokumenById(id);
            responseEntity = ResponseEntity.ok(tipeDokumen);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // tambah
    @RequestMapping(value = "/tipe-dokumen", method = RequestMethod.POST)
    public ResponseEntity postTipeDokumen(@RequestBody TipeDokumenDTO tipeDokumenDTO) {
        ResponseEntity responseEntity = null;
        try {
            tipeDokumenService.saveTipeDokumen(tipeDokumenDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/tipe-dokumen", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody TipeDokumenDTO tipeDokumenDTO) {
        ResponseEntity responseEntity = null;

        try {
            tipeDokumenService.updateTipeDokumen(tipeDokumenDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/tipe-dokumen/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = tipeDokumenService.deleteTipeDokumen(id, username);
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
