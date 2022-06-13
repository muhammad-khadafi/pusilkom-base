package com.pusilkom.base.controller;

import com.pusilkom.base.dto.LaporanDTO;
import com.pusilkom.base.service.LaporanService;
import com.pusilkom.base.dto.Response;
import com.pusilkom.base.helper.ResponseEntityHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@RestController
public class LaporanController {

    @Autowired
    LaporanService laporanService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    // get laporan kerjasama per tipe kerjasama
    @RequestMapping(value = "/laporan/perTipeKerjasama", method = RequestMethod.GET)
    public ResponseEntity getLaporanPerTipeKerjasama(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<LaporanDTO> listLaporan = laporanService.getLaporanPerTipeKerjasama();
            responseEntity = ResponseEntity.ok(listLaporan);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // get laporan kerjasama per tipe institusi
    @RequestMapping(value = "/laporan/perTipeInstitusi", method = RequestMethod.GET)
    public ResponseEntity getLaporanPerTipeInstitusi(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<LaporanDTO> listLaporan = laporanService.getLaporanPerTipeInstitusi();
            responseEntity = ResponseEntity.ok(listLaporan);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }


    // get laporan kerjasama per  negara
    @RequestMapping(value = "/laporan/perNegara", method = RequestMethod.GET)
    public ResponseEntity getLaporanPerNegara(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<LaporanDTO> listLaporan = laporanService.getLaporanPerNegara();
            responseEntity = ResponseEntity.ok(listLaporan);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }
    // get laporan kerjasama per range nilai
    @RequestMapping(value = "/laporan/perRangeNilai", method = RequestMethod.GET)
    public ResponseEntity getLaporanPerRangeNilai(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<LaporanDTO> listLaporan = laporanService.getLaporanPerRange();
            responseEntity = ResponseEntity.ok(listLaporan);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }
}
