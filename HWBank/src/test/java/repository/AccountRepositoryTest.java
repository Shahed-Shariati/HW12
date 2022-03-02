package repository;

import database.SessionFactorySingleton;
import database.SessionFactoryTest;
import model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import service.AccountService;

import static org.junit.jupiter.api.Assertions.*;
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class AccountRepositoryTest {
    private SessionFactory sessionFactory;
    private Account account;

    private AccountRepository accountRepository;

    @BeforeEach
    void sessionConnection(){
        sessionFactory =SessionFactoryTest.getInstance();
        account = new Account();
        accountRepository = new AccountRepository(sessionFactory);

        account.setAccountNumber("5823");
        account.setBalance(5823d);
        account.setType("ss");

    }

    @Test
    void connectionTest(){
        //Arrange

        //Act

        //Assert
        assertDoesNotThrow( () -> {

            SessionFactory sessionFactory = SessionFactoryTest.getInstance();
        });
    }
    @Test
    void add() {
        //Arrange

        //Act
        accountRepository.add(account);
        //Assert
        assertNotNull(accountRepository.findById(account.getId())); }

    @Test
    void upDate() {
        //Arrange

        accountRepository.add(account);
        account.setAccountNumber("9876");
        account.setBalance(1200d);


        //Act
        accountRepository.upDate(account);
       account = accountRepository.findById(account.getId());

        //Assert

        assertAll(() -> assertEquals("9876",account.getAccountNumber()),
                () -> assertEquals(1200d,account.getBalance())
        );


    }

    @Test
    void delete() {
        //Arrange

        accountRepository.add(account);

        //Act
        accountRepository.delete(account.getId());

        //Assert

        assertNull(accountRepository.findById(account.getId()));
    }

    @Test
    void findById() {
        //Arrange
       
        accountRepository.add(account);

        //Act
        account = accountRepository.findById(account.getId());

        //Assert
        assertAll(() -> assertEquals("5823",account.getAccountNumber()),
                () -> assertEquals(5823d,account.getBalance())
        );
    }

    @AfterEach
    void truncate(){
     accountRepository.truncateTable();
    }
}