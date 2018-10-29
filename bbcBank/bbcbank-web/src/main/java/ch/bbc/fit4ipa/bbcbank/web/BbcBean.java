package ch.bbc.fit4ipa.bbcbank.web;

import entity.Account;
import entity.Customer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean
public class BbcBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getTest() {
        return "";
    }
}
