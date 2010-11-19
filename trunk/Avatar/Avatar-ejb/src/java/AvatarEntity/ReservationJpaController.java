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
import java.util.Iterator;

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
        if (reservation.getReservationItemCollection() == null) {
            reservation.setReservationItemCollection(new ArrayList<ReservationItem>());
        }
        if (reservation.getReservationCollection() == null) {
            reservation.setReservationCollection(new ArrayList<Reservation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Payment payment = reservation.getPayment();
            if (payment != null) {
                payment = em.getReference(payment.getClass(), payment.getPaymentId());
                reservation.setPayment(payment);
            }
            Reservation parent = reservation.getParent();
            if (parent != null) {
                parent = em.getReference(parent.getClass(), parent.getReservationId());
                reservation.setParent(parent);
            }
            Customer username = reservation.getUsername();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                reservation.setUsername(username);
            }
            Collection<ReservationItem> attachedReservationItemCollection = new ArrayList<ReservationItem>();
            for (ReservationItem reservationItemCollectionReservationItemToAttach : reservation.getReservationItemCollection()) {
                reservationItemCollectionReservationItemToAttach = em.getReference(reservationItemCollectionReservationItemToAttach.getClass(), reservationItemCollectionReservationItemToAttach.getReservationItemId());
                attachedReservationItemCollection.add(reservationItemCollectionReservationItemToAttach);
            }
            reservation.setReservationItemCollection(attachedReservationItemCollection);
            Collection<Reservation> attachedReservationCollection = new ArrayList<Reservation>();
            for (Reservation reservationCollectionReservationToAttach : reservation.getReservationCollection()) {
                reservationCollectionReservationToAttach = em.getReference(reservationCollectionReservationToAttach.getClass(), reservationCollectionReservationToAttach.getReservationId());
                attachedReservationCollection.add(reservationCollectionReservationToAttach);
            }
            reservation.setReservationCollection(attachedReservationCollection);
            em.persist(reservation);
            if (payment != null) {
                Reservation oldReservationIdOfPayment = payment.getReservationId();
                if (oldReservationIdOfPayment != null) {
                    oldReservationIdOfPayment.setPayment(null);
                    oldReservationIdOfPayment = em.merge(oldReservationIdOfPayment);
                }
                payment.setReservationId(reservation);
                payment = em.merge(payment);
            }
            if (parent != null) {
                parent.getReservationCollection().add(reservation);
                parent = em.merge(parent);
            }
            if (username != null) {
                username.getReservationCollection().add(reservation);
                username = em.merge(username);
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
            for (Reservation reservationCollectionReservation : reservation.getReservationCollection()) {
                Reservation oldParentOfReservationCollectionReservation = reservationCollectionReservation.getParent();
                reservationCollectionReservation.setParent(reservation);
                reservationCollectionReservation = em.merge(reservationCollectionReservation);
                if (oldParentOfReservationCollectionReservation != null) {
                    oldParentOfReservationCollectionReservation.getReservationCollection().remove(reservationCollectionReservation);
                    oldParentOfReservationCollectionReservation = em.merge(oldParentOfReservationCollectionReservation);
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
            Payment paymentOld = persistentReservation.getPayment();
            Payment paymentNew = reservation.getPayment();
            Reservation parentOld = persistentReservation.getParent();
            Reservation parentNew = reservation.getParent();
            Customer usernameOld = persistentReservation.getUsername();
            Customer usernameNew = reservation.getUsername();
            Collection<ReservationItem> reservationItemCollectionOld = persistentReservation.getReservationItemCollection();
            Collection<ReservationItem> reservationItemCollectionNew = reservation.getReservationItemCollection();
            Collection<Reservation> reservationCollectionOld = persistentReservation.getReservationCollection();
            Collection<Reservation> reservationCollectionNew = reservation.getReservationCollection();
            List<String> illegalOrphanMessages = null;
            if (paymentOld != null && !paymentOld.equals(paymentNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Payment " + paymentOld + " since its reservationId field is not nullable.");
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
            if (paymentNew != null) {
                paymentNew = em.getReference(paymentNew.getClass(), paymentNew.getPaymentId());
                reservation.setPayment(paymentNew);
            }
            if (parentNew != null) {
                parentNew = em.getReference(parentNew.getClass(), parentNew.getReservationId());
                reservation.setParent(parentNew);
            }
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                reservation.setUsername(usernameNew);
            }
            Collection<ReservationItem> attachedReservationItemCollectionNew = new ArrayList<ReservationItem>();
            for (ReservationItem reservationItemCollectionNewReservationItemToAttach : reservationItemCollectionNew) {
                reservationItemCollectionNewReservationItemToAttach = em.getReference(reservationItemCollectionNewReservationItemToAttach.getClass(), reservationItemCollectionNewReservationItemToAttach.getReservationItemId());
                attachedReservationItemCollectionNew.add(reservationItemCollectionNewReservationItemToAttach);
            }
            reservationItemCollectionNew = attachedReservationItemCollectionNew;
            reservation.setReservationItemCollection(reservationItemCollectionNew);
            Collection<Reservation> attachedReservationCollectionNew = new ArrayList<Reservation>();
            for (Reservation reservationCollectionNewReservationToAttach : reservationCollectionNew) {
                reservationCollectionNewReservationToAttach = em.getReference(reservationCollectionNewReservationToAttach.getClass(), reservationCollectionNewReservationToAttach.getReservationId());
                attachedReservationCollectionNew.add(reservationCollectionNewReservationToAttach);
            }
            reservationCollectionNew = attachedReservationCollectionNew;
            reservation.setReservationCollection(reservationCollectionNew);
            reservation = em.merge(reservation);
            if (paymentNew != null && !paymentNew.equals(paymentOld)) {
                Reservation oldReservationIdOfPayment = paymentNew.getReservationId();
                if (oldReservationIdOfPayment != null) {
                    oldReservationIdOfPayment.setPayment(null);
                    oldReservationIdOfPayment = em.merge(oldReservationIdOfPayment);
                }
                paymentNew.setReservationId(reservation);
                paymentNew = em.merge(paymentNew);
            }
            if (parentOld != null && !parentOld.equals(parentNew)) {
                parentOld.getReservationCollection().remove(reservation);
                parentOld = em.merge(parentOld);
            }
            if (parentNew != null && !parentNew.equals(parentOld)) {
                parentNew.getReservationCollection().add(reservation);
                parentNew = em.merge(parentNew);
            }
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getReservationCollection().remove(reservation);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getReservationCollection().add(reservation);
                usernameNew = em.merge(usernameNew);
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
            for (Reservation reservationCollectionOldReservation : reservationCollectionOld) {
                if (!reservationCollectionNew.contains(reservationCollectionOldReservation)) {
                    reservationCollectionOldReservation.setParent(null);
                    reservationCollectionOldReservation = em.merge(reservationCollectionOldReservation);
                }
            }
            for (Reservation reservationCollectionNewReservation : reservationCollectionNew) {
                if (!reservationCollectionOld.contains(reservationCollectionNewReservation)) {
                    Reservation oldParentOfReservationCollectionNewReservation = reservationCollectionNewReservation.getParent();
                    reservationCollectionNewReservation.setParent(reservation);
                    reservationCollectionNewReservation = em.merge(reservationCollectionNewReservation);
                    if (oldParentOfReservationCollectionNewReservation != null && !oldParentOfReservationCollectionNewReservation.equals(reservation)) {
                        oldParentOfReservationCollectionNewReservation.getReservationCollection().remove(reservationCollectionNewReservation);
                        oldParentOfReservationCollectionNewReservation = em.merge(oldParentOfReservationCollectionNewReservation);
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
            Reservation parent = reservation.getParent();
            if (parent != null) {
                parent.getReservationCollection().remove(reservation);
                parent = em.merge(parent);
            }
            Customer username = reservation.getUsername();
            if (username != null) {
                username.getReservationCollection().remove(reservation);
                username = em.merge(username);
            }
            Collection<Reservation> reservationCollection = reservation.getReservationCollection();
            for (Reservation reservationCollectionReservation : reservationCollection) {
                reservationCollectionReservation.setParent(null);
                reservationCollectionReservation = em.merge(reservationCollectionReservation);
            }
            em.remove(reservation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Find All Reservations
    public List<Reservation> findReservationEntities() {
        return findReservationEntities(true, -1, -1);
    }

    public List<Reservation> findReservationEntities(int maxResults, int firstResult) {
        return findReservationEntities(false, maxResults, firstResult);
    }

    private List<Reservation> findReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Reservation.findAll");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // Find Only Parent Reservations
    public List<Reservation> findParentReservationEntities() {
        return findParentReservationEntities(true, -1, -1);
    }

    public List<Reservation> findParentReservationEntities(int maxResults, int firstResult) {
        return findParentReservationEntities(false, maxResults, firstResult);
    }

    private List<Reservation> findParentReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Reservation.findParent");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getParentReservationCount() {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Reservation.countParent");
        return ((Long) q.getSingleResult()).intValue();
    }

    // Find Unpaid Reservations
    public List<Reservation> findUnpaidReservationEntities() {
        return findUnpaidReservationEntities(true, -1, -1);
    }

    public List<Reservation> findUnpaidReservationEntities(int maxResults, int firstResult) {
        return findUnpaidReservationEntities(false, maxResults, firstResult);
    }

    private List<Reservation> findUnpaidReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Reservation.findUnpaid");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getUnpaidReservationCount() {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Reservation.countUnpaid");
        return ((Long) q.getSingleResult()).intValue();
    }

    // Find Unverified Reservations
    public List<Reservation> findUnverifiedReservationEntities() {
        return findUnverifiedReservationEntities(true, -1, -1);
    }

    public List<Reservation> findUnverifiedReservationEntities(int maxResults, int firstResult) {
        return findUnverifiedReservationEntities(false, maxResults, firstResult);
    }

    private List<Reservation> findUnverifiedReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Reservation.findUnverified");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getUnverifiedReservationCount() {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Reservation.countUnverified");
        return ((Long) q.getSingleResult()).intValue();
    }

    // Find Verified Reservations
    public List<Reservation> findVerifiedReservationEntities() {
        return findVerifiedReservationEntities(true, -1, -1);
    }

    public List<Reservation> findVerifiedReservationEntities(int maxResults, int firstResult) {
        return findVerifiedReservationEntities(false, maxResults, firstResult);
    }

    private List<Reservation> findVerifiedReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Reservation.findVerified");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getVerifiedReservationCount() {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Reservation.countVerified");
        return ((Long) q.getSingleResult()).intValue();
    }

    public Reservation findReservation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservation.class, id);
        } finally {
            em.close();
        }
    }

    public List<Reservation> findPaid() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Reservation.findPaid");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Reservation> findOnlineReservationByName(String name) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Reservation.findReservationByName");
            q.setParameter("name", name);
            q.setParameter("isOnspot", false);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Reservation> findOnspotReservationByName(String name) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Reservation.findReservationByName");
            q.setParameter("name", name);
            q.setParameter("isOnspot", true);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Reservation> findUnpaidOnlineReservation() {
        List<Reservation> result = new ArrayList<Reservation>();
        List<Reservation> all = findReservationEntities();
        Iterator<Reservation> i = all.iterator();
        while (i.hasNext()) {
            Reservation r = i.next();
            if (r.getIsOnspot() == false) {
                if (r.getPayment() == null) {
                    result.add(r);
                }
            }
        }
        return result;
    }

    public List<Reservation> findUnpaidOnlineReservationByName(String name) {
        List<Reservation> result = new ArrayList<Reservation>();
        List<Reservation> all = findOnlineReservationByName(name);
        List<Reservation> paid = findPaidOnlineReservationByName(name);
        Iterator<Reservation> i = all.iterator();
        while (i.hasNext()) {
            Reservation r = i.next();
            if (!paid.contains(r)) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Reservation> findPaidOnlineReservationByName(String name) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Reservation.findPaidOnlineReservationByName");
            q.setParameter("name", name);
            return q.getResultList();
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
