package com.bank.management.service;

import com.bank.management.model.account.AccountResponseModel;

import java.net.URI;
import java.util.List;

public interface AccountService {
    AccountResponseModel createNewAccount();

    List<AccountResponseModel> getMyAccounts();
}
