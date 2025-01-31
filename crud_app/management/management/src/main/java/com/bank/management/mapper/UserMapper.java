package com.bank.management.mapper;

import com.bank.management.entity.User;
import com.bank.management.model.authentication.RegisterRequestModel;

public interface UserMapper {
    User toUser(RegisterRequestModel request);
}
