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
public class OtherServicesJpaController {

    public OtherServicesJpaController() {
        emf = Persistence.createEntityManagerFactory("AvatarPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OtherServices otherServices) throws PreexistingEntityException, Exception {
        if (otherServices.getOtherServicesReservationCollection() == null) {
            otherServices.setOtherServicesReservationCollection(new ArrayList<OtherServicesReservation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<OtherServicesReservation> attachedOtherServicesReservationCollection = new ArrayList<OtherServicesReservation>();
            for (OtherServicesReservation otherServicesReservationCollectionOtherServicesReservationToAttach : otherServices.getOtherServicesReservationCollection()) {
                otherServicesReservationCollectionOtherServicesReservationToAttach = em.getReference(otherServicesReservationCollectionOtherServicesReservationToAttach.getClass(), otherServicesReservationCollectionOtherServicesReservationToAttach.getReservationItemId());
                attachedOtherServicesReservationCollection.add(otherServicesReservationCollectionOtherServicesReservationToAttach);
            }
            otherServices.setOtherServicesReservationCollection(attachedOtherServicesReservationCollection);
            em.persist(otherServices);
            for (OtherServicesReservation otherServicesReservationCollectionOtherServicesReservation : otherServices.getOtherServicesReservationCollection()) {
                OtherServices oldProductIdOfOtherServicesReservationCollectionOtherServicesReservation = otherServicesReservationCollectionOtherServicesReservation.getProductId();
                otherServicesReservationCollectionOtherServicesReservation.setProductId(otherServices);
                otherServicesReservationCollectionOtherServicesReservation = em.merge(otherServicesReservationCollectionOtherServicesReservation);
                if (oldProductIdOfOtherServicesReservationCollectionOtherServicesReservation != null) {
                    oldProductIdOfOtherServicesReservationCollectionOtherServicesReservation.getOtherServicesReservationCollection().remove(otherServicesReservationCollectionOtherServicesReservation);
                    oldProductIdOfOtherServicesReservationCollectionOtherServicesReservation = em.merge(oldProductIdOfOtherServicesReservationCollectionOtherServicesReservation);
                }
            }
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

    public void edit(OtherServices otherServices) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OtherServices persistentOtherServices = em.find(OtherServices.class, otherServices.getProductId());
            Collection<OtherServicesReservation> otherServicesReservationCollectionOld = persistentOtherServices.getOtherServicesReservationCollection();
            Collection<OtherServicesReservation> otherServicesReservationCollectionNew = otherServices.getOtherServicesReservationCollection();
            List<String> illegalOrphanMessages = null;
            for (OtherServicesReservation otherServicesReservationCollectionOldOtherServicesReservation : otherServicesReservationCollectionOld) {
                if (!otherServicesReservationCollectionNew.contains(otherServicesReservationCollectionOldOtherServicesReservation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OtherServicesReservation " + otherServicesReservationCollectionOldOtherServicesReservation + " since its productId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<OtherServicesReservation> attachedOtherServicesReservationCollectionNew = new ArrayList<OtherServicesReservation>();
            for (OtherServicesReservation otherServicesReservationCollectionNewOtherServicesReservationToAttach : otherServicesReservationCollectionNew) {
                otherServicesReservationCollectionNewOtherServicesReservationToAttach = em.getReference(otherServicesReservationCollectionNewOtherServicesReservationToAttach.getClass(), otherServicesReservationCollectionNewOtherServicesReservationToAttach.getReservationItemId());
                attachedOtherServicesReservationCollectionNew.add(otherServicesReservationCollectionNewOtherServicesReservationToAttach);
            }
            otherServicesReservationCollectionNew = attachedOtherServicesReservationCollectionNew;
            otherServices.setOtherServicesReservationCollection(otherServicesReservationCollectionNew);
            otherServices = em.merge(otherServices);
            for (OtherServicesReservation otherServicesReservationCollectionNewOtherServicesReservation : otherServicesReservationCollectionNew) {
                if (!otherServicesReservationCollectionOld.contains(otherServicesReservationCollectionNewOtherServicesReservation)) {
                    OtherServices oldProductIdOfOtherServicesReservationCollectionNewOtherServicesReservation = otherServicesReservationCollectionNewOtherServicesReservation.getProductId();
                    otherServicesReservationCollectionNewOtherServicesReservation.setProductId(otherServices);
                    otherServicesReservationCollectionNewOtherServicesReservation = em.merge(otherServicesReservationCollectionNewOtherServicesReservation);
                    if (oldProductIdOfOtherServicesReservationCollectionNewOtherServicesReservation != null && !oldProductIdOfOtherServicesReservationCollectionNewOtherServicesReservation.equals(otherServices)) {
                        oldProductIdOfOtherServicesReservationCollectionNewOtherServicesReservation.getOtherServicesReservationCollection().remove(otherServicesReservationCollectionNewOtherServicesReservation);
                        oldProductIdOfOtherServicesReservationCollectionNewOtherServicesReservation = em.merge(oldProductIdOfOtherServicesReservationCollectionNewOtherServicesReservation);
                    }
                }
            }
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<OtherServicesReservation> otherServicesReservationCollectionOrphanCheck = otherServices.getOtherServicesReservationCollection();
            for (OtherServicesReservation otherServicesReservationCollectionOrphanCheckOtherServicesReservation : otherServicesReservationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OtherServices (" + otherServices + ") cannot be destroyed since the OtherServicesReservation " + otherServicesReservationCollectionOrphanCheckOtherServicesReservation + " in its otherServicesReservationCollection field has a non-nullable productId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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

    public OtherServices findOtherServices(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OtherServices.class, id);
        } finally {
            em.close();
        }
    }

    public List<OtherServices> findPublishedOtherServices() {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("findPublished");
        return q.getResultList();
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
