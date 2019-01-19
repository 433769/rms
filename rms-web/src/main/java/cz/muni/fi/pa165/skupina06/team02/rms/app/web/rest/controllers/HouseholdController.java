package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.controllers;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingListDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.ShoppingItemFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.ShoppingListFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.ApiUris;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.exceptions.ResourceNotFoundException;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(ApiUris.ROOT_URI_HOUSEHOLDS)
public class HouseholdController extends BaseController {

    final static Logger logger = LoggerFactory.getLogger(HouseholdController.class);

    @Inject
    private HouseholdFacade housheoldFacade;

    /**
     * get all the categories
     * 
     * @return list of ShoppingItemDTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HouseholdDTO> getShoppingItem() {
        logger.debug("rest getHousehold()");
        return housheoldFacade.findAll();
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
    public final HouseholdDTO getHousehold(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getHousehold({})", id);

        HouseholdDTO householdDTO = housheoldFacade.findHouseholdById(id);
        if (householdDTO == null) {
            throw new ResourceNotFoundException();
        }

        return householdDTO;
    }
}