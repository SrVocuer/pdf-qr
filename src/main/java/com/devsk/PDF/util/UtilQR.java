package com.devsk.PDF.util;

import com.devsk.PDF.dto.request.PapeletaDTO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;

public class UtilQR {

    public static String renderPDF(final PapeletaDTO papeletaDTO) throws IOException {

        final String html = getTemplate(papeletaDTO);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, "/");
        builder.useDefaultPageSize(80, 3000, null);
        builder.toStream(outputStream);
        builder.run();
        outputStream.flush();
        outputStream.close();
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    public static String qrGenerator(final String url) throws IOException, WriterException {

        final int width = 400;
        final int height = 430;
        final EnumMap<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        byte[] byteArray = outputStream.toByteArray();

        return Base64.getEncoder().encodeToString(byteArray);

    }
    private static String getTemplate(PapeletaDTO datos) {

        String html = """
                <!DOCTYPE html>
                <html lang="es">
                                
                <head>
                    <meta charset="UTF-8"/>
                    <title>QRS</title>
                    <style>
                       * {
                            font-size: 12px;
                            font-family: Helvetica, sans-serif;
                        }
                                
                        body {
                            margin: 0;
                            padding: 0;
                        }
                                
                        .contenedor {
                            text-align: center;
                        }
                                
                        .row {
                            text-align: center;
                            width: 100%;
                            
                        }
                                
                        .title {
                            text-align: center;
                            margin-top: 10px;
                            margin-bottom: 10px;
                            margin-left: -5px;
                        }
                                
                        .content-text {
                            text-align: left;
                            width: 100%;
                            margin-bottom: 5px;
                        }
                                
                        .dotted {
                            border-top: 2px dotted #BEBEBD;
                            width: 100%;
                            margin-top: 10px;
                            margin-bottom: 5px;
                        }
                                
                        .qr {
                            width: 130px;
                            height: 150px;
                            margin-left: -5px;
                        }
                                
                        .footer {
                            margin-top: 20px;
                        }
                    </style>
                </head>
                                
                <body>
                                
                    <div class="contenedor">
                                
                        <div class="row">
                            <b class="title">Timbrado de carga</b>
                            <div class="content-text">
                                <p>Se generó el siguiente CFDI el cual ampara la mercancía:</p>
                                <b>Folio: </b> <span>[[QR.FOLIO]]</span>
                            </div>
                                
                            <div class="content-text">
                                <b>Fecha de Timbrado:</b> <span>[[QR.FECHA]]</span>
                            </div>
                            <div class="content-text">
                                <b>Folio Fiscal:</b> <span>[[QR.FOLIOFISCAL]]</span>
                            </div>
                        </div>
                                
                        <div class="dotted"></div>
                        <div class="row">
                            <b class="title">Código QR del SAT</b>
                            <img class="qr" src='data:image/png;base64,[[QR.QRSAT]]' alt="QR Timbrado"/>
                        </div>
                                
                        <div class="dotted"></div>
                                
                        <div class="row">
                            <b class="title">Detalle del Timbrado</b>
                            <img class="qr" src='data:image/png;base64,[[QR.QRTIMBRADO]]' alt="QR Timbrado"/>
                                
                        </div>
                        <div class="footer">.</div>
                    </div>
                </body>
                </html>
                """;

        html = html.replace("[[QR.FOLIO]]", datos.getFolioCarga());
        html = html.replace("[[QR.FECHA]]", datos.getFecha());
        html = html.replace("[[QR.FOLIOFISCAL]]", datos.getFolioFiscal());
        html = html.replace("[[QR.QRSAT]]", datos.getQrSAT());
        html = html.replace("[[QR.QRTIMBRADO]]", datos.getQrDetalle());

        return html;
    }
}
