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
public class RoomReservationJpaController {

    public RoomReservationJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RoomReservation roomReservation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Room roomNo = roomReservation.getRoomNo();
            if (roomNo != null) {
                roomNo = em.getReference(roomNo.getClass(), roomNo.getRoomNo());
                roomReservation.setRoomNo(roomNo);
            }
            Reservation reservationId = roomReservation.getReservationId();
            if (reservationId != null) {
                reservationId = em.getReference(reservationId.getClass(), reservationId.getReservationId());
                roomReservation.setReservationId(reservationId);
            }
            em.persist(roomReservation);
            if (roomNo != null) {
                roomNo.getRoomReservationCollection().add(roomReservation);
                roomNo = em.merge(roomNo);
            }
            if (reservationId != null) {
                reservationId.getReservationItemCollection().add(roomReservation);
                reservationId = em.merge(reservationId);
            }
            em.getTransaction().commit();
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
            RoomReservation persistentRoomReservation = em.find(RoomReservation.class, roomReservation.getReservationItemId());
            Room roomNoOld = persistentRoomReservation.getRoomNo();
            Room roomNoNew = roomReservation.getRoomNo();
            Reservation reservationIdOld = persistentRoomReservation.getReservationId();
            Reservation reservationIdNew = roomReservation.getReservationId();
            if (roomNoNew != null) {
                roomNoNew = em.getReference(roomNoNew.getClass(), roomNoNew.getRoomNo());
                roomReservation.setRoomNo(roomNoNew);
            }
            if (reservationIdNew != null) {
                reservationIdNew = em.getReference(reservationIdNew.getClass(), reservationIdNew.getReservationId());
                roomReservation.setReservationId(reservationIdNew);
            }
            roomReservation = em.merge(roomReservation);
            if (roomNoOld != null && !roomNoOld.equals(roomNoNew)) {
                roomNoOld.getRoomReservationCollection().remove(roomReservation);
                roomNoOld = em.merge(roomNoOld);
            }
            if (roomNoNew != null && !roomNoNew.equals(roomNoOld)) {
                roomNoNew.getRoomReservationCollection().add(roomReservation);
                roomNoNew = em.merge(roomNoNew);
            }
            if (reservationIdOld != null && !reservationIdOld.equals(reservationIdNew)) {
                reservationIdOld.getReservationItemCollection().remove(roomReservation);
                reservationIdOld = em.merge(reservationIdOld);
            }
            if (reservationIdNew != null && !reservationIdNew.equals(reservationIdOld)) {
                reservationIdNew.getReservationItemCollection().add(roomReservation);
                reservationIdNew = em.merge(reservationIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = roomReservation.getReservationItemId();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RoomReservation roomReservation;
            try {
                roomReservation = em.getReference(RoomReservation.class, id);
                roomReservation.getReservationItemId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roomReservation with id " + id + " no longer exists.", enfe);
            }
            Room roomNo = roomReservation.getRoomNo();
            if (roomNo != null) {
                roomNo.getRoomReservationCollection().remove(roomReservation);
                roomNo = em.merge(roomNo);
            }
            Reservation reservationId = roomReservation.getReservationId();
            if (reservationId != null) {
                reservationId.getReservationItemCollection().remove(roomReservation);
                reservationId = em.merge(reservationId);
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

    public RoomReservation findRoomReservation(Integer id) {
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
