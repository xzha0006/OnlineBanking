/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.beans;

import fit5042.as.repository.AccountRepository;
import fit5042.as.repository.entities.BankAccount;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xuanzhang
 */
@Stateless
public class AccountRepositoryImpl implements AccountRepository{
    @PersistenceContext(unitName = "FIT5042AS-ejbPU")
    EntityManager entityManager;
    
    @Override
    public void editAccount(BankAccount account) throws Exception {
        entityManager.merge(account);
    }

    @Override
    public BankAccount searchById(int id) throws Exception {
        BankAccount account =  entityManager.find(BankAccount.class, id);
        return account;
    }
    
}
