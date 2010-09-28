/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaPengguna;

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
public class StaffFacade {
    @PersistenceContext(unitName = "AvatarPersistenceUnit")
    private EntityManager em;

    public void create(Staff staff) {
        em.persist(staff);
    }

    public void edit(Staff staff) {
        em.merge(staff);
    }

    public void remove(Staff staff) {
        em.remove(em.merge(staff));
    }

    public Staff find(Object id) {
        return em.find(Staff.class, id);
    }

    public List<Staff> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Staff.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Staff> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Staff.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Staff> rt = cq.from(Staff.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
