package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.controllers;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserPublicDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.UserFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.ApiUris;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.exceptions.ResourceNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

//@Secured("USER")
@RequestMapping(ApiUris.API_PREFIX)
public abstract class BaseController {

    /**
     * @param userFacade
     * @return
     */
    public UserDTO getCurrentUser(UserFacade userFacade) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        // username = user email
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        UserDTO userDTO = userFacade.findUserByEmail(username);
        if (userDTO == null) {
            throw new ResourceNotFoundException();
        }
        /*
        UserPublicDTO up = new UserPublicDTO();
        up.setEmail(userDTO.getEmail());
        up.setFirstName(userDTO.getFirstName());
        up.setLastName(userDTO.getLastName());
        up.setId(userDTO.getId());
        if (householdFacade != null) {
           List<HouseholdDTO> housheoldsOfUser = householdFacade.findAll().stream()
                    .filter(
                            h -> h.getTenants().stream().filter(
                                    t -> t.getId().equals(up.getId())).findFirst().get().getHouseholds()).collect(Collectors.toList());
        
        }*/
        return userDTO;
    }

}