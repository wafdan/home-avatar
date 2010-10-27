/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import AvatarEntity.exceptions.IllegalOrphanException;
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
public class CustomerJpaController {

    public CustomerJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) throws PreexistingEntityException, Exception {
        if (customer.getReservationCollection() == null) {
            customer.setReservationCollection(new ArrayList<Reservation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Reservation> attachedReservationCollection = new ArrayList<Reservation>();
            for (Reservation reservationCollectionReservationToAttach : customer.getReservationCollection()) {
                reservationCollectionReservationToAttach = em.getReference(reservationCollectionReservationToAttach.getClass(), reservationCollectionReservationToAttach.getReservationId());
                attachedReservationCollection.add(reservationCollectionReservationToAttach);
            }
            customer.setReservationCollection(attachedReservationCollection);
            em.persist(customer);
            for (Reservation reservationCollectionReservation : customer.getReservationCollection()) {
                Customer oldUsernameOfReservationCollectionReservation = reservationCollectionReservation.getUsername();
                reservationCollectionReservation.setUsername(customer);
                reservationCollectionReservation = em.merge(reservationCollectionReservation);
                if (oldUsernameOfReservationCollectionReservation != null) {
                    oldUsernameOfReservationCollectionReservation.getReservationCollection().remove(reservationCollectionReservation);
                    oldUsernameOfReservationCollectionReservation = em.merge(oldUsernameOfReservationCollectionReservation);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCustomer(customer.getUsername()) != null) {
                throw new PreexistingEntityException("Customer " + customer + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customer customer) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer persistentCustomer = em.find(Customer.class, customer.getUsername());
            Collection<Reservation> reservationCollectionOld = persistentCustomer.getReservationCollection();
            Collection<Reservation> reservationCollectionNew = customer.getReservationCollection();
            List<String> illegalOrphanMessages = null;
            for (Reservation reservationCollectionOldReservation : reservationCollectionOld) {
                if (!reservationCollectionNew.contains(reservationCollectionOldReservation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reservation " + reservationCollectionOldReservation + " since its username field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Reservation> attachedReservationCollectionNew = new ArrayList<Reservation>();
            for (Reservation reservationCollectionNewReservationToAttach : reservationCollectionNew) {
                reservationCollectionNewReservationToAttach = em.getReference(reservationCollectionNewReservationToAttach.getClass(), reservationCollectionNewReservationToAttach.getReservationId());
                attachedReservationCollectionNew.add(reservationCollectionNewReservationToAttach);
            }
            reservationCollectionNew = attachedReservationCollectionNew;
            customer.setReservationCollection(reservationCollectionNew);
            customer = em.merge(customer);
            for (Reservation reservationCollectionNewReservation : reservationCollectionNew) {
                if (!reservationCollectionOld.contains(reservationCollectionNewReservation)) {
                    Customer oldUsernameOfReservationCollectionNewReservation = reservationCollectionNewReservation.getUsername();
                    reservationCollectionNewReservation.setUsername(customer);
                    reservationCollectionNewReservation = em.merge(reservationCollectionNewReservation);
                    if (oldUsernameOfReservationCollectionNewReservation != null && !oldUsernameOfReservationCollectionNewReservation.equals(customer)) {
                        oldUsernameOfReservationCollectionNewReservation.getReservationCollection().remove(reservationCollectionNewReservation);
                        oldUsernameOfReservationCollectionNewReservation = em.merge(oldUsernameOfReservationCollectionNewReservation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = customer.getUsername();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Reservation> reservationCollectionOrphanCheck = customer.getReservationCollection();
            for (Reservation reservationCollectionOrphanCheckReservation : reservationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the Reservation " + reservationCollectionOrphanCheckReservation + " in its reservationCollection field has a non-nullable username field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
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

    public Customer findCustomer(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
