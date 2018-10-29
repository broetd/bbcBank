package entity;

import entity.exception.InsufficientFundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "account"
)
@NamedQueries({@NamedQuery
        (
                name = "Account.findAll",
                query = "SELECT a FROM Account a"
        ), @NamedQuery(
        name = "Account.findByIBAN",
        query = "SELECT a FROM Account a WHERE a.iban = :iban"
), @NamedQuery(
        name = "Account.getIBANsByCustomerNumber",
        query = "SELECT a.iban FROM Account a JOIN a.customer c where c.customerNumber = :customerNumber"
)})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            unique = true,
            nullable = false
    )
    private int id;
    @Column(
            nullable = false,
            precision = 9,
            scale = 2
    )
    private BigDecimal balance;
    @Column(
            nullable = false,
            length = 30
    )
    private String iban;
    @Column(
            nullable = false,
            precision = 7,
            scale = 2
    )
    private BigDecimal overdraft;
    @ManyToOne
    @JoinColumn(
            name = "CUSTOMER_ID",
            nullable = false
    )

    private Customer customer;
    @OneToMany(
            mappedBy = "account",
            fetch = FetchType.EAGER
    )
    @OrderBy("transactionTime ASC")
    private List<TransactionRecord> transactionRecords;

    public Account() {
    }

    public int getId() {

        return this.id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getOverdraft() {

        return this.overdraft;
    }

    public void setOverdraft(BigDecimal overdraft) {
        this.overdraft = overdraft;
    }

    @XmlTransient
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<TransactionRecord> getTransactionRecords() {
        return this.transactionRecords;
    }

    public void setTransactionRecords(List<TransactionRecord> transactionRecords) {
        this.transactionRecords = transactionRecords;
    }

    public TransactionRecord addTransactionRecord(TransactionRecord transactionRecord) {
        this.getTransactionRecords().add(transactionRecord);
        transactionRecord.setAccount(this);
        return transactionRecord;
    }

    public TransactionRecord removeTransactionRecord(TransactionRecord transactionRecord) {
        this.getTransactionRecords().remove(transactionRecord);
        transactionRecord.setAccount(null);
        return transactionRecord;
    }

    public BigDecimal withdraw(BigDecimal amount) throws InsufficientFundException {
        BigDecimal calculatedNewBalance = this.getBalance().subtract(amount);
        if (calculatedNewBalance.compareTo(this.getOverdraft().negate()) == -1) {
            String errorMessage = "Not enough funds. Current balance: " + this.getBalance() + ". Credit limit: " + this.getOverdraft();
            throw new InsufficientFundException(errorMessage);
        } else {
            this.setBalance(calculatedNewBalance);
            return this.getBalance();
        }
    }

    public BigDecimal deposit(BigDecimal amount) {
        this.setBalance(this.getBalance().add(amount));
        return this.getBalance();
    }
}

















































