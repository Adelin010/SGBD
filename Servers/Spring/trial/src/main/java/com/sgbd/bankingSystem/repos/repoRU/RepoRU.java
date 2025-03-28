package com.sgbd.bankingSystem.repos.repoRU;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgbd.bankingSystem.model.BankAccount;

@Repository
public interface RepoRU extends JpaRepository<BankAccount, Integer> {}
