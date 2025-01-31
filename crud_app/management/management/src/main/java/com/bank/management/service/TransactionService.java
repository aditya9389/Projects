package com.bank.management.service;

import com.bank.management.model.transaction.DepositRequestModel;
import com.bank.management.model.transaction.TransactionResponseModel;
import com.bank.management.model.transaction.WithdrawRequestModel;

public interface TransactionService {
    TransactionResponseModel deposit(DepositRequestModel request);
    TransactionResponseModel withdraw(WithdrawRequestModel request);
}