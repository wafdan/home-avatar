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
import java.util.Collection;

/**
 *
 * @author Christian
 */
public class ReservationJpaController {

    public ReservationJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservation reservation) {
        if (reservation.getPaymentCollection() == null) {
            reservation.setPaymentCollection(new ArrayList<Payment>());
        }
        if (reservation.getReservationItemCollection() == null) {
            reservation.setReservationItemCollection(new ArrayList<ReservationItem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer username = reservation.getUsername();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                reservation.setUsername(username);
            }
            Collection<Payment> attachedPaymentCollection = new ArrayList<Payment>();
            for (Payment paymentCollectionPaymentToAttach : reservation.getPaymentCollection()) {
                paymentCollectionPaymentToAttach = em.getReference(paymentCollectionPaymentToAttach.getClass(), paymentCollectionPaymentToAttach.getPaymentId());
                attachedPaymentCollection.add(paymentCollectionPaymentToAttach);
            }
            reservation.setPaymentCollection(attachedPaymentCollection);
            Collection<ReservationItem> attachedReservationItemCollection = new ArrayList<ReservationItem>();
            for (ReservationItem reservationItemCollectionReservationItemToAttach : reservation.getReservationItemCollection()) {
                reservationItemCollectionReservationItemToAttach = em.getReference(reservationItemCollectionReservationItemToAttach.getClass(), reservationItemCollectionReservationItemToAttach.getReservationItemId());
                attachedReservationItemCollection.add(reservationItemCollectionReservationItemToAttach);
            }
            reservation.setReservationItemCollection(attachedReservationItemCollection);
            em.persist(reservation);
            if (username != null) {
                username.getReservationCollection().add(reservation);
                username = em.merge(username);
            }
            for (Payment paymentCollectionPayment : reservation.getPaymentCollection()) {
                Reservation oldReservationIdOfPaymentCollectionPayment = paymentCollectionPayment.getReservationId();
                paymentCollectionPayment.setReservationId(reservation);
                paymentCollectionPayment = em.merge(paymentCollectionPayment);
                if (oldReservationIdOfPaymentCollectionPayment != null) {
                    oldReservationIdOfPaymentCollectionPayment.getPaymentCollection().remove(paymentCollectionPayment);
                    oldReservationIdOfPaymentCollectionPayment = em.merge(oldReservationIdOfPaymentCollectionPayment);
                }
            }
            for (ReservationItem reservationItemCollectionReservationItem : reservation.getReservationItemCollection()) {
                Reservation oldReservationIdOfReservationItemCollectionReservationItem = reservationItemCollectionReservationItem.getReservationId();
                reservationItemCollectionReservationItem.setReservationId(reservation);
                reservationItemCollectionReservationItem = em.merge(reservationItemCollectionReservationItem);
                if (oldReservationIdOfReservationItemCollectionReservationItem != null) {
                    oldReservationIdOfReservationItemCollectionReservationItem.getReservationItemCollection().remove(reservationItemCollectionReservationItem);
                    oldReservationIdOfReservationItemCollectionReservationItem = em.merge(oldReservationIdOfReservationItemCollectionReservationItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservation reservation) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservation persistentReservation = em.find(Reservation.class, reservation.getReservationId());
            Customer usernameOld = persistentReservation.getUsername();
            Customer usernameNew = reservation.getUsername();
            Collection<Payment> paymentCollectionOld = persistentReservation.getPaymentCollection();
            Collection<Payment> paymentCollectionNew = reservation.getPaymentCollection();
            Collection<ReservationItem> reservationItemCollectionOld = persistentReservation.getReservationItemCollection();
            Collection<ReservationItem> reservationItemCollectionNew = reservation.getReservationItemCollection();
            List<String> illegalOrphanMessages = null;
            for (Payment paymentCollectionOldPayment : paymentCollectionOld) {
                if (!paymentCollectionNew.contains(paymentCollectionOldPayment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Payment " + paymentCollectionOldPayment + " since its reservationId field is not nullable.");
                }
            }
            for (ReservationItem reservationItemCollectionOldReservationItem : reservationItemCollectionOld) {
                if (!reservationItemCollectionNew.contains(reservationItemCollectionOldReservationItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReservationItem " + reservationItemCollectionOldReservationItem + " since its reservationId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                reservation.setUsername(usernameNew);
            }
            Collection<Payment> attachedPaymentCollectionNew = new ArrayList<Payment>();
            for (Payment paymentCollectionNewPaymentToAttach : paymentCollectionNew) {
                paymentCollectionNewPaymentToAttach = em.getReference(paymentCollectionNewPaymentToAttach.getClass(), paymentCollectionNewPaymentToAttach.getPaymentId());
                attachedPaymentCollectionNew.add(paymentCollectionNewPaymentToAttach);
            }
            paymentCollectionNew = attachedPaymentCollectionNew;
            reservation.setPaymentCollection(paymentCollectionNew);
            Collection<ReservationItem> attachedReservationItemCollectionNew = new ArrayList<ReservationItem>();
            for (ReservationItem reservationItemCollectionNewReservationItemToAttach : reservationItemCollectionNew) {
                reservationItemCollectionNewReservationItemToAttach = em.getReference(reservationItemCollectionNewReservationItemToAttach.getClass(), reservationItemCollectionNewReservationItemToAttach.getReservationItemId());
                attachedReservationItemCollectionNew.add(reservationItemCollectionNewReservationItemToAttach);
            }
            reservationItemCollectionNew = attachedReservationItemCollectionNew;
            reservation.setReservationItemCollection(reservationItemCollectionNew);
            reservation = em.merge(reservation);
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getReservationCollection().remove(reservation);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getReservationCollection().add(reservation);
                usernameNew = em.merge(usernameNew);
            }
            for (Payment paymentCollectionNewPayment : paymentCollectionNew) {
                if (!paymentCollectionOld.contains(paymentCollectionNewPayment)) {
                    Reservation oldReservationIdOfPaymentCollectionNewPayment = paymentCollectionNewPayment.getReservationId();
                    paymentCollectionNewPayment.setReservationId(reservation);
                    paymentCollectionNewPayment = em.merge(paymentCollectionNewPayment);
                    if (oldReservationIdOfPaymentCollectionNewPayment != null && !oldReservationIdOfPaymentCollectionNewPayment.equals(reservation)) {
                        oldReservationIdOfPaymentCollectionNewPayment.getPaymentCollection().remove(paymentCollectionNewPayment);
                        oldReservationIdOfPaymentCollectionNewPayment = em.merge(oldReservationIdOfPaymentCollectionNewPayment);
                    }
                }
            }
            for (ReservationItem reservationItemCollectionNewReservationItem : reservationItemCollectionNew) {
                if (!reservationItemCollectionOld.contains(reservationItemCollectionNewReservationItem)) {
                    Reservation oldReservationIdOfReservationItemCollectionNewReservationItem = reservationItemCollectionNewReservationItem.getReservationId();
                    reservationItemCollectionNewReservationItem.setReservationId(reservation);
                    reservationItemCollectionNewReservationItem = em.merge(reservationItemCollectionNewReservationItem);
                    if (oldReservationIdOfReservationItemCollectionNewReservationItem != null && !oldReservationIdOfReservationItemCollectionNewReservationItem.equals(reservation)) {
                        oldReservationIdOfReservationItemCollectionNewReservationItem.getReservationItemCollection().remove(reservationItemCollectionNewReservationItem);
                        oldReservationIdOfReservationItemCollectionNewReservationItem = em.merge(oldReservationIdOfReservationItemCollectionNewReservationItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservation.getReservationId();
                if (findReservation(id) == null) {
                    throw new NonexistentEntityException("The reservation with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservation reservation;
            try {
                reservation = em.getReference(Reservation.class, id);
                reservation.getReservationId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservation with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Payment> paymentCollectionOrphanCheck = reservation.getPaymentCollection();
            for (Payment paymentCollectionOrphanCheckPayment : paymentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reservation (" + reservation + ") cannot be destroyed since the Payment " + paymentCollectionOrphanCheckPayment + " in its paymentCollection field has a non-nullable reservationId field.");
            }
            Collection<ReservationItem> reservationItemCollectionOrphanCheck = reservation.getReservationItemCollection();
            for (ReservationItem reservationItemCollectionOrphanCheckReservationItem : reservationItemCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reservation (" + reservation + ") cannot be destroyed since the ReservationItem " + reservationItemCollectionOrphanCheckReservationItem + " in its reservationItemCollection field has a non-nullable reservationId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Customer username = reservation.getUsername();
            if (username != null) {
                username.getReservationCollection().remove(reservation);
                username = em.merge(username);
            }
            em.remove(reservation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservation> findReservationEntities() {
        return findReservationEntities(true, -1, -1);
    }

    public List<Reservation> findReservationEntities(int maxResults, int firstResult) {
        return findReservationEntities(false, maxResults, firstResult);
    }

    private List<Reservation> findReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reservation.class));
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

    public Reservation findReservation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservation.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reservation> rt = cq.from(Reservation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
