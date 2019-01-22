package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.controllers;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserPublicDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.UserFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.facades.UserFacadeImpl;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.ApiUris;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.exceptions.ResourceNotFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * REST Controller for Users
 * 
 * @author Vojtech Prusa
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_USERS)
public class UsersController extends BaseController {

    final static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Inject
    private UserFacade userFacade;

    /**
     *
     * add user according and return id
     * 
     * @param UserDTO as converted json from body
     * @return UserDTO id
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final Long register(@RequestBody UserDTO userDTO) throws Exception {
        // public final UserDTO registerUser(@Valid @ModelAttribute("user") UserDTO
        // userDTO, BindingResult bindingResult,
        // Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder
        // uriBuilder) throws Exception {

        logger.debug("rest register(UserDTO)", userDTO.toString());
        userFacade.registerUser(userDTO, userDTO.getPassword());
        userDTO = userFacade.findUserByEmail(userDTO.getEmail());
        if (userDTO == null) {
            throw new ResourceNotFoundException();
        }
        return userDTO.getId();
    }

    /**
     *
     * set users household according ids
     * 
     * @param UserDTO as converted json from body
     * @return UserDTO id
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/sethousehold/{userId}/{householdId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final boolean setHousehold(@PathVariable("userId") long userId,
            @PathVariable("householdId") long householdId) throws Exception {
        return userFacade.updateHousheold(userId, householdId);
    }

    /**
     *
     * 
     * @param id user identifier
     * @return UserDTO
     * @throws ResourceNotFoundException
     */
    /*
     * @RequestMapping(value = "/authenticate/", method = RequestMethod.POST,
     * produces = MediaType.APPLICATION_JSON_VALUE, consumes =
     * MediaType.APPLICATION_JSON_VALUE) public final Long authenticate(@RequestBody
     * UserDTO userDTO) throws Exception { //public final UserDTO
     * registerUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult
     * bindingResult, // Model model, RedirectAttributes redirectAttributes,
     * UriComponentsBuilder uriBuilder) throws Exception {
     * 
     * logger.debug("rest registerUser({})", userDTO.toString());
     * userFacade.registerUser(userDTO, userDTO.getPassword()); userDTO =
     * userFacade.findUserByEmail(userDTO.getEmail()); if (userDTO == null) { throw
     * new ResourceNotFoundException(); } return userDTO.getId(); }
     */

    /**
     * returns all users according to a Summary View
     * {@link cz.muni.fi.pa165.team02.rms.app.web.views.View}
     *
     * @return list of UserDTOs
     * @throws JsonProcessingException
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> getUsers() throws JsonProcessingException {
        logger.debug("rest getUsers()");
        return userFacade.getAllUsers();
    }

    public UserDTO getUserById( long id) throws Exception {
        logger.debug("rest getUser({})", id);
        UserDTO userDTO = userFacade.findUserById(id);
        if (userDTO == null) {
            throw new ResourceNotFoundException();
        }
        return userDTO;
    }
    
    /**
     *
     * getting user according to id
     * 
     * @param id user identifier
     * @return UserDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUser(@PathVariable("id") long id) throws Exception {
        return getUserById(id);
    }
    
    /**
    *
    * getting user according to id
    * 
    * @param id user identifier
    * @return UserDTO
    * @throws ResourceNotFoundException
    */
   @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public final UserDTO getUserShort(@PathVariable("id") long id) throws Exception {
       return getUserById(id);
   }

    /**
     *
     * getting user according to email
     * 
     * @param id user identifier
     * @return UserDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/email/{string}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserByEmail(@PathVariable("email") String email) throws Exception {

        logger.debug("rest getUser({})", email);
        UserDTO userDTO = userFacade.findUserByEmail(email);
        if (userDTO == null) {
            throw new ResourceNotFoundException();
        }
        return userDTO;
    }

    /**
     *
     * get currently logged user
     * 
     * @param id user identifier
     * @return UserDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getLoggedUser() throws Exception {
        logger.debug("rest getLoggedUser({})");
        return this.getCurrentUser(userFacade);
    }

}
