package ch.bbc.fit4ipa.bbcbank.web;

import entity.Account;
import entity.Customer;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class BbcBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public BigDecimal getAccountBalance(){
        Account account = new Account();
        BigDecimal accountBalance = account.getBalance();
        return accountBalance;
    }


}