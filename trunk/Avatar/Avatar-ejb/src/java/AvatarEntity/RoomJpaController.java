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
import java.util.Date;

/**
 *
 * @author Christian
 */
public class RoomJpaController {

    public RoomJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Room room) throws PreexistingEntityException, Exception {
        if (room.getRoomReservationCollection() == null) {
            room.setRoomReservationCollection(new ArrayList<RoomReservation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accomodation productId = room.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getProductId());
                room.setProductId(productId);
            }
            Collection<RoomReservation> attachedRoomReservationCollection = new ArrayList<RoomReservation>();
            for (RoomReservation roomReservationCollectionRoomReservationToAttach : room.getRoomReservationCollection()) {
                roomReservationCollectionRoomReservationToAttach = em.getReference(roomReservationCollectionRoomReservationToAttach.getClass(), roomReservationCollectionRoomReservationToAttach.getReservationItemId());
                attachedRoomReservationCollection.add(roomReservationCollectionRoomReservationToAttach);
            }
            room.setRoomReservationCollection(attachedRoomReservationCollection);
            em.persist(room);
            if (productId != null) {
                productId.getRoomCollection().add(room);
                productId = em.merge(productId);
            }
            for (RoomReservation roomReservationCollectionRoomReservation : room.getRoomReservationCollection()) {
                Room oldRoomNoOfRoomReservationCollectionRoomReservation = roomReservationCollectionRoomReservation.getRoomNo();
                roomReservationCollectionRoomReservation.setRoomNo(room);
                roomReservationCollectionRoomReservation = em.merge(roomReservationCollectionRoomReservation);
                if (oldRoomNoOfRoomReservationCollectionRoomReservation != null) {
                    oldRoomNoOfRoomReservationCollectionRoomReservation.getRoomReservationCollection().remove(roomReservationCollectionRoomReservation);
                    oldRoomNoOfRoomReservationCollectionRoomReservation = em.merge(oldRoomNoOfRoomReservationCollectionRoomReservation);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRoom(room.getRoomNo()) != null) {
                throw new PreexistingEntityException("Room " + room + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Room room) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Room persistentRoom = em.find(Room.class, room.getRoomNo());
            Accomodation productIdOld = persistentRoom.getProductId();
            Accomodation productIdNew = room.getProductId();
            Collection<RoomReservation> roomReservationCollectionOld = persistentRoom.getRoomReservationCollection();
            Collection<RoomReservation> roomReservationCollectionNew = room.getRoomReservationCollection();
            List<String> illegalOrphanMessages = null;
            for (RoomReservation roomReservationCollectionOldRoomReservation : roomReservationCollectionOld) {
                if (!roomReservationCollectionNew.contains(roomReservationCollectionOldRoomReservation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RoomReservation " + roomReservationCollectionOldRoomReservation + " since its roomNo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getProductId());
                room.setProductId(productIdNew);
            }
            Collection<RoomReservation> attachedRoomReservationCollectionNew = new ArrayList<RoomReservation>();
            for (RoomReservation roomReservationCollectionNewRoomReservationToAttach : roomReservationCollectionNew) {
                roomReservationCollectionNewRoomReservationToAttach = em.getReference(roomReservationCollectionNewRoomReservationToAttach.getClass(), roomReservationCollectionNewRoomReservationToAttach.getReservationItemId());
                attachedRoomReservationCollectionNew.add(roomReservationCollectionNewRoomReservationToAttach);
            }
            roomReservationCollectionNew = attachedRoomReservationCollectionNew;
            room.setRoomReservationCollection(roomReservationCollectionNew);
            room = em.merge(room);
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getRoomCollection().remove(room);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getRoomCollection().add(room);
                productIdNew = em.merge(productIdNew);
            }
            for (RoomReservation roomReservationCollectionNewRoomReservation : roomReservationCollectionNew) {
                if (!roomReservationCollectionOld.contains(roomReservationCollectionNewRoomReservation)) {
                    Room oldRoomNoOfRoomReservationCollectionNewRoomReservation = roomReservationCollectionNewRoomReservation.getRoomNo();
                    roomReservationCollectionNewRoomReservation.setRoomNo(room);
                    roomReservationCollectionNewRoomReservation = em.merge(roomReservationCollectionNewRoomReservation);
                    if (oldRoomNoOfRoomReservationCollectionNewRoomReservation != null && !oldRoomNoOfRoomReservationCollectionNewRoomReservation.equals(room)) {
                        oldRoomNoOfRoomReservationCollectionNewRoomReservation.getRoomReservationCollection().remove(roomReservationCollectionNewRoomReservation);
                        oldRoomNoOfRoomReservationCollectionNewRoomReservation = em.merge(oldRoomNoOfRoomReservationCollectionNewRoomReservation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = room.getRoomNo();
                if (findRoom(id) == null) {
                    throw new NonexistentEntityException("The room with id " + id + " no longer exists.");
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
            Room room;
            try {
                room = em.getReference(Room.class, id);
                room.getRoomNo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The room with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RoomReservation> roomReservationCollectionOrphanCheck = room.getRoomReservationCollection();
            for (RoomReservation roomReservationCollectionOrphanCheckRoomReservation : roomReservationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Room (" + room + ") cannot be destroyed since the RoomReservation " + roomReservationCollectionOrphanCheckRoomReservation + " in its roomReservationCollection field has a non-nullable roomNo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Accomodation productId = room.getProductId();
            if (productId != null) {
                productId.getRoomCollection().remove(room);
                productId = em.merge(productId);
            }
            em.remove(room);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Room> findRoomEntities() {
        return findRoomEntities(true, -1, -1);
    }

    public List<Room> findRoomEntities(int maxResults, int firstResult) {
        return findRoomEntities(false, maxResults, firstResult);
    }

    private List<Room> findRoomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Room.class));
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

    public List<Room> findUnused(String productId, Date entryDate, Date exitDate) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Room.findUnused");
            q.setParameter("productId", productId);
            q.setParameter("entryDate", entryDate);
            q.setParameter("exitDate", exitDate);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Room findRoom(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Room.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Room> rt = cq.from(Room.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
