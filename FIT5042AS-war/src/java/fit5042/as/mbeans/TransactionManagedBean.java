/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.mbeans;

import fit5042.as.repository.TransactionRepository;
import fit5042.as.repository.entities.BankTransaction;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author xuanzhang
 */
@ManagedBean(name = "transactionManagedBean")
@ApplicationScoped

public class TransactionManagedBean implements Serializable {
    @EJB
    private TransactionRepository transactionRepository;
    private List<BankTransaction> allTransactions;
    private String welcome;
    public TransactionManagedBean() {
        welcome = "welcome to online banking!";
    }
    
//    
    
    public List<BankTransaction> getAllTransactions() {
        try {
            this.allTransactions = transactionRepository.getAllTransactions();
        } catch (Exception ex) {
            Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE, 
                    "cannot get all transaction records!", ex);
        }
        return this.allTransactions;
    }
    
    public BankTransaction getTransactionById(int id) {
        BankTransaction resultTransc = null;
        for(BankTransaction transaction: this.allTransactions) {
            if(transaction.getTransactionId() == id) {
                resultTransc = transaction;
                break;
            }
        }
        return resultTransc;
    }
//    public List<BankTransaction> searchByNameOrTypeOrId(String queryContent) {
//        List<BankTransaction> transactions = new ArrayList<>();
//        if (queryContent.matches("\\d+")) {
//            try {
//                BankTransaction transaction = transactionRepository.searchTransactionById(Integer.parseInt(queryContent));
//                transactions.add(transaction);
//            } catch (Exception ex) {
//                Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE, "Can not convert string to integer.", ex);
//            }
//        }
//        else {
//            try {
//                transactions = transactionRepository.searchByTypeOrName(queryContent);
//            } catch (Exception ex) {
//                Logger.getLogger(TransactionManagedBean.class.getName()).log(Level.SEVERE, "Can not search by transaction name or type.", ex);
//            }
//        }
//        return transactions;
//    }
    public String navigateToTransactionPage() {
        return "transaction";
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
    
}
