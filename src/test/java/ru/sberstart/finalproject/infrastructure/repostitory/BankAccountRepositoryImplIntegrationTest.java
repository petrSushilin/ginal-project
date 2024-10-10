package ru.sberstart.finalproject.infrastructure.repostitory;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.StorageScanner;
import ru.sberstart.finalproject.api.dto.bankaccount.request.BankAccountCreationRequestDTO;
import ru.sberstart.finalproject.domain.enitity.bank.Bank;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.domain.enitity.user.User;
import ru.sberstart.finalproject.infrastructure.repostitory.interfaces.BankAccountRepository;
import ru.sberstart.finalproject.infrastructure.repostitory.interfaces.BankRepository;
import ru.sberstart.finalproject.infrastructure.repostitory.interfaces.UserRepository;
import ru.sberstart.finalproject.mapper.BankAccountMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class BankAccountRepositoryImplIntegrationTest {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    UserRepository userRepository;

    private final StorageScanner scanner = new StorageScanner();

    @Test
    void create() {
        Bank bank = bankRepository.getByIdentityNumber(scanner.findFirst(Bank.class).orElseThrow().getIdentityNumber())
                .orElseThrow();
        User user = userRepository.getFullUserInfoByPhoneNumber(scanner.findFirst(User.class).orElseThrow().getPhoneNumber())
                .orElseThrow();

        BankAccount createdBankAccount = bankAccountRepository.create(
                BankAccountMapper.INSTANCE.toEntity(new BankAccountCreationRequestDTO(bank.getId(), user.getId()))
                ).orElseThrow();
    }

    @Test
    void getServiceInfoByNumber() {
    }

    @Test
    void getByNumber() {
    }

    @Test
    void updateState() {
    }

    @Test
    void getTransactionAccounts() {
    }

    @Test
    void provideTransaction() {
    }
}