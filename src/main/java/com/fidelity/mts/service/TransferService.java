package com.fidelity.mts.service;

public interface TransferService {
    //void transfer(TransferRequest);
    boolean validateTransfer();
    void executeTransfer();

}
