/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import AvatarEntity.exceptions.NonexistentEntityException;
import AvatarEntity.exceptions.PreexistingEntityException;
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
 * @author kamoe
 */
public class RoomReservationJpaController {

    public RoomReservationJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RoomReservation roomReservation) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(roomReservation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRoomReservation(roomReservation.getReservationTime()) != null) {
                throw new PreexistingEntityException("RoomReservation " + roomReservation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RoomReservation roomReservation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            roomReservation = em.merge(roomReservation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Date id = roomReservation.getReservationTime();
                if (findRoomReservation(id) == null) {
                    throw new NonexistentEntityException("The roomReservation with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Date id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RoomReservation roomReservation;
            try {
                roomReservation = em.getReference(RoomReservation.class, id);
                roomReservation.getReservationTime();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roomReservation with id " + id + " no longer exists.", enfe);
            }
            em.remove(roomReservation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RoomReservation> findRoomReservationEntities() {
        return findRoomReservationEntities(true, -1, -1);
    }

    public List<RoomReservation> findRoomReservationEntities(int maxResults, int firstResult) {
        return findRoomReservationEntities(false, maxResults, firstResult);
    }

    private List<RoomReservation> findRoomReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RoomReservation.class));
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

    public RoomReservation findRoomReservation(Date id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RoomReservation.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomReservationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RoomReservation> rt = cq.from(RoomReservation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
