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
public class LayoutJpaController {

    public LayoutJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Layout layout) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(layout);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Layout layout) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            layout = em.merge(layout);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = layout.getLayoutNo();
                if (findLayout(id) == null) {
                    throw new NonexistentEntityException("The layout with id " + id + " no longer exists.");
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
            Layout layout;
            try {
                layout = em.getReference(Layout.class, id);
                layout.getLayoutNo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The layout with id " + id + " no longer exists.", enfe);
            }
            em.remove(layout);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Layout> findLayoutEntities() {
        return findLayoutEntities(true, -1, -1);
    }

    public List<Layout> findLayoutEntities(int maxResults, int firstResult) {
        return findLayoutEntities(false, maxResults, firstResult);
    }

    private List<Layout> findLayoutEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Layout.class));
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

    public Layout findLayout(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Layout.class, id);
        } finally {
            em.close();
        }
    }

    public int getLayoutCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Layout> rt = cq.from(Layout.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
