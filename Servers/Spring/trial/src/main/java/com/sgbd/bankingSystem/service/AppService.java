package com.sgbd.bankingSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgbd.bankingSystem.model.BankAccount;
import com.sgbd.bankingSystem.repos.repoFR.RepoFR;
import com.sgbd.bankingSystem.repos.repoGE.RepoGE;
import com.sgbd.bankingSystem.repos.repoID.RepoID;
import com.sgbd.bankingSystem.repos.repoRU.RepoRU;

import jakarta.transaction.Transactional;

@Service
public class AppService {
    

    @Autowired
    private RepoGE repge;
    @Autowired
    private RepoFR repfr;
    @Autowired
    private RepoID repoid;
    @Autowired
    private RepoRU reporu;
    
    
    /*
     * GET ALL METHODS
     */
    public List<BankAccount> getAllAccounts(String country){
        List<BankAccount> accounts;
        switch(country){
            case "GE": {
                accounts = this.repge.findAll();
                break;
            }
            case "FR":{
                accounts = this.repfr.findAll();
                break;

            }
            case "RU": {
                accounts = this.reporu.findAll();
                break;
            }
            case "ID": {
                accounts = this.repoid.findAll();
                break;
            }
            default: {
                accounts = new ArrayList<>();
            }
        }
        return accounts;
    }
    // public List<BankAccount> getAllAccountsGE(){
    //     return repge.findAll();
    // } 

    // public List<BankAccount> getAllAccountsFR(){
    //     return repfr.findAll();
    // }

    // public List<BankAccount> getAllAccountsID(){
    //     return repoid.findAll();
    // }

    // public List<BankAccount> getAllAccountsRU(){
    //     return reporu.findAll();
    // }
    
    /*
     * GET BY ID 
     */
    public Optional<BankAccount> getAccount(String country, int id){
        Optional<BankAccount> bank;
        switch(country){
            case "GE": {
                bank = this.repge.findById(id);
                break;
            }
            case "FR":{
                bank = this.repfr.findById(id);
                break;
            }
            case "RU": {
                bank = this.reporu.findById(id);
                break;
            }
            case "ID": {
                bank = this.repoid.findById(id);
                break;
            }
            default: {
                bank = null;
            }
        }
        return bank;
    }

    /*
     * UPDATE METHOD
     */
    public void update(String country, int id ,double amount){
        Optional<BankAccount> account;
        try{
            account = this.getAccount(country, id);
            account.get().setAmount(amount);
            switch (country) {
                case "GE":{
                    repge.save(account.get());
                    break;
                }
                case "FR":{
                    repfr.save(account.get());
                    break;
                }
                case "ID":{
                    repoid.save(account.get());
                    break;
                }
                case "RU": {
                    reporu.save(account.get());
                    break;
                }
            }
        }catch(Exception exp){
            System.out.println(exp);
        }
    }

    /*
     * DELETE METHOD
     */
    public void delete(String country, int id){
        switch (country) {
            case "GE":{
                repge.deleteById(id);
                break;
            }
            case "FR":{
                repfr.deleteById(id);
                break;
            }
            case "ID":{
                repoid.deleteById(id);
                break;
            }
            case "RU": {
                reporu.deleteById(id);
                break;
            }
        }
    }

    /*
     * SAVE METHOD
     */
    public void save(String country, BankAccount account){
        switch (country) {
            case "GE":{
                repge.save(account);
                break;
            }
            case "FR":{
                repfr.save(account);
                break;
            }
            case "ID":{
                repoid.save(account);
                break;
            }
            case "RU": {
                reporu.save(account);
                break;
            }
        }
    }

    /*
     * Concurrent Issues
     */
    @Transactional
    public void uncommitedUpdate(String country, int id, double amount){
        try{
            BankAccount account = this.getAccount(country, id).get();
            this.save(country, account);
            Thread.sleep(5000);
            throw new RuntimeException("Triggers automatically the rollback of the transaction");

        }catch(InterruptedException ex){
            System.out.println(ex);
        }
        
    }

    @Transactional
    public void uncommitedDoubleAmount(String countryCode, int id, boolean rollback){
        try{
            BankAccount account = this.getAccount(countryCode, id).get();
            account.setAmount(account.getAmount() * 2);
            this.save(countryCode, account);
            if(rollback)
                Thread.sleep(10000);
            throw new RuntimeException("Triggers automatically the rollback for the transaction");
    
        }catch(InterruptedException exp){
            System.out.println(exp);
        }
    }

    @Transactional
    public boolean uncommitedAuthPersona(String countryCode, int id){
        try{
            BankAccount account = this.getAccount(countryCode, id).get();
            String owner = account.getOwnerName();
            // Waiting for another transaction to execute and check the name of the owner of that to this one
            Thread.sleep(5000);
            if(!owner.equals(this.getAccount(countryCode, id).get().getOwnerName())){
                return false;
            }
            return true;
        }catch(InterruptedException ex){
            System.out.println(ex);
        }
        return false;
    }


    @Transactional 
    public void changeName(String countryCode, int id, String newName){
        BankAccount account = this.getAccount(countryCode, id).get();
        account.setOwnerName(newName);
        this.save(countryCode, account);
    }


    @Transactional
    public double lostChange(String countryCode, int id, boolean sleep){
        try{
            BankAccount account = this.getAccount(countryCode, id).get();
            account.setAmount(account.getAmount() + 100.00);
            if(sleep)
                Thread.sleep(1000);
            this.save(countryCode, account);
            return account.getAmount();
            
        }catch(InterruptedException ex){
            System.out.println(ex);
        }
        return 0.0;
    }

    @Transactional 
    public int logAccountsNumber(String countryCode){
        return this.getAllAccounts(countryCode).size();
    }

}
