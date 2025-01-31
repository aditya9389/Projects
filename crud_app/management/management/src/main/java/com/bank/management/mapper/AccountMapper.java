package com.bank.management.mapper;

import com.bank.management.entity.Account;
import com.bank.management.model.account.AccountResponseModel;

public interface AccountMapper {
    AccountResponseModel toResponseModel(Account account);
}
