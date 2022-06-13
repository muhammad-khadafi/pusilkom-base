package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.helper.FileUploadHelper;
import com.pusilkom.base.model.DokumenKerjasama;
import com.pusilkom.base.service.DokumenKerjasamaService;
import com.pusilkom.base.dto.DokumenKerjasamaDTO;

import com.pusilkom.base.util.DownloadUtil;
import com.pusilkom.base.util.UploadUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.pusilkom.base.helper.ResponseEntityHelper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class DokumenKerjasamaController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DokumenKerjasamaService DokumenKerjasamaService;
    @Autowired
    private UploadUtil uploadUtil;
    @Autowired
    DownloadUtil downloadUtil;

    @Value("${dir.upload.dok.kerjasama}") String DIR_UPLOAD_FILE;

    final static String TICKET_NOTVALID_MSG = "Ticket is not valid";
    final static String FILE_NOT_FOUND = "File not found";

    @RequestMapping(value = "/dokumen-kerjasama", method = RequestMethod.GET)
    public ResponseEntity getDokumenKerjasama() {
        ResponseEntity responseEntity = null;

        try {
            List<DokumenKerjasamaDTO> listDokumenKerjasamaDTO = DokumenKerjasamaService.getDokumenKerjasamaDTO();
            responseEntity = ResponseEntity.ok(listDokumenKerjasamaDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-kerjasama/{idKerjasama}", method = RequestMethod.GET)
    public ResponseEntity getDokumenKerjasamaByIdKerjasama(@PathVariable Integer idKerjasama) {
        ResponseEntity responseEntity = null;

        try {
            List<DokumenKerjasamaDTO> listDokumenKerjasamaDTO = DokumenKerjasamaService.getDokumenKerjasamaDTO(idKerjasama);
            responseEntity = ResponseEntity.ok(listDokumenKerjasamaDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/dokumen-kerjasama", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody DokumenKerjasamaDTO DokumenKerjasamaDTO) {
        ResponseEntity responseEntity = null;

        try {
            DokumenKerjasamaService.updateDokumenKerjasama(DokumenKerjasamaDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // add
    @RequestMapping(value = "/dokumen-kerjasama", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody DokumenKerjasamaDTO DokumenKerjasamaDTO) {
        ResponseEntity responseEntity = null;

        try {
            DokumenKerjasamaService.addDokumenKerjasama(DokumenKerjasamaDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-kerjasama-upload", method = RequestMethod.POST)
    public ResponseEntity dokumenOrganisasiUpload(@RequestParam("file") MultipartFile fileUpload) {
        ResponseEntity responseEntity = null;
        try {
            FileUploadHelper fileUploadHelper = uploadUtil.uploadFileCommonService(DIR_UPLOAD_FILE, fileUpload);
            responseEntity = ResponseEntity.ok().body(fileUploadHelper);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-kerjasama-upload/{id}", method = RequestMethod.PUT)
    public ResponseEntity dokumenKerjasamaUpdateFile(@RequestParam("file") MultipartFile fileUpload, @PathVariable Integer id) {
        ResponseEntity responseEntity = null;
        try {
            DokumenKerjasamaService.deleteFile(id);
            FileUploadHelper fileUploadHelper = uploadUtil.uploadFileCommonService(DIR_UPLOAD_FILE, fileUpload);
            responseEntity = ResponseEntity.ok().body(fileUploadHelper);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-kerjasama-delete-file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteDokumenKerjasamaFile(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            DokumenKerjasamaService.deleteFile(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/dokumen-kerjasama/{id}/{usernameSubmitter}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String usernameSubmitter) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = DokumenKerjasamaService.deleteDokumenKerjasama(id, usernameSubmitter);
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

    @RequestMapping(value = "/dokumen-kerjasama/download/{id}", method = RequestMethod.POST)
    public ResponseEntity generateTicketDownload(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            String ticket = downloadUtil.generateTicket("/download/dokumen-kerjasama/" + id);
            responseEntity = ResponseEntity.ok(new DownloadResponse(ticket));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/download/dokumen-kerjasama/{id}", method = RequestMethod.GET)
    public void getDownloadDoc(@PathVariable Integer id, @RequestParam("ticket") String ticket, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            boolean isVerified = downloadUtil.verifyTicket(request, ticket);

            if (!isVerified) {
                response.getOutputStream().write(TICKET_NOTVALID_MSG.getBytes());
                return;
            }

            DokumenKerjasama dokumenKerjasama = DokumenKerjasamaService.getDokumenKerjasamaById(id);

            InputStream is = new FileInputStream(dokumenKerjasama.getFilePath());

            byte[] fileBytes = IOUtils.toByteArray(is);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + dokumenKerjasama.getNamaFile());

            response.getOutputStream().write(fileBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            response.getOutputStream().write(FILE_NOT_FOUND.getBytes());
            return;
        }
    }

    @RequestMapping(value = "/dokumen-kerjasama-file/{id}", method = RequestMethod.GET)
    public ResponseEntity getDokumenOrganisasiFile(@PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            String fileEncoded = DokumenKerjasamaService.getFile(id);
            responseEntity = ResponseEntity.ok(fileEncoded);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

}
