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
public class OtherServicesJpaController {

    public OtherServicesJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OtherServices otherServices) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(otherServices);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOtherServices(otherServices.getProductId()) != null) {
                throw new PreexistingEntityException("OtherServices " + otherServices + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OtherServices otherServices) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            otherServices = em.merge(otherServices);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = otherServices.getProductId();
                if (findOtherServices(id) == null) {
                    throw new NonexistentEntityException("The otherServices with id " + id + " no longer exists.");
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
            OtherServices otherServices;
            try {
                otherServices = em.getReference(OtherServices.class, id);
                otherServices.getProductId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The otherServices with id " + id + " no longer exists.", enfe);
            }
            em.remove(otherServices);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OtherServices> findOtherServicesEntities() {
        return findOtherServicesEntities(true, -1, -1);
    }

    public List<OtherServices> findOtherServicesEntities(int maxResults, int firstResult) {
        return findOtherServicesEntities(false, maxResults, firstResult);
    }

    private List<OtherServices> findOtherServicesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OtherServices.class));
            TypedQuery<OtherServices> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<OtherServices> results = q.getResultList();
            return results;
        } finally {
            em.close();
        }
    }

    public List<OtherServices> findPublishedOtherServicesEntities() {
        return findPublishedOtherServicesEntities(true, -1, -1);
    }

    public List<OtherServices> findPublishedOtherServicesEntities(int maxResults, int firstResult) {
        return findPublishedOtherServicesEntities(false, maxResults, firstResult);
    }

    private List<OtherServices> findPublishedOtherServicesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OtherServices.class));
            TypedQuery<OtherServices> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<OtherServices> results = q.getResultList();
            return results;
        } finally {
            em.close();
        }
    }

    public OtherServices findOtherServices(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OtherServices.class, id);
        } finally {
            em.close();
        }
    }

    public int getOtherServicesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OtherServices> rt = cq.from(OtherServices.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
