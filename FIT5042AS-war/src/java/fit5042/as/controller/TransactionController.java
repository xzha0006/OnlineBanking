/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.controller;

import fit5042.as.mbeans.TransactionManagedBean;
import fit5042.as.repository.TypeRepository;
import fit5042.as.repository.entities.BankTransaction;
import fit5042.as.repository.entities.TransactionType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author xuanzhang
 */

@ManagedBean(name = "transactionController")
@SessionScoped
public class TransactionController {
    
    private int transactionId;
    private BankTransaction transaction;
    
    
    public TransactionController() {
        this.transactionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("transactionId"));
        System.out.println("ssss" + this.transactionId);
    }
    
    
    public BankTransaction getTransaction() {
        if (transaction == null) {
            System.out.println(this.transactionId);
            // Get application context bean MovieApplication 
            ELContext context = FacesContext.getCurrentInstance().getELContext();
            TransactionManagedBean transactionManagedBean = (TransactionManagedBean) FacesContext.getCurrentInstance()
                    .getApplication()
                    .getELResolver()
                    .getValue(context, null, "transactionManagedBean");
            // -1 to movieId since we +1 in JSF (to always have positive movie id!) 
            return transactionManagedBean.getTransactionById(transactionId);
        }
        return transaction;
    }
}
