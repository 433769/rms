package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import java.util.List;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;

/**
 * @author Vojtech Prusa
 *
 */
public interface HouseholdDao {

    /**
     * @param id
     * @return
     */
    public Household findById(Long id);

    /**
     * @param Household instance
     */
    public void create(Household h);

    /**
     * @param Household instance
     */
    public void delete(Household h);

    /**
     * @return List of Household instances
     */
    public List<Household> findAll();
    
    /**
     * @param Household instance
     * @return updated Household instance
     */
    public Household update(Household h);


    /**
     * Find Household by given address
     * 
     * @param name
     * @return
     */
    // public Household findByAddress(String street, String buildingNumber, String
    // zipCode, String state);
}
