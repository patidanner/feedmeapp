package com.inbound.text.feedMe.service;

import com.inbound.text.feedMe.model.Staging;
import com.inbound.text.feedMe.repository.StagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

@Service
public class UploadService {

    private static String UPLOADED_DIR = "temp/";
    public static final String CPF = "cpf";
    public static final String PRIVATE = "private";
    public static final String INCOMPLETO = "incompleto";
    public static final String DATA_ULTIMA_COMPRA = "dataUltimaCompra";
    public static final String TICKET_MEDIO = "ticketMedio";
    public static final String TICKET_ULTIMA_COMPRA = "ticketUltimaCompra";
    public static final String LOJA_MAIS_FREQUENTE = "lojaMaisFrequente";
    public static final String LOJA_ULTIMA_COMPRA = "lojaUltimaCompra";

    @Autowired
    private StagingRepository stagingRepository;
    @Autowired
    private StagingServiceUtils serviceUtils;

    @Transactional
    public Boolean processFile(MultipartFile file) throws IOException, ParseException{
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_DIR + file.getOriginalFilename());
        Files.write(path, bytes);
        this.processEntriesFromFile(file.getOriginalFilename());
        return Boolean.TRUE;
    }

    @Transactional
    public Iterable<Staging> processEntriesFromFile(String fileName) throws IOException, ParseException {
        String fileDir;
        fileDir = getFileDirectory(fileName);
        long t1 = System.currentTimeMillis();
        Scanner scanner = new Scanner(new File(fileDir), StandardCharsets.UTF_8);
        Map<String,String> row = new HashMap<>();
        List<Map<String,String>> rows = new ArrayList<>();
        this.readFileContent(scanner, rows);
        scanner.close();
        List<Staging> stagingList = new ArrayList<>();
        this.stagingPopulator(rows, stagingList);
        long t2 = System.currentTimeMillis();
        Iterable<Staging> databaseResult = this.saveAll(stagingList);
        long delta = t2-t1;
        System.out.println("Demorei "+ delta + " ms para processar o arquivo");
        return databaseResult;
    }

    private void readFileContent(Scanner scanner, List<Map<String, String>> rows) {
        Map<String, String> row;
        scanner.nextLine();
        while(scanner.hasNext()){
            row = new HashMap<>();
            row.put(CPF, scanner.next());
            row.put(PRIVATE, scanner.next());
            row.put(INCOMPLETO, scanner.next());
            row.put(DATA_ULTIMA_COMPRA, scanner.next());
            row.put(TICKET_MEDIO, scanner.next());
            row.put(TICKET_ULTIMA_COMPRA, scanner.next());
            row.put(LOJA_MAIS_FREQUENTE, scanner.next());
            row.put(LOJA_ULTIMA_COMPRA, scanner.next());
            rows.add(row);
        }
    }

    private String getFileDirectory(String fileName) {
        String fileDir;
        if(fileName == null){
            fileDir = "base_teste.txt";
        }else{
            fileDir = UPLOADED_DIR + fileName;
        }
        return fileDir;
    }

    public void stagingPopulator(List<Map<String,String>> rows, List<Staging> stagingList) throws ParseException {
        Staging staging;
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        for (Map<String,String> row :
             rows) {
            staging = new Staging();
            staging.setCpf(row.get(CPF));
            staging.setIsIncompleto(row.get(INCOMPLETO).equalsIgnoreCase("1") ? Boolean.TRUE : Boolean.FALSE);
            staging.setIsPrivate(row.get(PRIVATE).equalsIgnoreCase("1") ? Boolean.TRUE : Boolean.FALSE);
            staging.setLojaMaisFrequente(row.get(LOJA_MAIS_FREQUENTE));
            staging.setTicketUltimaCompra(!row.get(TICKET_ULTIMA_COMPRA).equalsIgnoreCase("null") ? format.parse(row.get(TICKET_ULTIMA_COMPRA)).doubleValue() : null);
            staging.setTicketMedio(!row.get(TICKET_MEDIO).equalsIgnoreCase("null") ? format.parse(row.get(TICKET_MEDIO)).doubleValue() : null);
            staging.setLojaUltimaCompra(row.get(LOJA_ULTIMA_COMPRA));
            staging.setValidCpf(serviceUtils.isCpf(row.get(CPF)));
            staging.setValidLojaMaisFrequente(serviceUtils.isCnpj(row.get(LOJA_MAIS_FREQUENTE)));
            staging.setValidLojaUltimaCompra(serviceUtils.isCnpj(row.get(LOJA_ULTIMA_COMPRA)));
            stagingList.add(staging);
        }
    }

    @Transactional
    public Iterable<Staging> saveAll(List<Staging> stagingList){
        return stagingRepository.saveAll(stagingList);
    }

}
