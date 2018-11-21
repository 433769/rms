package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import cz.muni.fi.pa165.skupina06.team02.rms.app.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Martin Lacko
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ShoppingListDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ShoppingListDao slDao;

//    @Autowired
//    private ShoppingItemDao siDao;
//
//    @Autowired
//    private HouseholdDao hDao;

    public static ShoppingList makeList() {
        ShoppingList ret = new ShoppingList();
        ret.setName("x");
        return ret;
    }

    public static List<ShoppingItem> makeItems() {
        List<ShoppingItem> ret = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ShoppingItem item = new ShoppingItem();
            item.setName(String.format("item %s", i));
            item.setBought(false);
            item.setQuantity(Long.valueOf(i));
        }

        return ret;
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullNameNotAllowed() {
        ShoppingList list = makeList();
        list.setName(null);
        slDao.create(list);
    }

    @Test
    public void createShoppingList() {
        ShoppingList list = makeList();
        slDao.create(list);

        ShoppingList found = slDao.findById(list.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(list, found);
    }

    @Test
    public void testFindAll() {
        // TODO: Missing implementation of FindAll
//        ShoppingList l1 = makeList();
//        ShoppingList l2 = makeList();
//        l1.setName("1");
//        l2.setName("2");
//        slDao.create(l1);
//        slDao.create(l2);
//
//        List<ShoppingList> all = slDao.findAll();
//        Assert.assertTrue(all.contains(l1));
//        Assert.assertTrue(all.contains(l2));
    }

    @Test
    public void testFindByName() {
        ShoppingList l1 = makeList();
        ShoppingList l2 = makeList();
        ShoppingList l3 = makeList();
        l1.setName("1");
        l2.setName("2");
        l3.setName("2");
        slDao.create(l1);
        slDao.create(l2);
        slDao.create(l3);

        List<ShoppingList> first = slDao.findByName("1");
        Assert.assertTrue(first.contains(l1));
        Assert.assertFalse(first.contains(l2));
        Assert.assertFalse(first.contains(l3));

        first = slDao.findByName("2");
        Assert.assertFalse(first.contains(l1));
        Assert.assertTrue(first.contains(l2));
        Assert.assertTrue(first.contains(l3));
    }

    @Test
    public void testUpdate() {
        ShoppingList l = makeList();
        slDao.create(l);

        l.setName("y");
        slDao.update(l);
        Assert.assertEquals(slDao.findById(l.getId()).getName(), l.getName());
    }

    @Test
    public void testDelete() {
        ShoppingList l = makeList();
        slDao.create(l);

        slDao.delete(l);
        Assert.assertNull(slDao.findById(l.getId()));
    }
}