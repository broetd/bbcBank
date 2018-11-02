package ch.bbc.fit4ipa.bbcbank.ejb;


import entity.Account;
import entity.Customer;
import entity.TransactionRecord;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BbcServiceImpl implements BbcBankService {

    @PersistenceContext(unitName = "bbcbank")
    private EntityManager em;

    private Customer customer;

    public Customer getCustomer() {
        List<Customer> resultList = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
        customer = resultList.get(0);
        customer.getAccounts();
        return customer;
    }

    public void getAuszahlung(TransactionRecord tr, Account belastungskonto) {
        tr.getAccount().setBalance(belastungskonto.getBalance().subtract(tr.getAmount()));
        em.merge(belastungskonto);
        em.persist(tr);
        em.flush();
    }
}
