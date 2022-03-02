package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private List<Account> accounts = new ArrayList<>();
    private Account account;

    public Customer(int id, String firstName, String lastName, String nationalCode, int role, ArrayList<Account> accounts) {
        super(id, firstName, lastName, nationalCode, role);
        this.accounts = accounts;
    }

    public Customer(int id, String firstName, String lastName, String nationalCode, int role) {
        super(id, firstName, lastName, nationalCode, role);
    }

    public Customer() {
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return
                "id= " + getId() +
                ", firstName= '" + getFirstName() + '\'' +
                ", lastName= '" + getLastName() + '\'' +
                ", nationalCode= '" + getNationalCode() + '\'' +
                '}';
    }
}
