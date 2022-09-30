package com.example.ledger;

import com.example.ledger.service.LedgerCo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@SpringBootApplication
@Slf4j
public class LedgerApplication implements CommandLineRunner {

    private LedgerCo ledgerCo = new LedgerCo();

    public static void main(String[] args) {
        SpringApplication.run(LedgerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try{
            File file = ResourceUtils.getFile("classpath:Input_2.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while( (line=reader.readLine())!=null ){
                final String[] query = line.toString().split(" ");

                switch (query[0]){
                    case "LOAN":
                        ledgerCo.createLoan(query[1],query[2],Integer.parseInt(query[3]),Integer.parseInt(query[4]),Integer.parseInt(query[5]));
                        break;
                    case "PAYMENT":
                        ledgerCo.doPayment(query[1],query[2],Integer.parseInt(query[3]),Integer.parseInt(query[4]));
                        break;
                    case "BALANCE":
                        ledgerCo.checkBalance(query[1],query[2],Integer.parseInt(query[3]));
                        break;
                }

            }

        } catch (Exception e){
            log.error(e.getMessage());
        }

    }
}
