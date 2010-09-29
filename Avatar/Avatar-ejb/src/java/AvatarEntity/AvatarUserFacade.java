/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author zulfikar
 */
@Stateless
public class AvatarUserFacade {
    @PersistenceContext(unitName = "AvatarPersistenceUnit")
    private EntityManager em;

    public void create(AvatarUser avatarUser) {
        em.persist(avatarUser);
    }

    public void edit(AvatarUser avatarUser) {
        em.merge(avatarUser);
    }

    public void remove(AvatarUser avatarUser) {
        em.remove(em.merge(avatarUser));
    }

    public AvatarUser find(Object id) {
        return em.find(AvatarUser.class, id);
    }

    public List<AvatarUser> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(AvatarUser.class));
        return em.createQuery(cq).getResultList();
    }

    public List<AvatarUser> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(AvatarUser.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<AvatarUser> rt = cq.from(AvatarUser.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
