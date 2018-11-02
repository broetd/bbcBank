package ch.bbc.fit4ipa.bbcbank.ejb;

import entity.Account;
import entity.Customer;
import entity.TransactionRecord;

import javax.ejb.Remote;

@Remote
public interface BbcBankService {

    Customer getCustomer();

    void getAuszahlung(TransactionRecord tr, Account belastungskonto);

}
