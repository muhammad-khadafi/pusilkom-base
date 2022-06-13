package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.dto.TipeInstitusiDTO;
import com.pusilkom.base.service.TipeInstitusiService;
import com.pusilkom.base.dto.MenuGroup;
import com.pusilkom.base.dto.Response;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.TipeInstitusi;
import com.pusilkom.base.service.SampleService;
import com.pusilkom.base.util.DownloadUtil;
import com.pusilkom.base.util.UploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sumiati Hutagalung 01.09.2020
 */

@RestController
public class TipeInstitusiController {

    @Autowired
    TipeInstitusiService tipeInstitusiService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    /*@RequestMapping(value = "/role", method = RequestMethod.GET)*/
    @GetMapping(value="/tipe-institusi")
    public ResponseEntity getTipeInstitusi(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<TipeInstitusi> tipeInstitusiList = tipeInstitusiService.getTipeInstitusiList();
            responseEntity = ResponseEntity.ok(tipeInstitusiList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/tipe-institusi", method = RequestMethod.POST)
    public ResponseEntity postTipeInstitusi(@RequestBody TipeInstitusiDTO tipeInstitusiDTO) {
        ResponseEntity responseEntity = null;
        try {
            tipeInstitusiService.saveTipeInstitusi(tipeInstitusiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/tipe-institusi", method = RequestMethod.PUT)
    public ResponseEntity updateTipeInstitusi(@RequestBody TipeInstitusiDTO tipeInstitusiDTO) {
        ResponseEntity responseEntity = null;

        try {
            tipeInstitusiService.updateTipeInstitusi(tipeInstitusiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/tipe-institusi/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTipeInstitusi(@PathVariable Long id,@PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = tipeInstitusiService.deleteTipeInstitusi(id,username);
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
