package com.fidelity.mts.repository;

import com.fidelity.mts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    Optional<Account> findById(long id);


}