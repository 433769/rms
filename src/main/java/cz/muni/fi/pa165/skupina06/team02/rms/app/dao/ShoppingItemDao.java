package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;

import java.util.List;

/**
 * Interface for accessing ShoppingItems
 *
 * @author Martin Lacko
 */
public interface ShoppingItemDao {
    /**
     * Find item by its id
     *
     * @param id id of item
     * @return Item with given id, null if such does not exists
     */
    public ShoppingItem findById(Long id);

    /**
     * Find all shopping items
     *
     * @return List of all shopping items
     */
    public List<ShoppingItem> findAll();

    /**
     * Create new shopping item
     *
     * @param item item to create
     */
    public void create(ShoppingItem item);

    /**
     * Delete existing item from db
     *
     * @param item Item in db
     */
    public void delete(ShoppingItem item);

    /**
     * Update existing item in database
     *
     * @param item item in database
     */
    public void update(ShoppingItem item);
}
