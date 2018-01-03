package fit5042.as.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fit5042.as.repository.TransactionRepository;
import fit5042.as.repository.entities.BankTransaction;
import fit5042.as.repository.entities.BankUser;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author xuanzhang
 */
@Stateless
public class TransactionRepositoryImpl implements TransactionRepository{
    @PersistenceContext(unitName = "FIT5042AS-ejbPU")
    EntityManager entityManager;
    
    @Override
    public void addBankTransaction(BankTransaction transaction) throws Exception {
        entityManager.persist(transaction);
    }

    @Override
    public BankTransaction searchTransactionById(int id) throws Exception {
        BankTransaction transaction = entityManager.find(BankTransaction.class, id);
//        property.getTags().size();//this line is used to get tag entity from TAG table for a LAZY fetch mode
        return transaction;
    }

    @Override
    public List<BankTransaction> getAllTransactions() throws Exception {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery cQuery = builder.createQuery(BankTransaction.class);
//        Root<BankTransaction> p = cQuery.from(BankTransaction.class);
//        cQuery.select(p);
//        return entityManager.createQuery(cQuery).getResultList();
        Query query = entityManager.createNamedQuery("getAllTransactions");
        return query.getResultList();
    }


    @Override
    public void removeTransaction(int transactionId) throws Exception {
        BankTransaction transaction = this.searchTransactionById(transactionId);
        entityManager.remove(transaction);
    }

    @Override
    public void editTransaction(BankTransaction transaction) throws Exception {
        entityManager.merge(transaction);
    }

    @Override
    public Set<BankTransaction> searchTransactionByUser(BankUser bankUser) throws Exception {
        BankUser userFull = entityManager.find(BankUser.class, bankUser.getUserId());
        userFull.getTransactions().size();
        return userFull.getTransactions();
    }

    @Override
    public List<BankTransaction> searchByTypeOrName(String input) throws Exception {
        Query query = entityManager.createNamedQuery(BankTransaction.GET_BY_TYPE_NAME_ID_QUERY_NAME);
        query.setParameter("name", input);
        query.setParameter("type", input);
        return query.getResultList();
    }
    
}
