package c.collections.lite.d.control;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class BankTest tests methods from class Bank.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class BankTest {

    public String l = System.lineSeparator();

    @Test
    public void whenAddUser() {
        Bank test = new Bank();
        test.addUser("TestUser", 7525);
        assertThat(test.getUser(7525).toString(), is("User name TestUser Passport 7525"));
    }

    @Test
    public void whenAddMoneyToAccountUser() {
        Bank test = new Bank();
        test.addUser("TestUser", 7525);
        test.addMoneyToAccountUser(7525, 0, 500);
        assertThat(test.getAccount(7525, 0).toString(), is("Account 0 Amount 500.0"));
    }

    @Test
    public void whenDeleteUser() {
        Bank test = new Bank();
        test.addUser("TestUser", 7525);
        test.deleteUser(7525);
        User result = null;
        assertThat(test.getUser(7525), is(result));
    }

    @Test
    public void whenAddAccountToUser() {
        Bank test = new Bank();
        test.addUser("TestUser", 7525);
        test.addAccountToUser(7525);
        assertThat(test.getAccount(7525, 0).toString(), is("Account 0 Amount 0.0"));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        Bank test = new Bank();
        test.addUser("TestUser", 7525);
        test.addAccountToUser(7525);
        test.deleteAccountFromUser(7525, 0);
        Account result = null;
        assertThat(test.getAccount(7525, 0), is(result));
    }

    @Test
    public void whenTransferMoney() {
        Bank test = new Bank();
        test.addUser("TestUserOne", 7525);
        test.addMoneyToAccountUser(7525, 0, 1000);
        test.addUser("TestUserTwo", 7545);
        test.transferMoney(7525, 0, 7545, 1, 500);
        assertThat(test.getAccount(7525, 0).toString(), is("Account 0 Amount 500.0"));
        assertThat(test.getAccount(7545, 1).toString(), is("Account 1 Amount 500.0"));
    }

    @Test
    public void whenTransferMoneyOneUser() {
        Bank test = new Bank();
        test.addUser("TestUser", 7525);
        test.addMoneyToAccountUser(7525, 0, 1000);
        test.addAccountToUser(7525);
        test.transferMoney(7525, 0, 7525, 1, 500);
        assertThat(test.getAccount(7525, 0).toString(), is("Account 0 Amount 500.0"));
        assertThat(test.getAccount(7525, 1).toString(), is("Account 1 Amount 500.0"));
    }
}

