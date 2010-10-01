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
public class VenueJpaController {

    public VenueJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venue venue) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(venue);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVenue(venue.getVenueNo()) != null) {
                throw new PreexistingEntityException("Venue " + venue + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venue venue) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            venue = em.merge(venue);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = venue.getVenueNo();
                if (findVenue(id) == null) {
                    throw new NonexistentEntityException("The venue with id " + id + " no longer exists.");
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
            Venue venue;
            try {
                venue = em.getReference(Venue.class, id);
                venue.getVenueNo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venue with id " + id + " no longer exists.", enfe);
            }
            em.remove(venue);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venue> findVenueEntities() {
        return findVenueEntities(true, -1, -1);
    }

    public List<Venue> findVenueEntities(int maxResults, int firstResult) {
        return findVenueEntities(false, maxResults, firstResult);
    }

    private List<Venue> findVenueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venue.class));
            TypedQuery<Venue> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Venue> results = q.getResultList();
            return results;
        } finally {
            em.close();
        }
    }

    public Venue findVenue(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venue.class, id);
        } finally {
            em.close();
        }
    }

    public int getVenueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venue> rt = cq.from(Venue.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
