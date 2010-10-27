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
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Christian
 */
public class VenueLayoutJpaController {

    public VenueLayoutJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VenueLayout venueLayout) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venue venue = venueLayout.getVenue();
            if (venue != null) {
                venue = em.getReference(venue.getClass(), venue.getVenueNo());
                venueLayout.setVenue(venue);
            }
            em.persist(venueLayout);
            if (venue != null) {
                venue.getVenueLayoutCollection().add(venueLayout);
                venue = em.merge(venue);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVenueLayout(venueLayout.getVenueNo()) != null) {
                throw new PreexistingEntityException("VenueLayout " + venueLayout + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VenueLayout venueLayout) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VenueLayout persistentVenueLayout = findVenueLayout(venueLayout.getVenueNo(), venueLayout.getLayoutNo());
            //VenueLayout persistentVenueLayout = em.find(VenueLayout.class, venueLayout.getVenueNo());
            Venue venueOld = persistentVenueLayout.getVenue();
            Venue venueNew = venueLayout.getVenue();
            if (venueNew != null) {
                venueNew = em.getReference(venueNew.getClass(), venueNew.getVenueNo());
                venueLayout.setVenue(venueNew);
            }
            venueLayout = em.merge(venueLayout);
            if (venueOld != null && !venueOld.equals(venueNew)) {
                venueOld.getVenueLayoutCollection().remove(venueLayout);
                venueOld = em.merge(venueOld);
            }
            if (venueNew != null && !venueNew.equals(venueOld)) {
                venueNew.getVenueLayoutCollection().add(venueLayout);
                venueNew = em.merge(venueNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = venueLayout.getVenueNo();
                if (findVenueLayout(id) == null) {
                    throw new NonexistentEntityException("The venueLayout with id " + id + " no longer exists.");
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
            VenueLayout venueLayout;
            try {
                venueLayout = em.getReference(VenueLayout.class, id);
                venueLayout.getVenueNo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venueLayout with id " + id + " no longer exists.", enfe);
            }
            Venue venue = venueLayout.getVenue();
            if (venue != null) {
                venue.getVenueLayoutCollection().remove(venueLayout);
                venue = em.merge(venue);
            }
            em.remove(venueLayout);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VenueLayout> findVenueLayoutEntities() {
        return findVenueLayoutEntities(true, -1, -1);
    }

    public List<VenueLayout> findVenueLayoutEntities(int maxResults, int firstResult) {
        return findVenueLayoutEntities(false, maxResults, firstResult);
    }

    private List<VenueLayout> findVenueLayoutEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VenueLayout.class));
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

    public VenueLayout findVenueLayout(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VenueLayout.class, id);
        } finally {
            em.close();
        }
    }

    public VenueLayout findVenueLayout(String venueNo, Integer layoutNo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("VenueLayout.findVenueLayout");
            q.setParameter("venueNo", venueNo);
            q.setParameter("layoutNo", layoutNo);
            Object res = q.getSingleResult();
            return (VenueLayout) res;
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getVenueLayoutCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VenueLayout> rt = cq.from(VenueLayout.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
