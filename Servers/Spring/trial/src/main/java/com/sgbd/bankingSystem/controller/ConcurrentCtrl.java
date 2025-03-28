package com.sgbd.bankingSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgbd.bankingSystem.model.BankAccount;
import com.sgbd.bankingSystem.service.AppService;

@RestController
public class ConcurrentCtrl {

    @Autowired
    private AppService service;
    
    
    /**
     * Dirty Read Controller that are meant to mock a Dirty Read scenario
     * By having 2 users that execute a Get/Post Request simultaneously 
     * The Call of the Get HTTP request is delayed ensures that the update one is executed first
     * , yet the update transaction as specified in the Service will not finish successfuly do to the 
     * throw statement at the end which - by the rules of the JPA - will force a automatical rollback
     * by this time though the Get request will have been triggered.
     * 
     */
    @PutMapping("/concurrent/dirtyread")
    public void put(@RequestBody Map<String, Object> json){
        String country = (String)json.get("country_code");
        double amount = (double)json.get("amount");
        int id = (int)json.get("id");
        Thread t = new Thread(() -> {
            service.uncommitedUpdate(country, id, amount);
        });
        t.start();
    }

    List<BankAccount> accounts;
    BankAccount account;

    @GetMapping("/concurrent/dirtyread")
    public BankAccount get(@RequestBody Map<String, Object> json){
        String country = (String)json.get("country_code");
        int id = (int)json.get("id");
        Thread t = new Thread(() -> {
            try{
                Thread.sleep(1500);
                account = service.getAccount(country, id).get();
            }catch(InterruptedException ex){
                System.out.println(ex);
            }
        });
        try{
            t.start();
            t.join();
            return account;
        }catch(InterruptedException ex){
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Dirty Write
     */
    @PutMapping("/concurrent/dirtywrite")
    public void putw(@RequestBody Map<String, Object> json){
        String country = (String)json.get("country_code");
        int id = (int)json.get("id");
        boolean rollback = (boolean)json.get("rollback");
        Thread t = new Thread(() -> {
            service.uncommitedDoubleAmount(country, id, rollback);
        });
        t.start();
    }
    
    /**
     * Unrepeatable read caused in a auth-type process where the data name of the BankAccount has
     * to be proven as beeing the same, while a secondary transaction will change it 
     */

    @GetMapping("/concurrent/unrepeatableread")
    public boolean getun(@RequestBody Map<String, Object> json){
        String countryCode = (String)json.get("country_code");
        String newName = (String)json.get("newName");
        int id = (int)json.get("id");
        boolean[] res = new boolean[1]; 
        try{
            Thread t1 = new Thread(() -> {
                res[0] = service.uncommitedAuthPersona(countryCode, id);
            });
            Thread t2 = new Thread(() -> {
                try{
                    Thread.sleep(1000);
                    service.changeName(countryCode, id, newName);
                }catch(InterruptedException ex){
                    System.out.println(ex);
                }
            });

            t1.start();
            t2.start();

        }catch(Exception ex){
            System.out.println(ex);
        }
        return res[0];
    }


    /**
     * 
     */
    @GetMapping("/concurrent/lostChanges")
    public Map<String, Double> lostChanges(@RequestBody Map<String, Object> json){
        String countryCode = (String)json.get("country_code");
        int id = (int)json.get("id");
        Map<String, Double> logs = new HashMap<>();
        Thread t1 = new Thread(() -> {
            logs.put("log after the Thread1 exec",service.lostChange(countryCode, id, false));
            try{
                Thread.sleep(1000);
                BankAccount account = service.getAccount(countryCode, id).get();
                logs.put("log at the end of changes", account.getAmount());
            }catch(InterruptedException ex){
                System.out.println(ex);
            }
            
        });
        Thread t2 = new Thread(() -> {
            logs.put("log after the Thread2 exec",service.lostChange(countryCode, id, false));

        });

        t1.start();
        try{
            t1.join();
        }catch(InterruptedException ex){
            System.out.println(ex);

        }

        t2.start();
        
        try{
            Thread.sleep(3000);
            return logs;
        }catch(InterruptedException ex){
            System.out.println(ex);
        }
        return new HashMap<>();
    }


    /**
     * Phantom Read
     */
     @GetMapping("/concurrent/phantomread")
     public Map<String, Integer> getph(@RequestBody Map<String, String>json){
        String countryCode = json.get("country_code");
        Map<String, Integer> res = new HashMap<>();
        Thread t1 = new Thread(() -> {  
            res.put("Before phantom read", service.logAccountsNumber(countryCode));
            try{
                Thread.sleep(3000);
                res.put("After phantom read", service.logAccountsNumber(countryCode));
            }catch(InterruptedException ex){
                System.out.println(ex);
            }
        });

        Thread t2 = new Thread(() -> {
            BankAccount acc = new BankAccount("AddedT2", 0.00);
            service.save(countryCode, acc);
        });

        t1.start();
        t2.start();
            
        try{
            Thread.sleep(5000);
            return res;
        }catch(InterruptedException ex){
            System.out.println(ex);
        }
        return new HashMap<>();
     }
    
}
