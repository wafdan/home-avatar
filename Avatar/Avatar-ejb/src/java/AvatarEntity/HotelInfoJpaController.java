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
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author zulfikar
 */
public class HotelInfoJpaController {

    public HotelInfoJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HotelInfo hotelInfo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hotelInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHotelInfo(hotelInfo.getId()) != null) {
                throw new PreexistingEntityException("HotelInfo " + hotelInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HotelInfo hotelInfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hotelInfo = em.merge(hotelInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = hotelInfo.getId();
                if (findHotelInfo(id) == null) {
                    throw new NonexistentEntityException("The hotelInfo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HotelInfo hotelInfo;
            try {
                hotelInfo = em.getReference(HotelInfo.class, id);
                hotelInfo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hotelInfo with id " + id + " no longer exists.", enfe);
            }
            em.remove(hotelInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HotelInfo> findHotelInfoEntities() {
        return findHotelInfoEntities(true, -1, -1);
    }

    public List<HotelInfo> findHotelInfoEntities(int maxResults, int firstResult) {
        return findHotelInfoEntities(false, maxResults, firstResult);
    }

    private List<HotelInfo> findHotelInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HotelInfo.class));
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

    public HotelInfo findHotelInfo(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HotelInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getHotelInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HotelInfo> rt = cq.from(HotelInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
