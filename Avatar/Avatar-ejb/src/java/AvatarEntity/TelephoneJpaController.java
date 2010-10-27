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
 * @author Christian
 */
public class TelephoneJpaController {

    public TelephoneJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Telephone telephone) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(telephone);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTelephone(telephone.getPhoneNumber()) != null) {
                throw new PreexistingEntityException("Telephone " + telephone + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Telephone telephone) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            telephone = em.merge(telephone);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = telephone.getPhoneNumber();
                if (findTelephone(id) == null) {
                    throw new NonexistentEntityException("The telephone with id " + id + " no longer exists.");
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
            Telephone telephone;
            try {
                telephone = em.getReference(Telephone.class, id);
                telephone.getPhoneNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The telephone with id " + id + " no longer exists.", enfe);
            }
            em.remove(telephone);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Telephone> findTelephoneEntities() {
        return findTelephoneEntities(true, -1, -1);
    }

    public List<Telephone> findTelephoneEntities(int maxResults, int firstResult) {
        return findTelephoneEntities(false, maxResults, firstResult);
    }

    private List<Telephone> findTelephoneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Telephone.class));
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

    public Telephone findTelephone(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Telephone.class, id);
        } finally {
            em.close();
        }
    }

    public int getTelephoneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Telephone> rt = cq.from(Telephone.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
