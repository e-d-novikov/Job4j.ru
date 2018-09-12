package c.collections.lite.d.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Class Bank.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class Bank {
    /**
     * Bank data warehouse.
     */
    private HashMap<User, List<Account>> bank = new HashMap<>(16, 0.75f);
    /**
     * Amount accounts.
     */
    private int amountAccounts = 0;
    /**
     * The method adds user.
     * @param name - user name;
     * @param passport - user's passport data.
     */
    public void addUser(String name, int passport) {
        bank.put(new User(name, passport), new ArrayList<Account>(10));
        addAccountToUser(passport);
    }
    /**
     * The method deletes the user.
     * @param passport - user's passport data.
     */
    public void deleteUser(int passport) {
        bank.remove(getUser(passport));
    }
    /**
     * The method adds an account to the user;
     * @param passport- user's passport data.
     */
    public void addAccountToUser(int passport) {
        bank.get(getUser(passport)).add(new Account(0, amountAccounts));
        amountAccounts++;
    }
    /**
     * The method delete an account to the user;
     * @param passport - user's passport data.
     * @param requisites - account details.
     */
    public void deleteAccountFromUser(int passport, int requisites) {
        for (Account account : bank.get(getUser(passport))) {
            if (account.getRequisites() == requisites) {
                bank.get(getUser(passport)).remove(account);
            }
        }
    }
    /**
     * The method adds money to the user account.
     * @param passport - user's passport data;
     * @param requisites - account details;
     * @param value - amount of money;
     */
    public void addMoneyToAccountUser(int passport, int requisites, int value) {
        getAccount(passport, requisites).setValue(getAccount(passport, requisites).getValue() + value);
    }
    /**
     * The method returns a list of user accounts.
     * @param passport - user's passport data.
     * @return - list of user accounts.
     */
    public List<Account> getUserAccounts(int passport) {
        return bank.get(getUser(passport));
    }
    /**
     * The method transfers money between accounts of users.
     * @param srcPassport - passport data of the sender;
     * @param srcRequisite - details of the sender's account;
     * @param destPassport - passport data of the recipient;
     * @param destRequisite - beneficiary account details;
     * @param amount - amount of money;
     * @return - true, if transfer successful, else false.
     */
    public boolean transferMoney(int srcPassport, int srcRequisite, int destPassport, int destRequisite, double amount) {
        boolean result = false;
        if (getAccount(srcPassport, srcRequisite).getValue() >= amount) {
            getAccount(srcPassport, srcRequisite).setValue(getAccount(srcPassport, srcRequisite).getValue() - amount);
            getAccount(destPassport, destRequisite).setValue(getAccount(destPassport, destRequisite).getValue() + amount);
            result = true;
        }
        return result;
    }
    /**
     * The method receives user data.
     * @param passport - user's passport data.
     * @return - receives user data.
     */
    public User getUser(int passport) {
        User result = null;
        for (User user : bank.keySet()) {
            if (user.getPassport() == passport) {
                result = user;
            }
        }
        return result;
    }
    /**
     * The method returns user account
     * @param passport - user's passport data.
     * @param requisites - account details.
     * @return - user account.
     */
    public Account getAccount(int passport, int requisites) {
        Account result = null;
        for (Account account : getUserAccounts(passport)) {
            if (account.getRequisites() == requisites) {
                result = account;
            }
        }
        return result;
    }
    /**
     * The method print list of user data.
     */
    public void listUsers() {
        for (User user : bank.keySet()) {
            System.out.printf("%s %d%n", user.getName(), user.getPassport());
            for (Account account : getUserAccounts(user.getPassport())) {
                System.out.printf("%s %d - %#.2f %s%n", "№", account.getRequisites(), account.getValue(), "руб.");
            }
        }
    }
}
