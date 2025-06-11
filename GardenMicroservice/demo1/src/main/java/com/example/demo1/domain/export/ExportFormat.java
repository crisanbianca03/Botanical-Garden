package com.example.demo1.domain.export;

import java.util.List;

public interface ExportFormat {
    byte[] export(List<?> data);
    String getContentType();
    String getFileExtension();

}