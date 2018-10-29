package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(
        name = "transaction_record"
)
@NamedQuery(
        name = "TransactionRecord.findAll",
        query = "SELECT t FROM TransactionRecord t"
)
public class TransactionRecord implements Serializable {
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
    private BigDecimal amount;
    @Column(
            nullable = false,
            length = 30
    )
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "TRANSACTION_TIME",
            nullable = false
    )
    private Date transactionTime;
    @Column(
            nullable = false,
            length = 1
    )
    private String type;
    @ManyToOne
    @JoinColumn(
            name = "ACCOUNT_ID",
            nullable = false
    )
    private Account account;

    public TransactionRecord() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTransactionTime() {
        return this.transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

