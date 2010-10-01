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
public class HallReservationJpaController {

    public HallReservationJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HallReservation hallReservation) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hallReservation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHallReservation(hallReservation.getReservationTime()) != null) {
                throw new PreexistingEntityException("HallReservation " + hallReservation + " already exists.", ex);
            }
            throw ex;
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
            hallReservation = em.merge(hallReservation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Date id = hallReservation.getReservationTime();
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

    public void destroy(Date id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HallReservation hallReservation;
            try {
                hallReservation = em.getReference(HallReservation.class, id);
                hallReservation.getReservationTime();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hallReservation with id " + id + " no longer exists.", enfe);
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

    public HallReservation findHallReservation(Date id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HallReservation.class, id);
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

}
