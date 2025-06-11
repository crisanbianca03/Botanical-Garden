package com.example.demo1.domain.export;

import java.util.List;

public class ExportService {

    private final ExportFormat format;

    public ExportService(ExportFormat format) {
        this.format = format;
    }

    public byte[] exportData(List<?> data) {
        return format.export(data);
    }


}

