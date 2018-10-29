package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.skupina06.team02.rms.app.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

/**
 * @author Vojtech Prusa
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTests extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public UserDao userDao;

    private User[] users;
    private final static int USERS_COUNT = 2;

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullEmailNotAllowed() {
        users[0].setEmail(null);
        userDao.create(users[0]);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullFirstNameNotAllowed() {
        users[0].setFirstName(null);
        userDao.create(users[0]);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullLastNameNotAllowed() {
        users[0].setLastName(null);
        userDao.create(users[0]);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullPasswordNotAllowed() {
        users[0].setPassword(null);
        userDao.create(users[0]);
    }

    /*
     * Prepare users
     */
    @BeforeMethod
    public void setUp() {
        users = new User[USERS_COUNT];
        for (int i = 0; i < USERS_COUNT; i++) {
            users[i] = new User();
            users[i].setFirstName("TestFirstName" + i);
            users[i].setLastName("TestLastName" + i);
            users[i].setPassword("TestPassword" + i);
            users[i].setEmail("TestEmail" + i);
        }
    }

    @Test
    public User findById() {
        for (int i = 0; i < USERS_COUNT; i++) {
            userDao.create(users[i]);
            User found = userDao.findById(users[i].getId());
            assertUsersEquals(users[i], found);
        }
        return null;
    }

    @Test
    public void findByEmail() {
        // TODO Auto-generated method stub
    }

    @Test
    public void findAll() {
        // TODO Auto-generated method stub
    }

    @Test
    public void create() {
        userDao.create(users[0]);
        User expceted = users[0];
        User found = userDao.findById(users[0].getId());
        assertUsersEquals(expceted, found);
    }

    @Test
    public void delete() {
        // TODO Auto-generated method stub
    }

    @Test
    public void update() {
        int usedUsers = 1;
        String differenEmail = "DifferenEmail";
        for (int i = 0; i < usedUsers; i++) {
            userDao.create(users[i]);
            User toUpdate = userDao.findById(users[i].getId());
            toUpdate.setEmail(differenEmail + (i + 1));
            userDao.update(toUpdate);
        }

        for (int i = 0; i < usedUsers; i++) {
            User expceted = users[i];
            User found = userDao.findById(expceted.getId());
            assertUsersEquals(expceted, found);
        }
    }

    private void assertUsersEquals(User expected, User found) {
        Assert.assertNotNull(found, "Found user can not be null");
        Assert.assertEquals(expected.getId(), found.getId());
        Assert.assertEquals(expected.getFirstName(), found.getFirstName());
        Assert.assertEquals(expected.getLastName(), found.getLastName());
        Assert.assertEquals(expected.getEmail(), found.getEmail());
        Assert.assertEquals(expected.getPassword(), found.getPassword());
    }

}
