package com.sgbd.bankingSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="Accounts")
@Getter 
@Setter
public class BankAccount{
    private String ownerName;
    private double amount;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    protected BankAccount(){}

    public BankAccount(String ownerName, double amount){
        this.amount = amount;
        this.ownerName = ownerName;
    }

    @Override
    public String toString(){
        return """
                {
                    Owner: %s,
                    amount: %d
                }
                """.formatted(ownerName, this.getAmount());
    }
}