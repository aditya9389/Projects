package com.bank.management.mapper;

import com.bank.management.entity.Account;
import com.bank.management.entity.Transaction;
import com.bank.management.entity.TransactionType;
import com.bank.management.model.transaction.DepositRequestModel;
import com.bank.management.model.transaction.TransactionResponseModel;

public interface TransactionMapper {
    Transaction toEntity(double amount, Account account, TransactionType type);
    TransactionResponseModel toResponseModel(Long id, double amount, double balance);
}
