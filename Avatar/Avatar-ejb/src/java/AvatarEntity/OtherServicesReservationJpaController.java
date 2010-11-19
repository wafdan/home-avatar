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
public class OtherServicesReservationJpaController {

    public OtherServicesReservationJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OtherServicesReservation otherServicesReservation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OtherServices productId = otherServicesReservation.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getProductId());
                otherServicesReservation.setProductId(productId);
            }
            Reservation reservationId = otherServicesReservation.getReservationId();
            if (reservationId != null) {
                reservationId = em.getReference(reservationId.getClass(), reservationId.getReservationId());
                otherServicesReservation.setReservationId(reservationId);
            }
            em.persist(otherServicesReservation);
            if (productId != null) {
                productId.getOtherServicesReservationCollection().add(otherServicesReservation);
                productId = em.merge(productId);
            }
            if (reservationId != null) {
                reservationId.getReservationItemCollection().add(otherServicesReservation);
                reservationId = em.merge(reservationId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OtherServicesReservation otherServicesReservation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OtherServicesReservation persistentOtherServicesReservation = em.find(OtherServicesReservation.class, otherServicesReservation.getReservationItemId());
            OtherServices productIdOld = persistentOtherServicesReservation.getProductId();
            OtherServices productIdNew = otherServicesReservation.getProductId();
            Reservation reservationIdOld = persistentOtherServicesReservation.getReservationId();
            Reservation reservationIdNew = otherServicesReservation.getReservationId();
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getProductId());
                otherServicesReservation.setProductId(productIdNew);
            }
            if (reservationIdNew != null) {
                reservationIdNew = em.getReference(reservationIdNew.getClass(), reservationIdNew.getReservationId());
                otherServicesReservation.setReservationId(reservationIdNew);
            }
            otherServicesReservation = em.merge(otherServicesReservation);
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getOtherServicesReservationCollection().remove(otherServicesReservation);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getOtherServicesReservationCollection().add(otherServicesReservation);
                productIdNew = em.merge(productIdNew);
            }
            if (reservationIdOld != null && !reservationIdOld.equals(reservationIdNew)) {
                reservationIdOld.getReservationItemCollection().remove(otherServicesReservation);
                reservationIdOld = em.merge(reservationIdOld);
            }
            if (reservationIdNew != null && !reservationIdNew.equals(reservationIdOld)) {
                reservationIdNew.getReservationItemCollection().add(otherServicesReservation);
                reservationIdNew = em.merge(reservationIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = otherServicesReservation.getReservationItemId();
                if (findOtherServicesReservation(id) == null) {
                    throw new NonexistentEntityException("The otherServicesReservation with id " + id + " no longer exists.");
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
            OtherServicesReservation otherServicesReservation;
            try {
                otherServicesReservation = em.getReference(OtherServicesReservation.class, id);
                otherServicesReservation.getReservationItemId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The otherServicesReservation with id " + id + " no longer exists.", enfe);
            }
            OtherServices productId = otherServicesReservation.getProductId();
            if (productId != null) {
                productId.getOtherServicesReservationCollection().remove(otherServicesReservation);
                productId = em.merge(productId);
            }
            Reservation reservationId = otherServicesReservation.getReservationId();
            if (reservationId != null) {
                reservationId.getReservationItemCollection().remove(otherServicesReservation);
                reservationId = em.merge(reservationId);
            }
            em.remove(otherServicesReservation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OtherServicesReservation> findOtherServicesReservationEntities() {
        return findOtherServicesReservationEntities(true, -1, -1);
    }

    public List<OtherServicesReservation> findOtherServicesReservationEntities(int maxResults, int firstResult) {
        return findOtherServicesReservationEntities(false, maxResults, firstResult);
    }

    private List<OtherServicesReservation> findOtherServicesReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("OtherServicesReservation.findAll");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OtherServicesReservation findOtherServicesReservation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OtherServicesReservation.class, id);
        } finally {
            em.close();
        }
    }

    public List<OtherServicesReservation> findByPeriod(Date from, Date to) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("OtherServicesReservation.findByPeriod");
            q.setParameter("from", from);
            q.setParameter("to", to);
            return (List<OtherServicesReservation>) q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getOtherServicesReservationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OtherServicesReservation> rt = cq.from(OtherServicesReservation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
