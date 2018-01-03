/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.controller;

import fit5042.as.mbeans.TransactionManagedBean;
import fit5042.as.mbeans.User;
import fit5042.as.repository.TypeRepository;
import fit5042.as.repository.UserRepository;
import fit5042.as.repository.entities.BankAccount;
import fit5042.as.repository.entities.BankUser;
import fit5042.as.repository.entities.TransactionType;
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
@ManagedBean(name = "userController")
@SessionScoped
public class UserController {

    @EJB
    private TypeRepository typeRepository;
    private BankUser user = null;
    @EJB
    private UserRepository userRepository;
    private List<BankUser> userList;
    private TransactionType type = new TransactionType();
    
    public UserController() {
//        this.userId = Integer.valueOf(FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap()
//                .get("userId"));
//        System.out.println("ssss" + this.transactionId);
    }

    public String addType() {
        try {
            typeRepository.addTransactionType(this.type);
        } catch (Exception ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, "Cannot add transaction type!", ex);
        }
        
        return "finish.xhtml";
    }

    public BankUser getUser() {
        if (this.user == null) {
            int userId = Integer.valueOf(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap()
                    .get("userId"));
            try {
                this.user = userRepository.searchUserById(userId);
            } catch (Exception ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "cannot get user by id", ex);
            }
        }
        return this.user;
    }

    public List<BankUser> getAllUsers() {
        try {
            this.userList = userRepository.getAllUsers();
        } catch (Exception ex) {
            Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE,
                    "cannot get all users!", ex);
        }
        return this.userList;
    }

    public String addUser(User user) {
        try {
            BankUser bankUser = new BankUser();
            bankUser.setEmail(user.getEmail());
            bankUser.setFirstName(user.getFirstName());
            bankUser.setLastName(user.getLastName());
            bankUser.setPassword(user.getPassword());
            bankUser.setType(user.getType());
            bankUser.setIsValid("true");
            bankUser.setPhoneNumber(user.getPhoneNumber());
            BankAccount account = new BankAccount(1000.0);
            account.setOwner(bankUser);
            bankUser.setAccount(account);

            userRepository.addBankUser(bankUser);
        } catch (Exception ex) {
            Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE,
                    "cannot add user into database", ex);
        }

        return "index?faces-redirect=true";
    }

    public String removeUser(int id) {
        System.out.println(id);
        try {
            userRepository.removeBankUser(id);
            userList.remove(id);
        } catch (Exception ex) {
            Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE,
                    "cannot remove user", ex);
        }

        return "index?faces-redirect=true";
    }

    public String editUser(BankUser bankUser) {
        System.out.println(bankUser.getFirstName());
        try {
            userRepository.editBankUser(bankUser);
        } catch (Exception ex) {
            Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE,
                    "cannot edit user", ex);
        }
        return "index?faces-redirect=true";
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "../index.xhtml";
    }
}
