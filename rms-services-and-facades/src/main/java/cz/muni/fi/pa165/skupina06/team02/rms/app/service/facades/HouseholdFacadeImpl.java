package cz.muni.fi.pa165.skupina06.team02.rms.app.service.facades;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.BeanMappingService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingListService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserService;

/**
 * @author Vojtech Prusa
 *
 *         Household Facade
 *
 */
@Service
@Transactional
public class HouseholdFacadeImpl implements HouseholdFacade {
    
    final public static Logger logger = LoggerFactory.getLogger(HouseholdFacadeImpl.class);

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingListService shoppingListService;
    
    @Autowired
    private BeanMappingService beanMappingService;

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * getHouseholdById(java.lang.Long)
     */
    @Override
    public HouseholdDTO findHouseholdById(Long id) {
        Household household = householdService.findHouseholdById(id);
        if(household != null) {
            //logger.info("\n\n\n\n");
            HouseholdDTO housheoldDTO = beanMappingService.mapTo(household, HouseholdDTO.class);
            //logger.info(housheoldDTO.toString());
            //logger.info(shoppingListService.findShoppingListById(household.getShoppingLists())));
            household.getShoppingLists().stream().forEach(msg->logger.info(msg.toString()));
            return housheoldDTO ;   
        }else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * createHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public Long createHousehold(HouseholdDTO householdDTO) {
        Household household = new Household();
        // household.setName(householdDTO.getName());
        householdService.createHousehold(household);
        return household.getId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * getAllHouseholds()
     */
    @Override
    public List<HouseholdDTO> findAll() {
        return beanMappingService.mapTo(householdService.findAll(), HouseholdDTO.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * updateHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public void updateHousehold(HouseholdDTO household) {
        householdService.updateHousehold(beanMappingService.mapTo(household, Household.class));
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * deleteHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public void deleteHousehold(HouseholdDTO household) {
        householdService.deleteHousehold(beanMappingService.mapTo(household, Household.class));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#removeTenant
     * (cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO,
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public void removeTenant(UserDTO tenant, HouseholdDTO household) {
        householdService.removeTenant(beanMappingService.mapTo(tenant, User.class), beanMappingService.mapTo(household, Household.class));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#addTenant(cz
     * .muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO,
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public void addTenant(UserDTO tenant, HouseholdDTO household) {
        householdService.addTenant(beanMappingService.mapTo(tenant, User.class), beanMappingService.mapTo(household, Household.class));
    }
}
