package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author Vojtech Prusa
 */
@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany
    // @OrderBy("tableColumnName DESC")
    // @JoinColumn(name="tableFK")
    private List<ShoppingItem> shoppingItems = new ArrayList<ShoppingItem>();

    @ManyToOne
    @JoinColumn()
    private Household household;


    /**
     * @return household instance
     */
    public Household getHousehold() {
        return household;
    }

    /**
     * @param household instance
     */
    public void setHousehold(Household household) {
        this.household = household;
    }

    /**
     * Default eConstructor
     *
     * @param id
     */
    public ShoppingList() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public ShoppingList(Long id) {
        super();
        this.id = id;
    }

    /**
     * @return shopping list instance
     */
    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    /**
     * Add ShoppingItem to list
     *
     * @param shoppingItem instance
     */
    public void addItem(ShoppingItem shoppingItem) {
        shoppingItems.add(shoppingItem);
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name value
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

