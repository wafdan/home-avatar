/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import AvatarEntity.exceptions.NonexistentEntityException;
import AvatarEntity.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Christian
 */
public class StaffJpaController {

    public StaffJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Staff staff) throws PreexistingEntityException, Exception {
        if (staff.getPaymentCollection() == null) {
            staff.setPaymentCollection(new ArrayList<Payment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Payment> attachedPaymentCollection = new ArrayList<Payment>();
            for (Payment paymentCollectionPaymentToAttach : staff.getPaymentCollection()) {
                paymentCollectionPaymentToAttach = em.getReference(paymentCollectionPaymentToAttach.getClass(), paymentCollectionPaymentToAttach.getPaymentId());
                attachedPaymentCollection.add(paymentCollectionPaymentToAttach);
            }
            staff.setPaymentCollection(attachedPaymentCollection);
            em.persist(staff);
            for (Payment paymentCollectionPayment : staff.getPaymentCollection()) {
                Staff oldUsernameOfPaymentCollectionPayment = paymentCollectionPayment.getUsername();
                paymentCollectionPayment.setUsername(staff);
                paymentCollectionPayment = em.merge(paymentCollectionPayment);
                if (oldUsernameOfPaymentCollectionPayment != null) {
                    oldUsernameOfPaymentCollectionPayment.getPaymentCollection().remove(paymentCollectionPayment);
                    oldUsernameOfPaymentCollectionPayment = em.merge(oldUsernameOfPaymentCollectionPayment);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStaff(staff.getUsername()) != null) {
                throw new PreexistingEntityException("Staff " + staff + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Staff staff) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Staff persistentStaff = em.find(Staff.class, staff.getUsername());
            Collection<Payment> paymentCollectionOld = persistentStaff.getPaymentCollection();
            Collection<Payment> paymentCollectionNew = staff.getPaymentCollection();
            Collection<Payment> attachedPaymentCollectionNew = new ArrayList<Payment>();
            for (Payment paymentCollectionNewPaymentToAttach : paymentCollectionNew) {
                paymentCollectionNewPaymentToAttach = em.getReference(paymentCollectionNewPaymentToAttach.getClass(), paymentCollectionNewPaymentToAttach.getPaymentId());
                attachedPaymentCollectionNew.add(paymentCollectionNewPaymentToAttach);
            }
            paymentCollectionNew = attachedPaymentCollectionNew;
            staff.setPaymentCollection(paymentCollectionNew);
            staff = em.merge(staff);
            for (Payment paymentCollectionOldPayment : paymentCollectionOld) {
                if (!paymentCollectionNew.contains(paymentCollectionOldPayment)) {
                    paymentCollectionOldPayment.setUsername(null);
                    paymentCollectionOldPayment = em.merge(paymentCollectionOldPayment);
                }
            }
            for (Payment paymentCollectionNewPayment : paymentCollectionNew) {
                if (!paymentCollectionOld.contains(paymentCollectionNewPayment)) {
                    Staff oldUsernameOfPaymentCollectionNewPayment = paymentCollectionNewPayment.getUsername();
                    paymentCollectionNewPayment.setUsername(staff);
                    paymentCollectionNewPayment = em.merge(paymentCollectionNewPayment);
                    if (oldUsernameOfPaymentCollectionNewPayment != null && !oldUsernameOfPaymentCollectionNewPayment.equals(staff)) {
                        oldUsernameOfPaymentCollectionNewPayment.getPaymentCollection().remove(paymentCollectionNewPayment);
                        oldUsernameOfPaymentCollectionNewPayment = em.merge(oldUsernameOfPaymentCollectionNewPayment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = staff.getUsername();
                if (findStaff(id) == null) {
                    throw new NonexistentEntityException("The staff with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Staff staff;
            try {
                staff = em.getReference(Staff.class, id);
                staff.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The staff with id " + id + " no longer exists.", enfe);
            }
            Collection<Payment> paymentCollection = staff.getPaymentCollection();
            for (Payment paymentCollectionPayment : paymentCollection) {
                paymentCollectionPayment.setUsername(null);
                paymentCollectionPayment = em.merge(paymentCollectionPayment);
            }
            em.remove(staff);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Staff> findStaffEntities() {
        return findStaffEntities(true, -1, -1);
    }

    public List<Staff> findStaffEntities(int maxResults, int firstResult) {
        return findStaffEntities(false, maxResults, firstResult);
    }

    private List<Staff> findStaffEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Staff.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Staff findStaff(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Staff.class, id);
        } finally {
            em.close();
        }
    }

    public int getStaffCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Staff> rt = cq.from(Staff.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public String translatePosition(short input){
        if(input==0){
            return "Administrator";
        }else if(input ==1){
            return "Receptionist";
        }else{
            return "Manager";
        }
    }

}
