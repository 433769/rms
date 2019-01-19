package cz.muni.fi.pa165.skupina06.team02.rms.app.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Vojtech Prusa
 * <p>
 * DTO class of User to transfer data between services
 */
public class UserDTO {

    private Long id;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    
    @JsonIgnoreProperties({"tenants","shoppingLists"})
    private Set<HouseholdDTO> households = new HashSet<>();

    @JsonIgnoreProperties({"households"})
    private List<ShoppingListDTO> shoppingLists = new ArrayList<>();

    /**
     * Constructor
     */
    public UserDTO() {

    }

    /**
     * Constructor
     *
     * @param id
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param households
     */
    public UserDTO(Long id, String password, String email, String firstName,
                   String lastName, Set<HouseholdDTO> households, List<ShoppingListDTO> shoppingLists) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.households = households;
        this.shoppingLists = shoppingLists;
    }

    public List<ShoppingListDTO> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<ShoppingListDTO> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }


    /**
     * Getter
     *
     * @return id instance
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter
     *
     * @param id instance
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter
     *
     * @return password instance
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter
     *
     * @param password instance
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter
     *
     * @return email instance
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter
     *
     * @param email instance
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter
     *
     * @return firstName instance
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter
     *
     * @param firstName instance
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter
     *
     * @return lastName instance
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter
     *
     * @param lastName instance
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<HouseholdDTO> getHouseholds() {
        return households;
    }

    public void setHouseholds(Set<HouseholdDTO> households) {
        this.households = households;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDTO other = (UserDTO) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", password=" + password + ", email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + "]";
    }
}
