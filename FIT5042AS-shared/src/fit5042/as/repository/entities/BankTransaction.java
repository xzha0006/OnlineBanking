/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.repository.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author xuanzhang
 */
@Entity
@Table(name = "BANK_TRANSACTION")
@NamedQueries({
@NamedQuery(name = BankTransaction.GET_BY_TYPE_NAME_ID_QUERY_NAME, query = "SELECT t FROM BankTransaction t WHERE "
            + "t.transactionName = :name OR t.transactionType = :type"),
@NamedQuery(name = "getAllTransactions", query = "SELECT t FROM BankTransaction t")})
  
public class BankTransaction implements Serializable{
    public static final String GET_BY_TYPE_NAME_ID_QUERY_NAME = "BankTransaction.getByIdOrTypeOrName";
    
    private int transactionId;
    private String transactionName;
    private String transactionType;
    private String description;
    private BankUser bankUser;
    private String createDate;
    private String isValid;

    public BankTransaction() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
    
    //set not null
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne()
    @JoinColumn(name = "bank_user_fk", nullable = false)
    public BankUser getBankUser() {
        return bankUser;
    }

    public void setBankUser(BankUser bankUser) {
        this.bankUser = bankUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
    
}
