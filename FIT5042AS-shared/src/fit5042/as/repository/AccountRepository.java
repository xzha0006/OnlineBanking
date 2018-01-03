/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.repository;

import fit5042.as.repository.entities.BankAccount;
import javax.ejb.Remote;

/**
 *
 * @author xuanzhang
 */
@Remote
public interface AccountRepository {
    /**
     * Update an account in the repository
     *
     * @param transaction - the updated information regarding an account
     */
    public void editAccount(BankAccount account) throws Exception;
    
    public BankAccount searchById(int id) throws Exception;
}
