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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author kamoe
 */
public class HallJpaController {

    public HallJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hall hall) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hall);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHall(hall.getProductId()) != null) {
                throw new PreexistingEntityException("Hall " + hall + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hall hall) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hall = em.merge(hall);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = hall.getProductId();
                if (findHall(id) == null) {
                    throw new NonexistentEntityException("The hall with id " + id + " no longer exists.");
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
            Hall hall;
            try {
                hall = em.getReference(Hall.class, id);
                hall.getProductId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hall with id " + id + " no longer exists.", enfe);
            }
            em.remove(hall);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hall> findHallEntities() {
        return findHallEntities(true, -1, -1);
    }

    public List<Hall> findHallEntities(int maxResults, int firstResult) {
        return findHallEntities(false, maxResults, firstResult);
    }

    private List<Hall> findHallEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hall.class));
            TypedQuery<Hall> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Hall> results = q.getResultList();
            return results;
        } finally {
            em.close();
        }
    }

    public Hall findHall(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hall.class, id);
        } finally {
            em.close();
        }
    }

    public int getHallCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hall> rt = cq.from(Hall.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
