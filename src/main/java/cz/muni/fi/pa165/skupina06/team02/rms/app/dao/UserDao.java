package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

import java.util.List;

/**
 * Interface to access Users in database
 *
 * @author Martin Lacko
 */
public interface UserDao {
    /**
     * Find user by its item
     *
     * @param id id of user
     * @return user with given id, null if doesnt exists
     */
    public User findById(Long id);

    /**
     * Find email by its *unique* email
     *
     * @param email email of user
     * @return user with given email, null if doesnt exists
     */
    public User findByEmail(String email);

    /**
     * Find all users
     *
     * @return list of users in database
     */
    public List<User> findAll();

    /**
     * Create new user
     *
     * @param user User to create
     */
    public void create(User user);

    /**
     * Delete existing user
     *
     * @param user user to delete
     */
    public void delete(User user);

    /**
     * Update existing user
     *
     * @param user user to update
     */
    public void update(User user);
}
