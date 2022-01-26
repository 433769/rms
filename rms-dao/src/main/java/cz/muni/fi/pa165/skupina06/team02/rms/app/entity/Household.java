package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Vojtech Prusa
 *
 * TODO @Data has to be otherwise compilation fails even though documentation says something else https://projectlombok.org/features/Data
 */
@Data
@Entity
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private String street;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private String buildingNumber;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private String zipCode;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private String state;

    @Getter
    @OneToMany
    private List<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();

    @Getter
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
     * Adds Shopping list to list of Shopping lists of Household
     *
     * @param shoppingList instance
     */
    public void addToShoppingLists(ShoppingList shoppingList) {
        shoppingLists.add(shoppingList);
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
