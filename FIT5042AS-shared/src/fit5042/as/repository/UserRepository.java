/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.repository;

import fit5042.as.repository.entities.BankUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author xuanzhang
 */
@Remote
public interface UserRepository {
    
    public List<BankUser> getAllUsers() throws Exception;
    /**
     * Add the user being passed as parameter into the repository
     *
     * @param user - the user to add
     */
    public void addBankUser(BankUser user) throws Exception;

    /**
     * Search for a user by its user ID
     *
     * @param id - the transactionId of the transaction to search for
     * @return the transaction found
     */
    public BankUser searchUserById(int id) throws Exception;

    
    /**
     * Remove the user, whose user ID matches the one being passed as parameter, from the repository
     *
     * @param userId - the ID of the user to remove
     */
    public void removeBankUser(int userId) throws Exception;
    
    /**
     * Update a user in the repository
     *
     * @param bankUser - the updated information regarding a user
     */
    public void editBankUser(BankUser bankUser) throws Exception;
}
