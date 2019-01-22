package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.controllers;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingListCreateDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingListDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.ShoppingListFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.ApiUris;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.exceptions.ResourceNotFoundException;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for ShoppingItem
 * 
 * @author Vojtech Prusa
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_SHOPPINGLISTS)
public class ShoppingListController extends BaseController {

    final static Logger logger = LoggerFactory.getLogger(ShoppingListController.class);

    @Inject
    private ShoppingListFacade shoppingListFacade;

    /**
     * get all the categories
     * @return list of ShoppingItemDTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ShoppingListDTO> getShoppingList() {
        logger.debug("rest getShoppingList()");
        return shoppingListFacade.getAllShoppingLists();
    }

    /**
     * 
     * Get one shoppingItem specified by id
     * 
     * @param id identifier for the shoppingItem
     * @return ShoppingItemDTO
     * @throws Exception ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ShoppingListDTO getShoppingList(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getShoppingList({})", id);

        ShoppingListDTO shoppingListDTO = shoppingListFacade.getListById(id);
        if (shoppingListDTO == null) {
            throw new ResourceNotFoundException();
        }

        return shoppingListDTO;
    }
    
    /**
     * 
     * Create new shoppingItem and returns its id
     * 
     * @param ShoppingItemCreateDTO as body
     * @return long id
     * @throws Exception ResourceNotFoundException if empty body
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final long createShoppingList(@RequestBody ShoppingListCreateDTO shoppingList) throws Exception {
        if (shoppingList == null) {
            throw new ResourceNotFoundException();
        }
        logger.debug("rest createShoppingList({})", shoppingList.toString());
        //ShoppingItemDTO shoppingItemDTO = shoppingItemFacade.getItemById(shoppingItem.getI());
        long newListId = shoppingListFacade.createList(shoppingList);
        return newListId;
    }
}