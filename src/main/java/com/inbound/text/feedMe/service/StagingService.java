package com.inbound.text.feedMe.service;

import com.inbound.text.feedMe.model.Staging;
import com.inbound.text.feedMe.repository.StagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
public class StagingService {

    @Autowired
    private StagingRepository stagingRepository;

    @Transactional
    public List<Staging> read() throws FileNotFoundException, ParseException {
        String file = "base_teste.txt";

        long t1 = System.currentTimeMillis();
        Scanner scanner = new Scanner(new File(file));

        List<Staging> stagingList = new ArrayList<>();

        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        scanner.nextLine();
        while(scanner.hasNext()){
            Staging staging = new Staging();
            staging.setCpf(scanner.next());
            staging.setEstaPrivate(Boolean.valueOf(scanner.next()));
            staging.setEstaIncompleto(Boolean.valueOf(scanner.next()));
            staging.setDataUltimaCompra(scanner.next());
            String next = scanner.next();
            staging.setTicketMedio(!next.equalsIgnoreCase("null") ? format.parse(next).doubleValue() : null);
            next = scanner.next();
            staging.setTicketUltimaCompra(!next.equalsIgnoreCase("null") ? format.parse(next).doubleValue() : null);
            staging.setLojaMaisFrequente(scanner.next());
            staging.setLojaUltimaCompra(scanner.next());

            stagingList.add(staging);
            //this.saveToDatabase(staging);
        }
        scanner.close();
        long t2 = System.currentTimeMillis();
        Iterable<Staging> stagingSaveAll = this.saveAllToDatabase(stagingList);
        long delta = t2-t1;
        System.out.println("Demorei "+ delta + " ms para processar o arquivo");
        return stagingList;
    }

    @Transactional
    public Staging saveToDatabase(Staging stagingData){
        return stagingRepository.save(stagingData);
    }

    @Transactional
    public Iterable<Staging> saveAllToDatabase(List<Staging> stagingList){
        return stagingRepository.saveAll(stagingList);
    }
}
