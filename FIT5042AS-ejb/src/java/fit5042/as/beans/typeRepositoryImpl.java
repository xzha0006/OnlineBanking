/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.beans;

import fit5042.as.repository.TypeRepository;
import fit5042.as.repository.entities.TransactionType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xuanzhang
 */
@Stateless
public class typeRepositoryImpl implements TypeRepository{
    @PersistenceContext(unitName = "FIT5042AS-ejbPU")
    EntityManager entityManager;

    @Override
    public void addTransactionType(TransactionType type) throws Exception {
        entityManager.persist(type);
    }
    
    
}
