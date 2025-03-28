package com.sgbd.bankingSystem.repos.repoFR;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgbd.bankingSystem.model.BankAccount;

@Repository
public interface RepoFR extends JpaRepository<BankAccount, Integer> {
    
}
