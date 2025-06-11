package com.example.demo1.domain.export;

import com.example.demo1.domain.Planta;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;

public class DocExport implements ExportFormat {

    @Override
    public String getContentType() {
        return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    }

    @Override
    public String getFileExtension() {
        return ".docx";
    }

    @Override
    public byte[] export(List<?> data) {
        if (data == null || data.isEmpty()) return new byte[0];

        try (XWPFDocument document = new XWPFDocument(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Export Date Plante");
            titleRun.setBold(true);
            titleRun.setFontSize(16);

            XWPFTable table = document.createTable();

            Object first = data.get(0);
            Field[] fields = first.getClass().getDeclaredFields();

            XWPFTableRow header = table.getRow(0);
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (i == 0) {
                    header.getCell(0).setText(fields[0].getName());
                } else {
                    header.addNewTableCell().setText(fields[i].getName());
                }
            }

            for (Object obj : data) {
                XWPFTableRow row = table.createRow();
                for (int i = 0; i < fields.length; i++) {
                    Object val = fields[i].get(obj);
                    row.getCell(i).setText(val != null ? val.toString() : "");
                }
            }

            document.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Eroare la export DOC", e);
        }
    }

}