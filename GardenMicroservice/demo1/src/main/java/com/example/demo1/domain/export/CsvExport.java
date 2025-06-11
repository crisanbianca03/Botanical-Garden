package com.example.demo1.domain.export;
import com.example.demo1.domain.Planta;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class CsvExport implements ExportFormat {

    @Override
    public byte[] export(List<?> data) {
        if (data.isEmpty()) return new byte[0];

        StringBuilder builder = new StringBuilder();
        Object first = data.get(0);
        Field[] fields = first.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            builder.append(field.getName()).append(",");
        }
        builder.deleteCharAt(builder.length() - 1).append("\n");

        for (Object obj : data) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    builder.append(value != null ? value.toString() : "").append(",");
                } catch (IllegalAccessException e) {
                    builder.append(",");
                }
            }
            builder.deleteCharAt(builder.length() - 1).append("\n");
        }

        return builder.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String getContentType() {
        return "text/csv";
    }

    @Override
    public String getFileExtension() {
        return ".csv";
    }
}

