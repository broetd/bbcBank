package ch.bbc.fit4ipa.bbcbank.web;

import ch.bbc.fit4ipa.bbcbank.ejb.BbcBankService;
import entity.Account;
import entity.Customer;
import entity.TransactionRecord;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

@ManagedBean
public class BbcBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    public BbcBankService bbcService;

    private TransactionRecord tr;

    private Account belastungskonto;

    public Customer getCustomer(){
        return bbcService.getCustomer();
    }

    public TransactionRecord getTransactionRecord(){
        return tr;
    }

    public void setTransactionRecord(TransactionRecord tr){
        this.tr = tr;
    }

    public void getAuszahlung() {
            belastungskonto = getCustomer().getAccounts().get(0);

            tr.setTransactionTime(Date.from(Instant.now()));
            belastungskonto.addTransactionRecord(tr);
            bbcService.getAuszahlung(tr, belastungskonto);
    }
}
