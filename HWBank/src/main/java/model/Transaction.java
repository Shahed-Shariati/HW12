package model;

import java.util.Date;

public class Transaction {
    private int id;
    private Account accountSource;
    private Double amount;
    private String status;
    private String Type;
    private Date date;
    private String dateStr;
    private Account accountDestination;

    public Transaction(int id, Account accountSource, Double amount, String status, String type, Date date) {
        this.id = id;
        this.accountSource = accountSource;
        this.amount = amount;
        this.status = status;
        Type = type;
        this.date = date;
    }

    public Transaction(Account account, Double amount, String status, String type, Date date, Account accountDestination) {
        this.accountDestination = accountDestination;
        this.accountSource = account;
        this.amount = amount;
        this.status = status;
        this.Type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccountSource() {
        return accountSource;
    }

    public void setAccountSource(Account accountSource) {
        this.accountSource = accountSource;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Account getAccountDestination() {
        return accountDestination;
    }

    public void setAccountDestination(Account accountDestination) {
        this.accountDestination = accountDestination;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                "," + accountSource +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", Type='" + Type + '\'' +
                ", date=" + date +
                '}';
    }
}
