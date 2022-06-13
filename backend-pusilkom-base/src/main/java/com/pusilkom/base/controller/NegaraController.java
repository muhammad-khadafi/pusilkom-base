package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.dto.NegaraDTO;
import com.pusilkom.base.dto.MenuGroup;
import com.pusilkom.base.dto.Response;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.Role;
import com.pusilkom.base.model.Negara;
import com.pusilkom.base.service.RoleService;
import com.pusilkom.base.service.NegaraService;
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
 * Created by Sumiati Hutagalung
 */

@RestController
public class NegaraController {
    @Autowired
    NegaraService negaraService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    /*@RequestMapping(value = "/role", method = RequestMethod.GET)*/
    @GetMapping(value="/negara")
    public ResponseEntity getRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<Negara> negaraList = negaraService.getNegaraList();
            responseEntity = ResponseEntity.ok(negaraList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/negara", method = RequestMethod.POST)
    public ResponseEntity postNegara(@RequestBody NegaraDTO negara) {
        ResponseEntity responseEntity = null;
        try {
            negaraService.saveNegara(negara);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/negara", method = RequestMethod.PUT)
    public ResponseEntity updateNegara(@RequestBody NegaraDTO negara) {
        ResponseEntity responseEntity = null;

        try {
            negaraService.updateNegara(negara);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/negara/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteNegara(@PathVariable Long id,@PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = negaraService.deleteNegara(id,username);
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
