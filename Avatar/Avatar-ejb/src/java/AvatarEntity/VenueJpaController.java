/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import AvatarEntity.VenueLayoutJpaController;
import AvatarEntity.exceptions.IllegalOrphanException;
import AvatarEntity.exceptions.NonexistentEntityException;
import AvatarEntity.exceptions.PreexistingEntityException;
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
import java.util.Date;

/**
 *
 * @author Christian
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
        if (venue.getHallReservationCollection() == null) {
            venue.setHallReservationCollection(new ArrayList<HallReservation>());
        }
        if (venue.getVenueLayoutCollection() == null) {
            venue.setVenueLayoutCollection(new ArrayList<VenueLayout>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<HallReservation> attachedHallReservationCollection = new ArrayList<HallReservation>();
            for (HallReservation hallReservationCollectionHallReservationToAttach : venue.getHallReservationCollection()) {
                hallReservationCollectionHallReservationToAttach = em.getReference(hallReservationCollectionHallReservationToAttach.getClass(), hallReservationCollectionHallReservationToAttach.getReservationItemId());
                attachedHallReservationCollection.add(hallReservationCollectionHallReservationToAttach);
            }
            venue.setHallReservationCollection(attachedHallReservationCollection);
            Collection<VenueLayout> attachedVenueLayoutCollection = new ArrayList<VenueLayout>();
            for (VenueLayout venueLayoutCollectionVenueLayoutToAttach : venue.getVenueLayoutCollection()) {
                venueLayoutCollectionVenueLayoutToAttach = em.getReference(venueLayoutCollectionVenueLayoutToAttach.getClass(), venueLayoutCollectionVenueLayoutToAttach.getVenueNo());
                attachedVenueLayoutCollection.add(venueLayoutCollectionVenueLayoutToAttach);
            }
            venue.setVenueLayoutCollection(attachedVenueLayoutCollection);
            em.persist(venue);
            for (HallReservation hallReservationCollectionHallReservation : venue.getHallReservationCollection()) {
                Venue oldVenueNoOfHallReservationCollectionHallReservation = hallReservationCollectionHallReservation.getVenueNo();
                hallReservationCollectionHallReservation.setVenueNo(venue);
                hallReservationCollectionHallReservation = em.merge(hallReservationCollectionHallReservation);
                if (oldVenueNoOfHallReservationCollectionHallReservation != null) {
                    oldVenueNoOfHallReservationCollectionHallReservation.getHallReservationCollection().remove(hallReservationCollectionHallReservation);
                    oldVenueNoOfHallReservationCollectionHallReservation = em.merge(oldVenueNoOfHallReservationCollectionHallReservation);
                }
            }
            for (VenueLayout venueLayoutCollectionVenueLayout : venue.getVenueLayoutCollection()) {
                Venue oldVenueOfVenueLayoutCollectionVenueLayout = venueLayoutCollectionVenueLayout.getVenue();
                venueLayoutCollectionVenueLayout.setVenue(venue);
                venueLayoutCollectionVenueLayout = em.merge(venueLayoutCollectionVenueLayout);
                if (oldVenueOfVenueLayoutCollectionVenueLayout != null) {
                    oldVenueOfVenueLayoutCollectionVenueLayout.getVenueLayoutCollection().remove(venueLayoutCollectionVenueLayout);
                    oldVenueOfVenueLayoutCollectionVenueLayout = em.merge(oldVenueOfVenueLayoutCollectionVenueLayout);
                }
            }
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

    public void edit(Venue venue) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venue persistentVenue = em.find(Venue.class, venue.getVenueNo());
            Collection<HallReservation> hallReservationCollectionOld = persistentVenue.getHallReservationCollection();
            Collection<HallReservation> hallReservationCollectionNew = venue.getHallReservationCollection();
            Collection<VenueLayout> venueLayoutCollectionOld = persistentVenue.getVenueLayoutCollection();
            Collection<VenueLayout> venueLayoutCollectionNew = venue.getVenueLayoutCollection();
            List<String> illegalOrphanMessages = null;
            for (VenueLayout venueLayoutCollectionOldVenueLayout : venueLayoutCollectionOld) {
                if (!venueLayoutCollectionNew.contains(venueLayoutCollectionOldVenueLayout)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VenueLayout " + venueLayoutCollectionOldVenueLayout + " since its venue field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<HallReservation> attachedHallReservationCollectionNew = new ArrayList<HallReservation>();
            for (HallReservation hallReservationCollectionNewHallReservationToAttach : hallReservationCollectionNew) {
                hallReservationCollectionNewHallReservationToAttach = em.getReference(hallReservationCollectionNewHallReservationToAttach.getClass(), hallReservationCollectionNewHallReservationToAttach.getReservationItemId());
                attachedHallReservationCollectionNew.add(hallReservationCollectionNewHallReservationToAttach);
            }
            hallReservationCollectionNew = attachedHallReservationCollectionNew;
            venue.setHallReservationCollection(hallReservationCollectionNew);
            Collection<VenueLayout> attachedVenueLayoutCollectionNew = new ArrayList<VenueLayout>();
            VenueLayoutJpaController vljpa = new VenueLayoutJpaController();
            for (VenueLayout venueLayoutCollectionNewVenueLayoutToAttach : venueLayoutCollectionNew) {
                venueLayoutCollectionNewVenueLayoutToAttach = vljpa.findVenueLayout(venueLayoutCollectionNewVenueLayoutToAttach.getVenueNo(), venueLayoutCollectionNewVenueLayoutToAttach.getLayoutNo());
                attachedVenueLayoutCollectionNew.add(venueLayoutCollectionNewVenueLayoutToAttach);
            }
            venueLayoutCollectionNew = attachedVenueLayoutCollectionNew;
            venue.setVenueLayoutCollection(venueLayoutCollectionNew);
            venue = em.merge(venue);
            for (HallReservation hallReservationCollectionOldHallReservation : hallReservationCollectionOld) {
                if (!hallReservationCollectionNew.contains(hallReservationCollectionOldHallReservation)) {
                    hallReservationCollectionOldHallReservation.setVenueNo(null);
                    hallReservationCollectionOldHallReservation = em.merge(hallReservationCollectionOldHallReservation);
                }
            }
            for (HallReservation hallReservationCollectionNewHallReservation : hallReservationCollectionNew) {
                if (!hallReservationCollectionOld.contains(hallReservationCollectionNewHallReservation)) {
                    Venue oldVenueNoOfHallReservationCollectionNewHallReservation = hallReservationCollectionNewHallReservation.getVenueNo();
                    hallReservationCollectionNewHallReservation.setVenueNo(venue);
                    hallReservationCollectionNewHallReservation = em.merge(hallReservationCollectionNewHallReservation);
                    if (oldVenueNoOfHallReservationCollectionNewHallReservation != null && !oldVenueNoOfHallReservationCollectionNewHallReservation.equals(venue)) {
                        oldVenueNoOfHallReservationCollectionNewHallReservation.getHallReservationCollection().remove(hallReservationCollectionNewHallReservation);
                        oldVenueNoOfHallReservationCollectionNewHallReservation = em.merge(oldVenueNoOfHallReservationCollectionNewHallReservation);
                    }
                }
            }
            for (VenueLayout venueLayoutCollectionNewVenueLayout : venueLayoutCollectionNew) {
                if (!venueLayoutCollectionOld.contains(venueLayoutCollectionNewVenueLayout)) {
                    Venue oldVenueOfVenueLayoutCollectionNewVenueLayout = venueLayoutCollectionNewVenueLayout.getVenue();
                    venueLayoutCollectionNewVenueLayout.setVenue(venue);
                    venueLayoutCollectionNewVenueLayout = em.merge(venueLayoutCollectionNewVenueLayout);
                    if (oldVenueOfVenueLayoutCollectionNewVenueLayout != null && !oldVenueOfVenueLayoutCollectionNewVenueLayout.equals(venue)) {
                        oldVenueOfVenueLayoutCollectionNewVenueLayout.getVenueLayoutCollection().remove(venueLayoutCollectionNewVenueLayout);
                        oldVenueOfVenueLayoutCollectionNewVenueLayout = em.merge(oldVenueOfVenueLayoutCollectionNewVenueLayout);
                    }
                }
            }
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<VenueLayout> venueLayoutCollectionOrphanCheck = venue.getVenueLayoutCollection();
            for (VenueLayout venueLayoutCollectionOrphanCheckVenueLayout : venueLayoutCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venue (" + venue + ") cannot be destroyed since the VenueLayout " + venueLayoutCollectionOrphanCheckVenueLayout + " in its venueLayoutCollection field has a non-nullable venue field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<HallReservation> hallReservationCollection = venue.getHallReservationCollection();
            for (HallReservation hallReservationCollectionHallReservation : hallReservationCollection) {
                hallReservationCollectionHallReservation.setVenueNo(null);
                hallReservationCollectionHallReservation = em.merge(hallReservationCollectionHallReservation);
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

    public List<Venue> findUnused(Date useDate, Integer layoutNo, int capacity) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Venue.findUnused");
            q.setParameter("useDate", useDate);
            q.setParameter("layoutNo", layoutNo);
            q.setParameter("capacity", capacity);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Venue> findUnused(Date useDate, Integer layoutNo, int capacity, Integer reservationItemId) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Venue.findUnused");
            q.setParameter("reservationItemId", reservationItemId);
            q.setParameter("useDate", useDate);
            q.setParameter("layoutNo", layoutNo);
            q.setParameter("capacity", capacity);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
