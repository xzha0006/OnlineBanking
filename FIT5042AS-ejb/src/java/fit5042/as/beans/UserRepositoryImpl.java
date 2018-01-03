/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.beans;

import fit5042.as.repository.UserRepository;
import fit5042.as.repository.entities.BankUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author xuanzhang
 */
@Stateless
public class UserRepositoryImpl implements UserRepository{
    @PersistenceContext(unitName = "FIT5042AS-ejbPU")
    EntityManager entityManager;
    
    @Override
    public List<BankUser> getAllUsers() throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery cQuery = builder.createQuery(BankUser.class);
        Root<BankUser> cp = cQuery.from(BankUser.class);
        cQuery.select(cp);
        cQuery.where(builder.equal(cp.get("isValid"), "true"));
        return entityManager.createQuery(cQuery).getResultList();
    }

    @Override
    public void addBankUser(BankUser user) throws Exception {
        entityManager.persist(user);
    }

    @Override
    public BankUser searchUserById(int id) throws Exception {
        BankUser user = entityManager.find(BankUser.class, id);
        return user;
    }

    @Override
    public void removeBankUser(int userId) throws Exception {
        BankUser user = this.searchUserById(userId);
        user.setIsValid("false");
        //change the isValid to false instead of deleting it 
        entityManager.merge(user);
        System.out.println("Delete user successfully.");
    }

    @Override
    public void editBankUser(BankUser bankUser) throws Exception {
        entityManager.merge(bankUser);
        System.out.println("Update user successfully.");
    }
    
}
