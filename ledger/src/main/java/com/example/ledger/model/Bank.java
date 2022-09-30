package com.example.ledger.model;

import lombok.Data;

import java.util.*;

@Data
public class Bank {

    private String name;
    private List<Loan> loanList = new ArrayList<>();

    public void createLoan(String personName, int loanAmount, int years, int rate){
        Person person = Person.builder()
                .name(personName)
                .build();

        double amountToReturn = Math.ceil(((loanAmount*years*rate)/(double)100)+(double)loanAmount);
        double emiAmount = Math.ceil(amountToReturn / (12*years));

        Loan loan = Loan.builder()
                .amountTaken(loanAmount)
                .amountToReturn(amountToReturn)
                .emiAmount(emiAmount)
                .interest(rate)
                .tenure(years)
                .person(person)
                .monthTolumpsumAmountMap(new HashMap<>())
                .build();
        loanList.add(loan);
    }

    public void checkBalance(String personName, int emiNumber){

        for(Loan loan: loanList){
            if(loan.getPerson().getName().equalsIgnoreCase(personName)){

                // calculate total lumpsum paid
                Map<Integer, Integer> monthTolumpsumAmountMap = loan.getMonthTolumpsumAmountMap();
                int totalLumpsumPaid = 0;
                for(Map.Entry<Integer,Integer> lumpsumInvestment : monthTolumpsumAmountMap.entrySet()){
                    if(lumpsumInvestment.getKey()<=emiNumber){
                        totalLumpsumPaid+=lumpsumInvestment.getValue();
                    }
                }

                // calculate amount paid
                double totalAmountPaid = totalLumpsumPaid + loan.getEmiAmount()*emiNumber;

                // calculate tenure left
                double tenureLeft = Math.ceil((loan.getAmountToReturn() - totalAmountPaid)/loan.getEmiAmount());

                System.out.println(this.name+" "+loan.getPerson().getName()+" "+(int)totalAmountPaid+" "+(int)tenureLeft);

                break;
            }
        }

    }

    public void doPayment(String personName, int lumpsumAmount, int emiNumber){
        for(Loan loan: loanList){
            if(loan.getPerson().getName().equalsIgnoreCase(personName)){
                loan.recordLumpsum(lumpsumAmount,emiNumber);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof String)) return false;
        return getName().equals((String)o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
