package com.sgbd.bankingSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.sgbd.bankingSystem.dbrepos.BankRepo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.sgbd.bankingSystem.model.BankAccount;
import com.sgbd.bankingSystem.services.MultiDbService;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class BankAccuntCtrl {


    
    // @Autowired
    // private BankRepo dbrepo;
    @Autowired
    private MultiDbService dbService;

    // @GetMapping("/BankAccount")
    // public List<BankAccount> getBankAccounts() {
    //     return this.dbrepo.findAll();
    // }

    @GetMapping("/BankAccount/{id}")
    public List<BankAccount> getBankAccount(@PathVariable int id){
        if(id == 1)
            return this.dbService.getAllFrom1();
        else if(id == 2)
            return this.dbService.getAllFrom2();
        else if(id == 3)
            return this.dbService.getAllFrom3();
        else if(id == 4)
            return this.dbService.getAllFrom4();
        return new ArrayList<>();
    }

    // @PostMapping("/register")
    // public ResponseEntity<Void> createBankAccount(@RequestBody Map<String, Object> body ) {
    //     double amount = (double)body.get("amount");
    //     String owner = (String)body.get("ownerName");
    //     BankAccount bacc = new BankAccount(owner, amount);
    //     this.dbrepo.save(bacc);
    //     return ResponseEntity.noContent().build();
    // }

    // @Transactional
    // @PutMapping("BankAccount/{id}")
    // public ResponseEntity<Void> updateAmount(@PathVariable int id, @RequestBody Map<String, Double> body) {
    //     this.dbrepo.updateAmount(id, body.get("amount"));
    //     return ResponseEntity.noContent().build();
    // }

    // @Transactional
    // @DeleteMapping("/BankAccount/{id}")
    // public ResponseEntity<Void> deleteBankAccount(@PathVariable int id){
    //     this.dbrepo.delete(id);
    //     return ResponseEntity.noContent().build();
    // }
    
    

}
