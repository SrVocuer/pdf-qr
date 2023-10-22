package com.devsk.PDF.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PapeletaDTO {

    private String folioCarga;

    private String fecha;

    private String folioFiscal;

    private String qrSAT;

    private String QrDetalle;
}
