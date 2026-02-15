package com.fidelity.mts.repository;

import com.fidelity.mts.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionLogRepository extends JpaRepository<TransactionLog,Integer> {
    List<TransactionLog> findAllByFromAccountId(long id);

    List<TransactionLog> findAllByToAccountId(long id);
    List<TransactionLog> findByIdempotencyKey(String idempotencykey);
}