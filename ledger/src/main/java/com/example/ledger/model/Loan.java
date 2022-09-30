package com.example.ledger.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Loan {
    private Person person;
    private Integer amountTaken;
    private Double amountToReturn;
    private Double emiAmount;
    private Integer tenure;
    private Integer interest;
    private Map<Integer, Integer> monthTolumpsumAmountMap;

    public void recordLumpsum(Integer amount, Integer month){
        int value = monthTolumpsumAmountMap.getOrDefault(month, 0) + amount;
        monthTolumpsumAmountMap.put(month, value);
    }

}
