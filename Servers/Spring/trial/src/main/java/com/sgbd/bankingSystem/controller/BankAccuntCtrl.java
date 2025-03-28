package com.sgbd.bankingSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.sgbd.bankingSystem.model.BankAccount;
import com.sgbd.bankingSystem.service.AppService;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class BankAccuntCtrl {
    
    @Autowired
    private AppService service;

    List<BankAccount> accountsGe;
    
    Thread getAllTupleGE = new Thread(()-> {
        accountsGe = service.getAllAccounts("GE");
    });
    
    @GetMapping("/BankSystemGE/accounts")
    public List<BankAccount> getAllAccountsGE(){
        try{getAllTupleGE.start();
            
            getAllTupleGE.join();
            return accountsGe;
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return new ArrayList<>();
    } 

    @GetMapping("/BankSystemFR/accounts")
    public List<BankAccount> getAllAccountsFR(){
        return service.getAllAccounts("FR");
    } 

    @GetMapping("/BankSystemID/accounts")
    public List<BankAccount> getAllAccountsID(){
        return service.getAllAccounts("ID");
    } 

    @GetMapping("/BankSystemRU/accounts")
    public List<BankAccount> getAllAccountsRU(){
        return service.getAllAccounts("RU");
    } 

    
    
    @PostMapping("/register")
    public ResponseEntity<Void> createBankAccount(@RequestBody Map<String, Object> body ) {
        double amount = (double)body.get("amount");
        String owner = (String)body.get("ownerName");
        String country = (String)body.get("country");
        BankAccount bacc = new BankAccount(owner, amount);
        this.service.save(country, bacc);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("BankAccount/{country_code}/{id}")
    public ResponseEntity<Void> updateAmount(@PathVariable int id,@PathVariable String country_code ,@RequestBody Map<String, Double> body) {
        double amount = (double) body.get("amount");
        this.service.update(country_code, id, amount);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/BankAccount/{country_code}/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable int id, @PathVariable String country_code){
        this.service.delete(country_code, id);
        return ResponseEntity.noContent().build();
    }


    
}
