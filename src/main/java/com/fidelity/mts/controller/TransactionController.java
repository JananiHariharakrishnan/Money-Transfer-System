package com.fidelity.mts.controller;

import com.fidelity.mts.dto.TransferRequestDto;
import com.fidelity.mts.entity.TransactionLog;
import com.fidelity.mts.enums.TransactionStatus;
import com.fidelity.mts.service.AccountService;
import com.fidelity.mts.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TransactionController {

    @Autowired
    TransferService transferService;
    @PostMapping("/transfers")
    public ResponseEntity<TransactionStatus> getTransactionLogById(@RequestBody TransferRequestDto transferRequestDto) {
        TransactionStatus e = transferService.transfer(transferRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }
}
