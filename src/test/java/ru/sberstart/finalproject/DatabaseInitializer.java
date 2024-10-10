package ru.sberstart.finalproject;

import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import ru.sberstart.finalproject.domain.enitity.bank.Bank;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.domain.enitity.card.Card;
import ru.sberstart.finalproject.domain.enitity.card.CardSecret;
import ru.sberstart.finalproject.domain.enitity.user.User;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;

import static ru.sberstart.finalproject.jooq.Tables.BANKS;
import static ru.sberstart.finalproject.jooq.Tables.BANKACCOUNTS;
import static ru.sberstart.finalproject.jooq.Tables.CARDS;
import static ru.sberstart.finalproject.jooq.Tables.CARDSECRETS;
import static ru.sberstart.finalproject.jooq.Tables.USERS;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestExecutionListeners(listeners = DependencyInjectionTestExecutionListener.class, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class DatabaseInitializer {
    @Value("${test-resource.package-name.path}")
    private String PATH_TO_DIRECTORY;

    private final LinkedHashSet<String> flow = new LinkedHashSet<>();

    @Autowired
    private DSLContext context;

    private final StorageScanner scanner = new StorageScanner();

    @BeforeAll
    public void setupDatabase() {
        try (Stream<Path> files = Files.walk(Path.of(PATH_TO_DIRECTORY))) {
            initializationFlow(
                    files.filter(Files::isRegularFile)
                            .peek(file -> System.out.println("Обнаружен и добавлен файл с именем: " + file))
                            .toList());
        } catch (Exception ignored) {}
    }

    private void initializationFlow(List<Path> files) {
        flow.forEach(this::insertFromJsonFile);
    }

    // При добавлении сущностей необходимо внести соответствующие изменения в порядок генерации БД,
    // а также настроить метод insertFromJsonFile, где добавить этап расшифровки нового файла,
    // хранящего, соответствующую сущность
    {
        flow.add("Banks");
        flow.add("Users");
        flow.add("BankAccounts");
        flow.add("CardSecrets");
        flow.add("Cards");
    }

    private void insertFromJsonFile(String entry) {
        switch (entry) {
            case "Banks"            ->      insertionFromJsonFile(BANKS, Bank.class);
            case "BankAccounts"     ->      insertionFromJsonFile(BANKACCOUNTS, BankAccount.class);
            case "Cards"            ->      insertionFromJsonFile(CARDS, Card.class);
            case "CardSecrets"      ->      insertionFromJsonFile(CARDSECRETS, CardSecret.class);
            case "Users"            ->      insertionFromJsonFile(USERS, User.class);
        }
    }

    private <T, R extends UpdatableRecordImpl<R>> void insertionFromJsonFile(Table<R> table, Class<T> clazz) {
        int[] execute = context.batchInsert(
                scanner
                        .findAll(clazz)
                        .peek(System.out::println)
                        .map(record -> context.newRecord(table, record))
                        .toList()
                )
                .execute();
        System.out.println("Добавил " + execute.length + " записей");
    }
}