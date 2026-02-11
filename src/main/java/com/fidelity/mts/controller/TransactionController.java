package com.fidelity.mts.controller;

import com.fidelity.mts.dto.TransferRequestDto;
import com.fidelity.mts.dto.TransferResponseDto;
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
    public ResponseEntity<TransferResponseDto> getTransactionLogById(@RequestBody TransferRequestDto transferRequestDto) {
        TransferResponseDto e = transferService.transfer(transferRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }
}
