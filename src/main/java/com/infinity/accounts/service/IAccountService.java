package com.infinity.accounts.service;

import com.infinity.accounts.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
}
