/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.controller;

import fit5042.as.mbeans.TransactionManagedBean;
import fit5042.as.mbeans.User;
import fit5042.as.repository.AccountRepository;
import fit5042.as.repository.TransactionRepository;
import fit5042.as.repository.UserRepository;
import fit5042.as.repository.entities.BankAccount;
import fit5042.as.repository.entities.BankTransaction;
import fit5042.as.repository.entities.BankUser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author xuanzhang
 */
@ManagedBean(name = "moneyController")
@SessionScoped
public class MoneyTransferController {
    @EJB
    private TransactionRepository transactionRepository;
    private BankUser user = null;
    @EJB
    private AccountRepository accountRepository;
    private String amount;
    private String fromId;
    private String toId;
    private String message;

    public MoneyTransferController() {
//        this.userId = Integer.valueOf(FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap()
//                .get("userId"));
//        System.out.println("ssss" + this.transactionId);
    }
    
    public String transfer() {
        try {
            BankAccount accountFrom = accountRepository.searchById(Integer.parseInt(this.fromId));
            BankAccount accountTo = accountRepository.searchById(Integer.parseInt(this.toId));
            accountFrom.setBalance(accountFrom.getBalance() - Double.parseDouble(this.amount));
            accountTo.setBalance(accountTo.getBalance() + Double.parseDouble(this.amount));
            
            accountRepository.editAccount(accountFrom);
            accountRepository.editAccount(accountTo);
            
            BankTransaction transaction1 = new BankTransaction();
            transaction1.setBankUser(accountFrom.getOwner());
            transaction1.setTransactionType("MoneyOut");
            transaction1.setTransactionName("Money gets out from account " + this.fromId);
            BankTransaction transaction2 = new BankTransaction();
            transaction2.setBankUser(accountTo.getOwner());
            transaction2.setTransactionType("MoneyIn");
            transaction2.setTransactionName("Money comes in account " + this.toId);
            
            transactionRepository.addBankTransaction(transaction1);
            transactionRepository.addBankTransaction(transaction2);
            
        } catch (Exception ex) {
            Logger.getLogger(MoneyTransferController.class.getName()).log(Level.SEVERE, "Error occurs while transfering money.", ex);
        }
        
        return "finish.xhtml";
    }
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
