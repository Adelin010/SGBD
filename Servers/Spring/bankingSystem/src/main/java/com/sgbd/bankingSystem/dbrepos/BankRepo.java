package com.sgbd.bankingSystem.dbrepos;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sgbd.bankingSystem.model.BankAccount;

public interface BankRepo extends JpaRepository<BankAccount, Integer>{
    //CRUD operations
    @Query(value = "select * from Accounts", nativeQuery = true)
    List<BankAccount> getAll();

    @Query(value = "select * from Accounts b where b.id = ?1", nativeQuery = true)
    Optional<BankAccount> getById(int id);

    @Modifying
    @Query(value = "update Accounts set amount = ?2 where id = ?1", nativeQuery = true)
    int updateAmount(int id, double amount);

    @Modifying
    @Query(value = "insert into Accounts values(?1, ?2)", nativeQuery = true)
    void create(String owner, double amount);

    @Modifying
    @Query(value = "delete from Accounts b where b.id = ?1", nativeQuery = true)
    void delete(int id);
}
