package com.example.demo1.domain.export;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonExport implements ExportFormat {

    @Override
    public byte[] export(List<?> data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            return jsonString.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Eroare la export JSON", e);
        }
    }

    @Override
    public String getContentType() {
        return "application/json";
    }

    @Override
    public String getFileExtension() {
        return ".json";
    }
}