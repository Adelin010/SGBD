package com.sgbd.bankingSystem.repos.repoID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgbd.bankingSystem.model.BankAccount;

@Repository
public interface RepoID extends JpaRepository<BankAccount, Integer> {}
