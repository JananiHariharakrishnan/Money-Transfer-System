package com.fidelity.mts;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fidelity.mts.dto.TransferRequestDto;
import com.fidelity.mts.dto.TransferResponseDto;
import com.fidelity.mts.entity.Account;
import com.fidelity.mts.entity.TransactionLog;
import com.fidelity.mts.enums.AccountStatus;
import com.fidelity.mts.enums.TransactionStatus;
import com.fidelity.mts.exceptions.NegativeAmountException;
import com.fidelity.mts.service.TransferService;
import com.fidelity.mts.service.TransferServiceImpl;
import com.fidelity.mts.repository.AccountRepository;
import com.fidelity.mts.repository.TransactionLogRepository;
import com.fidelity.mts.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

public class TransferRequestValidationTest {

    @Mock
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionLogRepository transactionLogRepository;

    @InjectMocks
    private TransferServiceImpl transferService; // Use the implementation of the service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
    }


        // Helper to create mock accounts
        private Account createAccount(long id, BigDecimal balance, AccountStatus status) {
            Account account = new Account();
            account.setId(id);
            account.setBalance(balance);
            account.setStatus(status);
            return account;
        }

        @Test
        void testValidTransfer() {

            long fromId = 1L, toId = 2L;
            BigDecimal amount = BigDecimal.valueOf(100.00);
            String key = "TestValidTransfer";

            Account fromAccount = createAccount(fromId, BigDecimal.valueOf(2000.00), AccountStatus.ACTIVE);
            Account toAccount = createAccount(toId, BigDecimal.valueOf(6570.00), AccountStatus.CLOSED);
            TransferRequestDto request = new TransferRequestDto(fromId, toId, amount, key);

            when(accountService.getAccount(fromId)).thenReturn(fromAccount);
            when(accountService.getAccount(toId)).thenReturn(toAccount);
            when(transactionLogRepository.findByIdempotencyKey(key)).thenReturn(Collections.emptyList());

            TransferResponseDto result = transferService.transfer(request);


            assertNotNull(result);
            assertEquals("Transfer completed", result.getFinalMessage());
            assertEquals(TransactionStatus.SUCCESS, result.getStatus());
            verify(accountRepository, times(2)).saveAndFlush(any(Account.class));
            verify(transactionLogRepository).save(any(TransactionLog.class));
        }

    @Test
    void testNegativeAmount() {

        long fromId = 3L, toId = 1L;
        BigDecimal amount = BigDecimal.valueOf(-10.00);
        String key = "testNegativeAmount";

        Account fromAccount = createAccount(fromId, BigDecimal.valueOf(290.00), AccountStatus.ACTIVE);
        Account toAccount = createAccount(toId, BigDecimal.valueOf(450.00), AccountStatus.ACTIVE);
        TransferRequestDto request = new TransferRequestDto(fromId, toId, amount, key);

        when(accountService.getAccount(fromId)).thenReturn(fromAccount);
        when(accountService.getAccount(toId)).thenReturn(toAccount);

        // Ensure that an exception is thrown and transaction log is not saved
        assertThrows(NegativeAmountException.class, () -> transferService.transfer(request));
    }


    @Test
    void testNullFields() {
        TransferRequestDto request = new TransferRequestDto();

        //TransferRequestDto request = new TransferRequestDto(1L, 2L, BigDecimal.valueOf(50.00), "testKey");

        assertEquals(0L, request.getFromAccountId(), "FromAccountId is not null or 0");
        assertEquals(0L, request.getToAccountId(), "ToAccountId is not null or 0");
        assertNull(request.getAmount(), "Amount is not null");
        assertNull(request.getIdempotencyKey(), "IdempotencyKey is not null");
    }
    }

