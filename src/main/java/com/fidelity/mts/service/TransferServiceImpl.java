package com.fidelity.mts.service;

import com.fidelity.mts.dto.TransferRequestDto;
import com.fidelity.mts.entity.Account;
import com.fidelity.mts.entity.TransactionLog;
import com.fidelity.mts.enums.AccountStatus;
import com.fidelity.mts.exceptions.AccountNotActiveException;
import com.fidelity.mts.exceptions.AccountNotFoundException;
import com.fidelity.mts.exceptions.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    AccountService accountService;

    @Override
    public void transfer(TransferRequestDto transferRequest) {
        long fromId=transferRequest.getFromAccountId();
        Account fromAccount=accountService.getAccount(fromId);

        long toId=transferRequest.getToAccountId();
        Account toAccount=accountService.getAccount(toId);

        BigDecimal amount= transferRequest.getAmount();

        boolean validTransfer=validateTransfer(fromAccount,toAccount,amount);
        if(validTransfer){
            executeTransfer();
        }
        else{
            return;
        }


    }

    @Override
    public boolean validateTransfer(Account senderAcc, Account recieverAcc, BigDecimal amountToBeDebited) {

        //Checking Self Transfer
        if(senderAcc.getId()==recieverAcc.getId()){
            //Here we have to return error because self transfer is not allowed
            //return;
        }

        //Checking if Sender Account is active
        if(!senderAcc.isActive()){
            throw new AccountNotActiveException("Sender Account Not Active !");
        }

        //Checking if Receiver Account is active
        if(!recieverAcc.isActive()){
            throw new AccountNotActiveException("Receiver Account Not Active !");
        }

        //Checking if amount > 0

        if(amountToBeDebited.compareTo(BigDecimal.ZERO)<0){
            //Here we have to return error because amount > 0 is a mandatory condition
            //throw new ;
        }

        if((amountToBeDebited.compareTo(senderAcc.getBalance())>0)){
            throw new InsufficientBalanceException("Balance is not sufficient");
        }


        return true;
    }

    @Override
    public void executeTransfer() {


    }
}
