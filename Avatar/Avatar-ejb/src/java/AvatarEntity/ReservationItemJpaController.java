/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import AvatarEntity.exceptions.NonexistentEntityException;
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
public class ReservationItemJpaController {

    public ReservationItemJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReservationItem reservationItem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservation reservationId = reservationItem.getReservationId();
            if (reservationId != null) {
                reservationId = em.getReference(reservationId.getClass(), reservationId.getReservationId());
                reservationItem.setReservationId(reservationId);
            }
            em.persist(reservationItem);
            if (reservationId != null) {
                reservationId.getReservationItemCollection().add(reservationItem);
                reservationId = em.merge(reservationId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReservationItem reservationItem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReservationItem persistentReservationItem = em.find(ReservationItem.class, reservationItem.getReservationItemId());
            Reservation reservationIdOld = persistentReservationItem.getReservationId();
            Reservation reservationIdNew = reservationItem.getReservationId();
            if (reservationIdNew != null) {
                reservationIdNew = em.getReference(reservationIdNew.getClass(), reservationIdNew.getReservationId());
                reservationItem.setReservationId(reservationIdNew);
            }
            reservationItem = em.merge(reservationItem);
            if (reservationIdOld != null && !reservationIdOld.equals(reservationIdNew)) {
                reservationIdOld.getReservationItemCollection().remove(reservationItem);
                reservationIdOld = em.merge(reservationIdOld);
            }
            if (reservationIdNew != null && !reservationIdNew.equals(reservationIdOld)) {
                reservationIdNew.getReservationItemCollection().add(reservationItem);
                reservationIdNew = em.merge(reservationIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservationItem.getReservationItemId();
                if (findReservationItem(id) == null) {
                    throw new NonexistentEntityException("The reservationItem with id " + id + " no longer exists.");
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
            ReservationItem reservationItem;
            try {
                reservationItem = em.getReference(ReservationItem.class, id);
                reservationItem.getReservationItemId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservationItem with id " + id + " no longer exists.", enfe);
            }
            Reservation reservationId = reservationItem.getReservationId();
            if (reservationId != null) {
                reservationId.getReservationItemCollection().remove(reservationItem);
                reservationId = em.merge(reservationId);
            }
            em.remove(reservationItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReservationItem> findReservationItemEntities() {
        return findReservationItemEntities(true, -1, -1);
    }

    public List<ReservationItem> findReservationItemEntities(int maxResults, int firstResult) {
        return findReservationItemEntities(false, maxResults, firstResult);
    }

    private List<ReservationItem> findReservationItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReservationItem.class));
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

    public ReservationItem findReservationItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReservationItem.class, id);
        } finally {
            em.close();
        }
    }
    

    public int getReservationItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReservationItem> rt = cq.from(ReservationItem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
