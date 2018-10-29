package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(
        name = "customer"
)
@NamedQueries({@NamedQuery(
        name = "Customer.findAll",
        query = "SELECT c FROM Customer c"
), @NamedQuery(
        name = "Customer.findByCustomerNumber",
        query = "SELECT c FROM Customer c WHERE c.customerNumber = :customerNumber"
)})
public class Customer implements Serializable {
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
            length = 30
    )
    private String city;
    @Column(
            name = "CUSTOMER_NUMBER",
            nullable = false,
            length = 10
    )
    private String customerNumber;
    @Column(
            name = "FIRST_NAME",
            nullable = false,
            length = 30
    )
    private String firstName;
    @Column(
            name = "LAST_NAME",
            nullable = false,
            length = 30
    )
    private String lastName;
    @Column(
            nullable = false,
            length = 30
    )
    private String street;
    @Column(
            nullable = false,
            length = 10
    )
    private String title;
    @Column(
            nullable = false
    )
    private short zipcode;
    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.EAGER
    )
    private List<Account> accounts;

    public Customer() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomerNumber() {
        return this.customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(short zipcode) {
        this.zipcode = zipcode;
    }

    @XmlTransient
    public List<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account addAccount(Account account) {
        this.getAccounts().add(account);
        account.setCustomer(this);
        return account;
    }

    public Account removeAccount(Account account) {
        this.getAccounts().remove(account);
        account.setCustomer(null);
        return account;
    }
}
