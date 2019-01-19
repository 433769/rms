package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import java.util.List;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

/**
 * @author Vojtech Prusa
 *
 *         An interface that defines a service access to the {@link Household}
 *         entity.
 *         
 *         {@link HouseholdServiceImpl}
 */
public interface HouseholdService {

    /**
     * Create new household TODO may be changed separated values
     * 
     * @param Household instance
     */
    void createHousehold(Household Household);

    /**
     * Return households users
     * 
     * @param user
     * @return list of hosueholds
     */
    List<Household> getHouseholdsByUser(User user);

    /**
     * GEts all households shopping lists
     * 
     * @param shoppingList
     * @return list of shopping lists
     */
    List<ShoppingList> getHouseholdsByShoppingList(ShoppingList shoppingList);

    /**
     * Get households
     * 
     * @return list of all households
     */
    List<Household> findAll();

    /**
     * Update values
     * 
     * @param household instance
     */
    void updateHousehold(Household household);

    /**
     * Delete input household TODO may be change to use just id
     * 
     * @param household instance
     */
    void deleteHousehold(Household household);

    /**
     * Find household by id
     * 
     * @param id value
     * @return Househol d instance
     */
    Household findHouseholdById(Long id);

    /**
     * Remove tenant
     * 
     * @param tenant
     */
    void removeTenant(User tenant, Household housheold);

    /**
     * Add tenant to tenants and Household to tenant
     * 
     * @param tenant
     */
    void addTenant(User tenant, Household housheold);


}
