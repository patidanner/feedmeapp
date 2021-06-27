package com.inbound.text.feedMe.service;

import com.inbound.text.feedMe.model.Consumidor;
import com.inbound.text.feedMe.model.Loja;
import com.inbound.text.feedMe.model.Perfil;
import com.inbound.text.feedMe.model.Staging;
import com.inbound.text.feedMe.repository.ConsumidorRepository;
import com.inbound.text.feedMe.repository.LojaRepository;
import com.inbound.text.feedMe.repository.PerfilRespository;
import com.inbound.text.feedMe.repository.StagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

@Service
public class StagingService {

    public static final String CPF = "cpf";
    public static final String PRIVATE = "private";
    public static final String INCOMPLETO = "incompleto";
    public static final String DATA_ULTIMA_COMPRA = "dataUltimaCompra";
    public static final String TICKET_MEDIO = "ticketMedio";
    public static final String TICKET_ULTIMA_COMPRA = "ticketUltimaCompra";
    public static final String LOJA_MAIS_FREQUENTE = "lojaMaisFrequente";
    public static final String LOJA_ULTIMA_COMPRA = "lojaUltimaCompra";
    @Autowired
    private PerfilRespository perfilRespository;

    @Autowired
    private StagingServiceUtils serviceUtils;

    @Autowired
    private StagingRepository stagingRepository;

    @Autowired
    private ConsumidorRepository consumidorRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Transactional
    public Iterable<Staging> readAll() throws IOException, ParseException {
        String file = "base_teste.txt";

        long t1 = System.currentTimeMillis();
        Scanner scanner = new Scanner(new File(file), StandardCharsets.UTF_8);
        Map<String,String> row = new HashMap<>();
        List<Map<String,String>> rows = new ArrayList<>();
        scanner.nextLine();
        while(scanner.hasNext()){
            row = new HashMap<>();
            row.put(CPF,scanner.next());
            row.put(PRIVATE, scanner.next());
            row.put(INCOMPLETO,scanner.next());
            row.put(DATA_ULTIMA_COMPRA,scanner.next());
            row.put(TICKET_MEDIO,scanner.next());
            row.put(TICKET_ULTIMA_COMPRA,scanner.next());
            row.put(LOJA_MAIS_FREQUENTE, scanner.next());
            row.put(LOJA_ULTIMA_COMPRA, scanner.next());
            rows.add(row);
        }
        scanner.close();
        List<Staging> stagingList = new ArrayList<>();
        this.stagingPopulator(rows, stagingList);
        long t2 = System.currentTimeMillis();
        Iterable<Staging> stagingSaveAll = this.saveAllStagingToDatabase(stagingList);
        long delta = t2-t1;
        System.out.println("Demorei "+ delta + " ms para processar o arquivo");
        return stagingSaveAll;
    }

    public void stagingPopulator(List<Map<String,String>> rows, List<Staging> stagingList) throws ParseException {
        Staging staging;
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        for (Map<String,String> row :
             rows) {
            staging = new Staging();
            staging.setCpf(row.get(CPF));
            staging.setEstaIncompleto(row.get(INCOMPLETO).equalsIgnoreCase("1") ? Boolean.TRUE : Boolean.FALSE);
            staging.setEstaPrivate(row.get(PRIVATE).equalsIgnoreCase("1") ? Boolean.TRUE : Boolean.FALSE);
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
    public Staging saveToDatabase(Staging stagingData){
        return stagingRepository.save(stagingData);
    }

    @Transactional
    public Iterable<Perfil> saveAllPerfil(List<Perfil> perfilList){
        return perfilRespository.saveAll(perfilList);
    }
    @Transactional
    public Iterable<Consumidor> saveAllConsumidor(List<Consumidor> consumidorList){
        return consumidorRepository.saveAll(consumidorList);
    }
    @Transactional
    public Iterable<Loja> saveAllLoja(List<Loja> lojaList){
        return lojaRepository.saveAll(lojaList);
    }

    @Transactional
    public Iterable<Staging> saveAllStagingToDatabase(List<Staging> stagingList){
        return stagingRepository.saveAll(stagingList);
    }

}
