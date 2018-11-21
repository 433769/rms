package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import cz.muni.fi.pa165.skupina06.team02.rms.app.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * @author Martin Lacko
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HouseholdDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private HouseholdDao hDao;

    public static Household makeTestHousehold() {
        Household h = new Household();
        h.setStreet("x");
        h.setState("x");
        h.setBuildingNumber("x");
        h.setZipCode("x");
        return h;
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullStreetNotAllowed() {
        Household h = makeTestHousehold();
        h.setStreet(null);
        hDao.create(h);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullStateNotAllowed() {
        Household h = makeTestHousehold();
        h.setState(null);
        hDao.create(h);
        ;
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullBuildingNumberNotAllowed() {
        Household h = makeTestHousehold();
        h.setBuildingNumber(null);
        hDao.create(h);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullZipCodeNotAllowed() {
        Household h = makeTestHousehold();
        h.setZipCode(null);
        hDao.create(h);
    }

    @Test
    public void createHousehold() {
        Household h = makeTestHousehold();
        hDao.create(h);

        Household found = hDao.findById(h.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(h.getId(), found.getId());
        Assert.assertEquals(h.getStreet(), found.getStreet());
        Assert.assertEquals(h.getState(), found.getState());
        Assert.assertEquals(h.getBuildingNumber(), found.getBuildingNumber());
        Assert.assertEquals(h.getZipCode(), found.getZipCode());
    }

    @Test
    public void findAll() {
        Household h1 = makeTestHousehold();
        Household h2 = makeTestHousehold();
        h1.setState("1");
        h2.setState("2");
        hDao.create(h1);
        hDao.create(h2);

        List<Household> all = hDao.findAll();
        Assert.assertTrue(all.contains(h1));
        Assert.assertTrue(all.contains(h2));
    }

    @Test
    public void update() {
        Household h = makeTestHousehold();
        hDao.create(h);

        h.setState("y");
        hDao.update(h);
        Assert.assertEquals(hDao.findById(h.getId()).getState(), h.getState());
    }

    @Test
    public void delete() {
        Household h = makeTestHousehold();
        hDao.create(h);

        hDao.delete(h);
        Assert.assertNull(hDao.findById(h.getId()));
    }
}