package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;

/**
 * @author Vojtech Prusa
 *
 */
@Repository
public class HouseholdDaoImpl implements HouseholdDao {

    @PersistenceContext
    private EntityManager em;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao#findById(java.lang
     * .Long)
     */
    @Override
    public Household findById(Long id) {
        return em.find(Household.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao#create(cz.muni.fi.
     * pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void create(Household h) {
        em.persist(h);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao#delete(cz.muni.fi.
     * pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void delete(Household h) {
        em.remove(h);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao#findAll()
     */
    @Override
    public List<Household> findAll() {
        return em.createQuery("select i from Household i", Household.class).getResultList();
    }

    /* (non-Javadoc)
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao#update(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public Household update(Household h) {
        return em.merge(h);
    }

}
