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
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

/**
 * @author Vojtech Prusa
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ShoppingItemDaoTests extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public ShoppingItemDao itemsDao;

    @Autowired
    public UserDao userDao;

    @Autowired
    public ShoppingListDao shoppingListDao;

    private ShoppingItem[] items;
    private User dedicatedBuyer;
    private ShoppingList shoppingList;
    private final static int ITEMS_COUNT = 3;

    /*
     * Prepare items
     */
    @BeforeMethod
    public void setUp() {
        dedicatedBuyer = new User();
        dedicatedBuyer.setFirstName("TestFirstName");
        dedicatedBuyer.setLastName("TestLastName");
        dedicatedBuyer.setPassword("TestPassword");
        dedicatedBuyer.setEmail("TestEmail");
        userDao.create(dedicatedBuyer);

        shoppingList = new ShoppingList();
        shoppingList.setName("ShoppingList");
        shoppingListDao.create(shoppingList);

        items = new ShoppingItem[ITEMS_COUNT];
        for (int i = 0; i < ITEMS_COUNT; i++) {
            items[i] = new ShoppingItem();
            items[i].setBought(false);
            items[i].setName("TestName" + i);
            items[i].setQuantity((long) i + 10);
            // items[i].setDedicatedBuyer(null);
            // items[i].setShoppingList(null);
            items[i].setDedicatedBuyer(dedicatedBuyer);
            items[i].setShoppingList(shoppingList);
            shoppingList.addItem(items[i]);
        }
        // items[0].setDedicatedBuyer(dedicatedBuyer);
        // items[0].setShoppingList(shoppingList);
        // shoppingList.addItem(items[0]);
        // shoppingList.addItem(items[1]);
        // items[2].setDedicatedBuyer(dedicatedBuyer);
    }

    /**
     * Tests raise of ConstraintViolationException exception when Name is null
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullNameNotAllowed() {
        items[0].setName(null);
        itemsDao.create(items[0]);
    }

    /**
     * Tests findById method
     */
    @Test
    public void findById() {
        for (int i = 0; i < ITEMS_COUNT; i++) {
            itemsDao.create(items[i]);
            ShoppingItem found = itemsDao.findById(items[i].getId());
            assertItemsEquals(items[i], found);
        }
    }

    /**
     * Tests findAll method
     */
    @Test
    public void findAll() {
        for (int i = 0; i < ITEMS_COUNT; i++) {
            itemsDao.create(items[i]);
        }
        List<ShoppingItem> foundItems = itemsDao.findAll();
        Assert.assertEquals(foundItems.size(), ITEMS_COUNT);
    }

    /**
     * Tests create method
     */
    @Test
    public void create() {
        itemsDao.create(items[0]);
        ShoppingItem expceted = items[0];
        ShoppingItem found = itemsDao.findById(items[0].getId());
        assertItemsEquals(expceted, found);
    }

    /**
     * Tests delete method
     */
    @Test
    public void delete() {
        ShoppingItem tested = items[0];
        itemsDao.create(tested);
        Long id = tested.getId();
        itemsDao.delete(tested);
        ShoppingItem notFound = itemsDao.findById(id);
        Assert.assertNull(notFound, "Found ShoppingItem should have been null");
    }

    /**
     * Tests update method
     */
    @Test
    public void update() {
        int usedItems = ITEMS_COUNT; // or 1
        String differenEmail = "DifferenName";
        for (int i = 0; i < usedItems; i++) {
            itemsDao.create(items[i]);
            ShoppingItem toUpdate = itemsDao.findById(items[i].getId());
            toUpdate.setName(differenEmail + (i + 1));
            itemsDao.update(toUpdate);
        }

        for (int i = 0; i < usedItems; i++) {
            ShoppingItem expceted = items[i];
            ShoppingItem found = itemsDao.findById(expceted.getId());
            assertItemsEquals(expceted, found);
        }
    }

    /**
     * This method is used for asserting values of found ShoppingItem vs expected
     * ShoppingItem
     * 
     * @param expected ShoppingItem
     * @param found    ShoppingItem
     */
    private void assertItemsEquals(ShoppingItem expected, ShoppingItem found) {
        Assert.assertNotNull(found, "Found ShoppingItem can not be null");
        Assert.assertEquals(expected.getBought(), found.getBought());
        Assert.assertEquals(expected.getName(), found.getName());
        Assert.assertEquals(expected.getQuantity(), found.getQuantity());
        Assert.assertEquals(expected.getDedicatedBuyer(), found.getDedicatedBuyer());
        Assert.assertEquals(expected.getShoppingList(), found.getShoppingList());
    }

}
