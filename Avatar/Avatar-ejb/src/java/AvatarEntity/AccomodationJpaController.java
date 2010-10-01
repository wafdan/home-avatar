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
public class AccomodationJpaController {

    public AccomodationJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accomodation accomodation) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(accomodation);
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

    public void edit(Accomodation accomodation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            accomodation = em.merge(accomodation);
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

    public void destroy(String id) throws NonexistentEntityException {
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
            TypedQuery<Accomodation> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Accomodation> results = q.getResultList();
            return results;
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
