/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import AvatarEntity.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Christian
 */
public class HallReservationJpaController {

    public HallReservationJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HallReservation hallReservation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hall productId = hallReservation.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getProductId());
                hallReservation.setProductId(productId);
            }
            Venue venueNo = hallReservation.getVenueNo();
            if (venueNo != null) {
                venueNo = em.getReference(venueNo.getClass(), venueNo.getVenueNo());
                hallReservation.setVenueNo(venueNo);
            }
            Reservation reservationId = hallReservation.getReservationId();
            if (reservationId != null) {
                reservationId = em.getReference(reservationId.getClass(), reservationId.getReservationId());
                hallReservation.setReservationId(reservationId);
            }
            em.persist(hallReservation);
            if (productId != null) {
                productId.getHallReservationCollection().add(hallReservation);
                productId = em.merge(productId);
            }
            if (venueNo != null) {
                venueNo.getHallReservationCollection().add(hallReservation);
                venueNo = em.merge(venueNo);
            }
            if (reservationId != null) {
                reservationId.getReservationItemCollection().add(hallReservation);
                reservationId = em.merge(reservationId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HallReservation hallReservation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HallReservation persistentHallReservation = em.find(HallReservation.class, hallReservation.getReservationItemId());
            Hall productIdOld = persistentHallReservation.getProductId();
            Hall productIdNew = hallReservation.getProductId();
            Venue venueNoOld = persistentHallReservation.getVenueNo();
            Venue venueNoNew = hallReservation.getVenueNo();
            Reservation reservationIdOld = persistentHallReservation.getReservationId();
            Reservation reservationIdNew = hallReservation.getReservationId();
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getProductId());
                hallReservation.setProductId(productIdNew);
            }
            if (venueNoNew != null) {
                venueNoNew = em.getReference(venueNoNew.getClass(), venueNoNew.getVenueNo());
                hallReservation.setVenueNo(venueNoNew);
            }
            if (reservationIdNew != null) {
                reservationIdNew = em.getReference(reservationIdNew.getClass(), reservationIdNew.getReservationId());
                hallReservation.setReservationId(reservationIdNew);
            }
            hallReservation = em.merge(hallReservation);
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getHallReservationCollection().remove(hallReservation);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getHallReservationCollection().add(hallReservation);
                productIdNew = em.merge(productIdNew);
            }
            if (venueNoOld != null && !venueNoOld.equals(venueNoNew)) {
                venueNoOld.getHallReservationCollection().remove(hallReservation);
                venueNoOld = em.merge(venueNoOld);
            }
            if (venueNoNew != null && !venueNoNew.equals(venueNoOld)) {
                venueNoNew.getHallReservationCollection().add(hallReservation);
                venueNoNew = em.merge(venueNoNew);
            }
            if (reservationIdOld != null && !reservationIdOld.equals(reservationIdNew)) {
                reservationIdOld.getReservationItemCollection().remove(hallReservation);
                reservationIdOld = em.merge(reservationIdOld);
            }
            if (reservationIdNew != null && !reservationIdNew.equals(reservationIdOld)) {
                reservationIdNew.getReservationItemCollection().add(hallReservation);
                reservationIdNew = em.merge(reservationIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hallReservation.getReservationItemId();
                if (findHallReservation(id) == null) {
                    throw new NonexistentEntityException("The hallReservation with id " + id + " no longer exists.");
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
            HallReservation hallReservation;
            try {
                hallReservation = em.getReference(HallReservation.class, id);
                hallReservation.getReservationItemId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hallReservation with id " + id + " no longer exists.", enfe);
            }
            Hall productId = hallReservation.getProductId();
            if (productId != null) {
                productId.getHallReservationCollection().remove(hallReservation);
                productId = em.merge(productId);
            }
            Venue venueNo = hallReservation.getVenueNo();
            if (venueNo != null) {
                venueNo.getHallReservationCollection().remove(hallReservation);
                venueNo = em.merge(venueNo);
            }
            Reservation reservationId = hallReservation.getReservationId();
            if (reservationId != null) {
                reservationId.getReservationItemCollection().remove(hallReservation);
                reservationId = em.merge(reservationId);
            }
            em.remove(hallReservation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HallReservation> findHallReservationEntities() {
        return findHallReservationEntities(true, -1, -1);
    }

    public List<HallReservation> findHallReservationEntities(int maxResults, int firstResult) {
        return findHallReservationEntities(false, maxResults, firstResult);
    }

    private List<HallReservation> findHallReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HallReservation.class));
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

    public HallReservation findHallReservation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HallReservation.class, id);
        } finally {
            em.close();
        }
    }

    public List<HallReservation> findByPeriod(Date from, Date to) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("HallReservation.findByPeriod");
            q.setParameter("from", from);
            q.setParameter("to", to);
            return (List<HallReservation>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getHallReservationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HallReservation> rt = cq.from(HallReservation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<HallReservation> findReservationByUseDate(Date useDate ){
        EntityManager em=getEntityManager();
        Query query=em.createNamedQuery(HallReservation.findByUseDate);
        query.setParameter("useDate", useDate);
        return query.getResultList();
    }

}
