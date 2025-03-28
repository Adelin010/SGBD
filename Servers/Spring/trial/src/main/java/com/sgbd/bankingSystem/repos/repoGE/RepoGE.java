package com.sgbd.bankingSystem.repos.repoGE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgbd.bankingSystem.model.BankAccount;

@Repository
public interface RepoGE extends JpaRepository<BankAccount, Integer> {}
