package com.sgbd.bankingSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@Entity
@Table(name="Accounts")
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount{
    private String ownerName;
    private double amount;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public BankAccount(String owner, double amount){
        this.amount = amount;
        this.ownerName = owner;
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