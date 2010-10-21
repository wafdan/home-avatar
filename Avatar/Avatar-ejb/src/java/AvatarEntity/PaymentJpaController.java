/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import AvatarEntity.exceptions.IllegalOrphanException;
import AvatarEntity.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class PaymentJpaController {

    public PaymentJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Payment payment) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Reservation reservationIdOrphanCheck = payment.getReservationId();
        if (reservationIdOrphanCheck != null) {
            Payment oldPaymentOfReservationId = reservationIdOrphanCheck.getPayment();
            if (oldPaymentOfReservationId != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Reservation " + reservationIdOrphanCheck + " already has an item of type Payment whose reservationId column cannot be null. Please make another selection for the reservationId field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservation reservationId = payment.getReservationId();
            if (reservationId != null) {
                reservationId = em.getReference(reservationId.getClass(), reservationId.getReservationId());
                payment.setReservationId(reservationId);
            }
            Staff username = payment.getUsername();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                payment.setUsername(username);
            }
            em.persist(payment);
            if (reservationId != null) {
                reservationId.setPayment(payment);
                reservationId = em.merge(reservationId);
            }
            if (username != null) {
                username.getPaymentCollection().add(payment);
                username = em.merge(username);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Payment payment) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Payment persistentPayment = em.find(Payment.class, payment.getPaymentId());
            Reservation reservationIdOld = persistentPayment.getReservationId();
            Reservation reservationIdNew = payment.getReservationId();
            Staff usernameOld = persistentPayment.getUsername();
            Staff usernameNew = payment.getUsername();
            List<String> illegalOrphanMessages = null;
            if (reservationIdNew != null && !reservationIdNew.equals(reservationIdOld)) {
                Payment oldPaymentOfReservationId = reservationIdNew.getPayment();
                if (oldPaymentOfReservationId != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Reservation " + reservationIdNew + " already has an item of type Payment whose reservationId column cannot be null. Please make another selection for the reservationId field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (reservationIdNew != null) {
                reservationIdNew = em.getReference(reservationIdNew.getClass(), reservationIdNew.getReservationId());
                payment.setReservationId(reservationIdNew);
            }
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                payment.setUsername(usernameNew);
            }
            payment = em.merge(payment);
            if (reservationIdOld != null && !reservationIdOld.equals(reservationIdNew)) {
                reservationIdOld.setPayment(null);
                reservationIdOld = em.merge(reservationIdOld);
            }
            if (reservationIdNew != null && !reservationIdNew.equals(reservationIdOld)) {
                reservationIdNew.setPayment(payment);
                reservationIdNew = em.merge(reservationIdNew);
            }
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getPaymentCollection().remove(payment);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getPaymentCollection().add(payment);
                usernameNew = em.merge(usernameNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = payment.getPaymentId();
                if (findPayment(id) == null) {
                    throw new NonexistentEntityException("The payment with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Payment payment;
            try {
                payment = em.getReference(Payment.class, id);
                payment.getPaymentId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The payment with id " + id + " no longer exists.", enfe);
            }
            Reservation reservationId = payment.getReservationId();
            if (reservationId != null) {
                reservationId.setPayment(null);
                reservationId = em.merge(reservationId);
            }
            Staff username = payment.getUsername();
            if (username != null) {
                username.getPaymentCollection().remove(payment);
                username = em.merge(username);
            }
            em.remove(payment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payment> findPaymentEntities() {
        return findPaymentEntities(true, -1, -1);
    }

    public List<Payment> findPaymentEntities(int maxResults, int firstResult) {
        return findPaymentEntities(false, maxResults, firstResult);
    }

    private List<Payment> findPaymentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Payment.class));
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

    public Payment findPayment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Payment.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Payment> rt = cq.from(Payment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
