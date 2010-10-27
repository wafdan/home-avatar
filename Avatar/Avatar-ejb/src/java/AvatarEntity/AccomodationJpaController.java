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
public class AccomodationJpaController {

    public AccomodationJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accomodation accomodation) throws PreexistingEntityException, Exception {
        if (accomodation.getRoomCollection() == null) {
            accomodation.setRoomCollection(new ArrayList<Room>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Room> attachedRoomCollection = new ArrayList<Room>();
            for (Room roomCollectionRoomToAttach : accomodation.getRoomCollection()) {
                roomCollectionRoomToAttach = em.getReference(roomCollectionRoomToAttach.getClass(), roomCollectionRoomToAttach.getRoomNo());
                attachedRoomCollection.add(roomCollectionRoomToAttach);
            }
            accomodation.setRoomCollection(attachedRoomCollection);
            em.persist(accomodation);
            for (Room roomCollectionRoom : accomodation.getRoomCollection()) {
                Accomodation oldProductIdOfRoomCollectionRoom = roomCollectionRoom.getProductId();
                roomCollectionRoom.setProductId(accomodation);
                roomCollectionRoom = em.merge(roomCollectionRoom);
                if (oldProductIdOfRoomCollectionRoom != null) {
                    oldProductIdOfRoomCollectionRoom.getRoomCollection().remove(roomCollectionRoom);
                    oldProductIdOfRoomCollectionRoom = em.merge(oldProductIdOfRoomCollectionRoom);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccomodation(accomodation.getProductId()) != null) {
                throw new PreexistingEntityException("Accomodation " + accomodation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accomodation accomodation) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accomodation persistentAccomodation = em.find(Accomodation.class, accomodation.getProductId());
            Collection<Room> roomCollectionOld = persistentAccomodation.getRoomCollection();
            Collection<Room> roomCollectionNew = accomodation.getRoomCollection();
            List<String> illegalOrphanMessages = null;
            for (Room roomCollectionOldRoom : roomCollectionOld) {
                if (!roomCollectionNew.contains(roomCollectionOldRoom)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Room " + roomCollectionOldRoom + " since its productId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Room> attachedRoomCollectionNew = new ArrayList<Room>();
            for (Room roomCollectionNewRoomToAttach : roomCollectionNew) {
                roomCollectionNewRoomToAttach = em.getReference(roomCollectionNewRoomToAttach.getClass(), roomCollectionNewRoomToAttach.getRoomNo());
                attachedRoomCollectionNew.add(roomCollectionNewRoomToAttach);
            }
            roomCollectionNew = attachedRoomCollectionNew;
            accomodation.setRoomCollection(roomCollectionNew);
            accomodation = em.merge(accomodation);
            for (Room roomCollectionNewRoom : roomCollectionNew) {
                if (!roomCollectionOld.contains(roomCollectionNewRoom)) {
                    Accomodation oldProductIdOfRoomCollectionNewRoom = roomCollectionNewRoom.getProductId();
                    roomCollectionNewRoom.setProductId(accomodation);
                    roomCollectionNewRoom = em.merge(roomCollectionNewRoom);
                    if (oldProductIdOfRoomCollectionNewRoom != null && !oldProductIdOfRoomCollectionNewRoom.equals(accomodation)) {
                        oldProductIdOfRoomCollectionNewRoom.getRoomCollection().remove(roomCollectionNewRoom);
                        oldProductIdOfRoomCollectionNewRoom = em.merge(oldProductIdOfRoomCollectionNewRoom);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = accomodation.getProductId();
                if (findAccomodation(id) == null) {
                    throw new NonexistentEntityException("The accomodation with id " + id + " no longer exists.");
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
            Accomodation accomodation;
            try {
                accomodation = em.getReference(Accomodation.class, id);
                accomodation.getProductId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accomodation with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Room> roomCollectionOrphanCheck = accomodation.getRoomCollection();
            for (Room roomCollectionOrphanCheckRoom : roomCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Accomodation (" + accomodation + ") cannot be destroyed since the Room " + roomCollectionOrphanCheckRoom + " in its roomCollection field has a non-nullable productId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(accomodation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Accomodation> findAccomodationEntities() {
        return findAccomodationEntities(true, -1, -1);
    }

    public List<Accomodation> findAccomodationEntities(int maxResults, int firstResult) {
        return findAccomodationEntities(false, maxResults, firstResult);
    }

    private List<Accomodation> findAccomodationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accomodation.class));
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

    public Accomodation findAccomodation(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accomodation.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccomodationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accomodation> rt = cq.from(Accomodation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
