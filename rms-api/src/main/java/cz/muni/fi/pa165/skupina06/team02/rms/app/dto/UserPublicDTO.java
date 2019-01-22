package cz.muni.fi.pa165.skupina06.team02.rms.app.dto;

import java.util.List;

/**
 * @author Vojtech Prusa
 *
 *         DTO class of User to transfer data between services
 *
 */
public class UserPublicDTO
{

    private Long id;
  
    private String email;
    
    private String firstName;

    private String lastName;

    private List<HouseholdDTO> householdsList;

    
    /**
     * Getter
     *
     * @return householdsList instance
     */
    public List<HouseholdDTO> getHouseholdsList() {
        return householdsList;
    }

    /**
     * Setter
     * 
     * @param householdsList instance
     */
    public void setHouseholdsList(List<HouseholdDTO> householdsList) {
        this.householdsList = householdsList;
    }

    /**
     * Constructor
     */
    public UserPublicDTO(){
        
    }
    
    /**
     * Constructor
     *
     * @param id
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     */
    public UserPublicDTO(Long id, String password, String email, String firstName, String lastName, List<HouseholdDTO> householdsList) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.householdsList = householdsList;
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
        UserPublicDTO other = (UserPublicDTO) obj;
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
        return "UserDTO [id=" + id + ", email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + "]";
    }
    
}
