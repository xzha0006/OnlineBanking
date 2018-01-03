/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.repository.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author xuanzhang
 */
@Entity
@Table(name = "BANK_USER")
public class BankUser implements Serializable{
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String type;
    private String address;
    private String phoneNumber;
    private String isValid;
    private Set<BankTransaction> transactions;
    private BankAccount account;
    
    
    public BankUser() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @OneToMany(mappedBy="bankUser")
    @JoinColumn(nullable = true)
    public Set<BankTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<BankTransaction> transactions) {
        this.transactions = transactions;
    }
    
    @OneToOne(mappedBy="owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(nullable = false)
    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
    
    public static BankUser copyWithoutId(BankUser oldUser) {
        BankUser newUser = new BankUser();
//        newUser.setAccount(oldUser.getAccount());
        newUser.setAddress(oldUser.getAddress());
        newUser.setEmail(oldUser.getEmail());
        newUser.setFirstName(oldUser.getFirstName());
        newUser.setIsValid(oldUser.getIsValid());
        newUser.setLastName(oldUser.getLastName());
        newUser.setPassword(oldUser.getPassword());
        newUser.setPhoneNumber(oldUser.getPhoneNumber());
        newUser.setType(oldUser.getType());
        
        return newUser;
    }
}
