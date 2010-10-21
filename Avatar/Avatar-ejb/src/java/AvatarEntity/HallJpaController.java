/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

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

/**
 *
 * @author Christian
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
        if (hall.getHallReservationCollection() == null) {
            hall.setHallReservationCollection(new ArrayList<HallReservation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<HallReservation> attachedHallReservationCollection = new ArrayList<HallReservation>();
            for (HallReservation hallReservationCollectionHallReservationToAttach : hall.getHallReservationCollection()) {
                hallReservationCollectionHallReservationToAttach = em.getReference(hallReservationCollectionHallReservationToAttach.getClass(), hallReservationCollectionHallReservationToAttach.getReservationItemId());
                attachedHallReservationCollection.add(hallReservationCollectionHallReservationToAttach);
            }
            hall.setHallReservationCollection(attachedHallReservationCollection);
            em.persist(hall);
            for (HallReservation hallReservationCollectionHallReservation : hall.getHallReservationCollection()) {
                Hall oldProductIdOfHallReservationCollectionHallReservation = hallReservationCollectionHallReservation.getProductId();
                hallReservationCollectionHallReservation.setProductId(hall);
                hallReservationCollectionHallReservation = em.merge(hallReservationCollectionHallReservation);
                if (oldProductIdOfHallReservationCollectionHallReservation != null) {
                    oldProductIdOfHallReservationCollectionHallReservation.getHallReservationCollection().remove(hallReservationCollectionHallReservation);
                    oldProductIdOfHallReservationCollectionHallReservation = em.merge(oldProductIdOfHallReservationCollectionHallReservation);
                }
            }
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

    public void edit(Hall hall) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hall persistentHall = em.find(Hall.class, hall.getProductId());
            Collection<HallReservation> hallReservationCollectionOld = persistentHall.getHallReservationCollection();
            Collection<HallReservation> hallReservationCollectionNew = hall.getHallReservationCollection();
            List<String> illegalOrphanMessages = null;
            for (HallReservation hallReservationCollectionOldHallReservation : hallReservationCollectionOld) {
                if (!hallReservationCollectionNew.contains(hallReservationCollectionOldHallReservation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HallReservation " + hallReservationCollectionOldHallReservation + " since its productId field is not nullable.");
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
            hall.setHallReservationCollection(hallReservationCollectionNew);
            hall = em.merge(hall);
            for (HallReservation hallReservationCollectionNewHallReservation : hallReservationCollectionNew) {
                if (!hallReservationCollectionOld.contains(hallReservationCollectionNewHallReservation)) {
                    Hall oldProductIdOfHallReservationCollectionNewHallReservation = hallReservationCollectionNewHallReservation.getProductId();
                    hallReservationCollectionNewHallReservation.setProductId(hall);
                    hallReservationCollectionNewHallReservation = em.merge(hallReservationCollectionNewHallReservation);
                    if (oldProductIdOfHallReservationCollectionNewHallReservation != null && !oldProductIdOfHallReservationCollectionNewHallReservation.equals(hall)) {
                        oldProductIdOfHallReservationCollectionNewHallReservation.getHallReservationCollection().remove(hallReservationCollectionNewHallReservation);
                        oldProductIdOfHallReservationCollectionNewHallReservation = em.merge(oldProductIdOfHallReservationCollectionNewHallReservation);
                    }
                }
            }
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<HallReservation> hallReservationCollectionOrphanCheck = hall.getHallReservationCollection();
            for (HallReservation hallReservationCollectionOrphanCheckHallReservation : hallReservationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hall (" + hall + ") cannot be destroyed since the HallReservation " + hallReservationCollectionOrphanCheckHallReservation + " in its hallReservationCollection field has a non-nullable productId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
