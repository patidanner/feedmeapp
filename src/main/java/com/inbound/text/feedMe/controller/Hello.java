package com.inbound.text.feedMe.controller;

import com.inbound.text.feedMe.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.ContentTypeUtils;

import java.io.IOException;
import java.text.ParseException;

@Controller
public class Hello {

    public static final String BASE_TEST_TXT = "base_test.txt";
    @Autowired
    private UploadService uploadService;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/feedmeexample")
    public String  readAllFromDataExample(RedirectAttributes redirectAttributes) throws IOException, ParseException {
        redirectAttributes.addFlashAttribute("message",
                "Arquivo processado com sucesso!" +
                        " Experimente ir até http://localhost:5050/ verificar o conteúdo da tabela staging.");

        uploadService.processEntriesFromFile(null);
        return "redirect:/uploadStatus";
    }

    @PostMapping("/feedme")
    public String readAllFromUploadedFile(@RequestParam("file") MultipartFile file,
                                                     RedirectAttributes redirectAttributes) throws IOException, ParseException {
        String error = isValidFile(file, redirectAttributes);
        if (error != null){
            return error;
        }
        try{
            uploadService.processFile(file);
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("message", "Não consigo ler o conteúdo deste arquivo :( Por favor verifique o conteúdo dele.");
            return "redirect:uploadStatus";
        }
        redirectAttributes.addFlashAttribute("message",
                "Arquivo processado com sucesso!" +
                        " Experimente ir até http://localhost:5050/ verificar o conteúdo da tabela staging.");

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";

    }

    private String isValidFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Arquivo vazio!");
            return "redirect:uploadStatus";
        }
        if (!ContentTypeUtils.isContentTypeText(file.getContentType())){
            redirectAttributes.addFlashAttribute("message", "Por enquanto só posso processar aquivos .txt! Por favor, escolha outro um arquivo do tipo texto.");
            return "redirect:uploadStatus";
        }
        return null;
    }
}
