package com.example.ledger.service;

import com.example.ledger.model.Bank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LedgerCo {

    private List<Bank> bankList = new ArrayList<>();

    public void createLoan(String bankName, String personName, int loanAmount, int years, int rate){
        boolean bankFoundInList = false;

        for(Bank ibank: bankList){
            if(ibank.getName().equalsIgnoreCase(bankName)){
                bankFoundInList=true;
                ibank.createLoan(personName, loanAmount, years, rate);
                break;
            }
        }

        if (!bankFoundInList) {
            Bank bank = new Bank();
            bank.setName(bankName);
            bank.createLoan(personName, loanAmount, years, rate);
            bankList.add(bank);
        }
    }

    public void checkBalance(String bankName, String personName, int emiNumber){

        for(Bank bank: bankList){
            if(bank.getName().equalsIgnoreCase(bankName)){
                bank.checkBalance(personName,emiNumber);
                break;
            }
        }

    }

    public void doPayment(String bankName, String personName, int lumpsumAmount, int emiNumber){

        for(Bank bank: bankList){
            if(bank.getName().equalsIgnoreCase(bankName)){
                bank.doPayment(personName,lumpsumAmount,emiNumber);
                break;
            }
        }

    }

}
