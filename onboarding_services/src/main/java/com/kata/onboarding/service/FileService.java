package com.kata.onboarding.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;


@Slf4j
@Service @RequiredArgsConstructor
public class FileService {

    private final TemplateEngine templateEngine;


    public byte[] generate(String template, Map<String, Object> data){
        log.info("Iniciar generación de documento...");

        Context context = new Context();
        context.setVariables(data);

        String processedTemplate = templateEngine.process(template, context);

        try(ByteArrayOutputStream os = new ByteArrayOutputStream()){
            new PdfRendererBuilder()
                    .useFastMode()
                    .withHtmlContent(processedTemplate, null)
                    .toStream(os)
                    .run();
            return os.toByteArray();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }



    }
}
