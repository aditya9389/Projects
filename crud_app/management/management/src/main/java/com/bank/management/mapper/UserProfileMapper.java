package com.bank.management.mapper;

import com.bank.management.entity.User;
import com.bank.management.model.authentication.UserProfileResponseModel;

public interface UserProfileMapper {
    UserProfileResponseModel toUserProfile(User user);
}
