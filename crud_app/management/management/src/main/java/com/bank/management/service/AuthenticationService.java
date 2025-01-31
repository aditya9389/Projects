package com.bank.management.service;

import com.bank.management.model.authentication.AuthenticationResponseModel;
import com.bank.management.model.authentication.RegisterRequestModel;
import com.bank.management.model.authentication.LoginRequestModel;
import com.bank.management.model.authentication.UserProfileResponseModel;

public interface AuthenticationService {
    public AuthenticationResponseModel register(RegisterRequestModel request);

    public AuthenticationResponseModel login(LoginRequestModel request);
}
