package fit5042.as.repository;

import fit5042.as.repository.entities.BankTransaction;
import fit5042.as.repository.entities.BankUser;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 * This interface is used to operate bank transaction
 * @author xuanzhang
 */
@Remote
public interface TransactionRepository {
    
    /**
     * Add the transaction being passed as parameter into the repository
     *
     * @param transaction - the transaction to add
     */
    public void addBankTransaction(BankTransaction transaction) throws Exception;

    /**
     * Search for a transaction by its transaction ID
     *
     * @param id - the transactionId of the transaction to search for
     * @return the transaction found
     */
    public BankTransaction searchTransactionById(int id) throws Exception;

    /**
     * Return all the transactions in the repository
     *
     * @return all the transactions in the repository
     */
    public List<BankTransaction> getAllTransactions() throws Exception;
    
    
    /**
     * Remove the transaction, whose transaction ID matches the one being passed as parameter, from the repository
     *
     * @param transactionId - the ID of the transaction to remove
     */
    public void removeTransaction(int transactionId) throws Exception;
    
    /**
     * Update a transaction in the repository
     *
     * @param transaction - the updated information regarding a transaction
     */
    public void editTransaction(BankTransaction transaction) throws Exception;
    
    /**
     * Search for transactions by their contact person
     *
     * @param contactPerson - the contact person that is responsible for the transactions
     * @return the transactions found
     */
    public Set<BankTransaction> searchTransactionByUser(BankUser bankUser) throws Exception;
    
    public List<BankTransaction> searchByTypeOrName(String input) throws Exception;
    
}
