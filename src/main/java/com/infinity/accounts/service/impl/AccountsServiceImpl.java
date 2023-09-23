package com.infinity.accounts.service.impl;

import com.infinity.accounts.AccountsConstants;
import com.infinity.accounts.dto.CustomerDto;
import com.infinity.accounts.entity.Accounts;
import com.infinity.accounts.entity.Customer;
import com.infinity.accounts.exception.CustomerAlreadyExists;
import com.infinity.accounts.mapper.CustomerMapper;
import com.infinity.accounts.repository.AccountsRepository;
import com.infinity.accounts.repository.CustomerRepository;
import com.infinity.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExists("Customer already registered with mobile number "
                    + customer.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Admin");
        //customer.setUpdatedAt(LocalDateTime.now());
        //customer.setUpdatedBy("Admin");
        Customer savedCustomer = customerRepository.save(customer);


        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long accountNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Admin");

        return newAccount;
    }
}
