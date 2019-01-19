package cz.muni.fi.pa165.skupina06.team02.rms.app.web.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingItemService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingListService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Vojtech Prusa
 *
 */
@Component
@Transactional // transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private UserService userService;
    @Autowired
    private HouseholdService householdService;
    @Autowired
    private ShoppingItemService shoppingItemService;
    @Autowired
    private ShoppingListService shoppingListService;

    @Override
    public void loadData() throws IOException {
        log.info("Loaded tenants");
        User adam = user("password", "Adam", "Mada");
        User tom = user("password", "Tom", "Mot");
        User jan = user("password", "Jan", "Naj");
        User kel = user("password", "Lek", "Kel");

        Household adamsH = household("AdamsH", "666", "CZ", "Elm Street", "333 x2", null, adam);
        adamsH.addTenant(adam);

        ShoppingList adamsSl = shoppingList("AdamsSL", adamsH, null);
        ShoppingItem orange = shoppingItem("orange", false, adam, 10l, adamsSl);
        adamsH.addToShoppingLists(adamsSl);
        //adamsSl.addItem(orange);
        // order(admin, daysBeforeNow(10), OrderState.DONE, orderItem(duck, 5),
        // orderItem(diamonds, 1));
        log.info("Everything loaded");
    }

    private static Date daysBeforeNow(int days) {
        return Date.from(ZonedDateTime.now().minusDays(days).toInstant());
    }

    private User user(String password, String firstName, String lastName) {
        String email = firstName + "@rms.com";
        User u = new User();
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        userService.registerUser(u, password);
        return u;
    }

    private Household household(String name, String buildingNumber, String state, String street, String zipCode,
            List<ShoppingList> shoppingLists, User... tenants) {
        Household h = new Household();
        h.setBuildingNumber(buildingNumber);
        h.setState(state);
        h.setStreet(street);
        h.setZipCode(zipCode);
        if (tenants != null) {
            for (User tenant : tenants) {
                h.addTenant(tenant);
            }
        }
        if (shoppingLists != null) {
            for (ShoppingList shoppingList : shoppingLists) {
                h.addToShoppingLists(shoppingList);
            }
        }
        householdService.createHousehold(h);
        return h;
    }

    private ShoppingItem shoppingItem(String name, Boolean bought, User dedicatedBuyer, Long quantity,
            ShoppingList shoppingList) {
        ShoppingItem si = new ShoppingItem();
        si.setName(name);
        si.setBought(bought);
        si.setDedicatedBuyer(dedicatedBuyer);
        si.setQuantity(quantity);
        si.setShoppingList(shoppingList);
        shoppingItemService.createShoppingItem(si);
        return si;
    }

    private ShoppingList shoppingList(String name, Household household, ShoppingItem... shoppingItems) {
        ShoppingList sl = new ShoppingList();
        sl.setHousehold(household);
        sl.setName(name);
        if (shoppingItems != null) {
            for (ShoppingItem si : shoppingItems) {
                sl.addItem(si);
            }
        }

        shoppingListService.createShoppingList(sl);
        return sl;
    }
}