/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.repository;

import fit5042.as.repository.entities.TransactionType;
import javax.ejb.Remote;

/**
 *
 * @author xuanzhang
 */
@Remote
public interface TypeRepository {
    
    public void addTransactionType(TransactionType type) throws Exception;

}
