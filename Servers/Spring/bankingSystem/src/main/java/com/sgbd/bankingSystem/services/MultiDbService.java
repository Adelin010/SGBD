package com.sgbd.bankingSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgbd.bankingSystem.dbrepos.dbrepo1.BankRepo1;
import com.sgbd.bankingSystem.dbrepos.dbrepo2.BankRepo2;
import com.sgbd.bankingSystem.dbrepos.dbrepo3.BankRepo3;
import com.sgbd.bankingSystem.dbrepos.dbrepo4.BankRepo4;
import com.sgbd.bankingSystem.model.BankAccount;

@Service
public class MultiDbService {
    @Autowired
    private BankRepo1 db1;
    @Autowired
    private BankRepo2 db2;
    @Autowired
    private BankRepo3 db3;
    @Autowired
    private BankRepo4 db4;

    public void saveTo1(BankAccount b){
        this.db1.save(b);
    }

    public void saveTo2(BankAccount b){
        this.db2.save(b);
    }
    public void saveTo3(BankAccount b){
        this.db3.save(b);
    }
    public void saveTo4(BankAccount b){
        this.db4.save(b);
    }

    public List<BankAccount> getAllFrom1(){
        return this.db1.findAll();
    }

    public List<BankAccount> getAllFrom2(){
        return this.db2.findAll();
    }

    public List<BankAccount> getAllFrom3(){
        return this.db3.findAll();
    }

    public List<BankAccount> getAllFrom4(){
        return this.db4.findAll();
    }
    
}
