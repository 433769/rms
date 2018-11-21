package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author Vojtech Prusa
 *
 */
@Entity
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false)
    private String buildingNumber;

    @NotNull
    @Column(nullable = false)
    private String zipCode;

    @NotNull
    @Column(nullable = false)
    private String state;

    @OneToMany
    private List<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();

    @ManyToMany
    private Set<User> tenants = new HashSet<User>();

    /**
     * Remove tenant
     * 
     * @param tenant
     */
    public void removeTenant(User tenant) {
        tenants.remove(tenant);
    }

    /**
     * Add tenant to tenants and Household to tenant
     * 
     * @param tenant
     */
    public void addTenant(User tenant) {
        tenants.add(tenant);
        // TODO some other magic like:
        // https://github.com/fi-muni/PA165/blob/864034ae6ae116da53c34e45b92edfe3a16b346b/eshop-persistence/src/main/java/cz/fi/muni/pa165/entity/Product.java#L81
        // g.e.:
        // tenant.addHousehold(this);
    }

    /**
     * Getter
     * 
     * @return tenants instacne
     */
    public Set<User> getTenants() {
        return tenants;
    }

    /**
     * Setter
     * 
     * @param tenants the tenants to set
     */
    public void setTenants(Set<User> tenants) {
        this.tenants = tenants;
    }

    /**
     * Default Constructor
     *
     * @param id
     */
    public Household() {
    }
    
    /**
     * Constructor
     *
     * @param id
     */
    public Household(Long id) {
        super();
        this.id = id;
    }

    
    /**
     * @return shopping list instance
     */
    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    /**
     * Adds Shopping list to list of Shopping lists of Household
     * 
     * @param shoppingList instance
     */
    public void addToShoppingLists(ShoppingList shoppingList) {
        shoppingLists.add(shoppingList);
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street value
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return building number
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * @param buildingNumber value
     */
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    /**
     * @return zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode value
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state value
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Household household = (Household) o;
        return Objects.equals(id, household.id) &&
                Objects.equals(street, household.street) &&
                Objects.equals(buildingNumber, household.buildingNumber) &&
                Objects.equals(zipCode, household.zipCode) &&
                Objects.equals(state, household.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, buildingNumber, zipCode, state);
    }
}
