package com.devsk.PDF.controllers;

import com.devsk.PDF.dto.ApiResponseDTO;
import com.devsk.PDF.dto.Meta;
import com.devsk.PDF.dto.request.PapeletaDTO;
import com.devsk.PDF.dto.response.PdfResponse;
import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static com.devsk.PDF.util.UtilQR.qrGenerator;
import static com.devsk.PDF.util.UtilQR.renderPDF;

@RestController
@RequestMapping("/cargas")
public class FakePdfController {

    @GetMapping("/imprimir")
    public ResponseEntity<ApiResponseDTO<Object>> printPDF (@RequestHeader Map<String, String> headers) {

        final PdfResponse pdf = new PdfResponse();
        try {

            final String qr = qrGenerator("https://cultofthepartyparrot.com/");

            final PapeletaDTO dto = new PapeletaDTO();
            dto.setFolioCarga("40000000001");
            dto.setFecha("22/10/2023");
            dto.setFolioFiscal(UUID.randomUUID().toString());
            dto.setQrSAT(qr);
            dto.setQrDetalle(qr);

            pdf.setPdfPapeleta( renderPDF(dto));

        } catch (IOException |WriterException e) {
            throw new RuntimeException(e);
        }
        final Meta meta = new Meta("OK", 200);
        return new ResponseEntity<>( new ApiResponseDTO<>(meta, pdf), HttpStatus.OK);
    }
}
