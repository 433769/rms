package cz.muni.fi.pa165.skupina06.team02.rms.app.dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ShoppingListDTO {
    private Long id;
    private String name;
    @JsonIgnoreProperties({"list"})
    private List<ShoppingItemDTO> shoppingItems;
    private HouseholdDTO household;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ShoppingItemDTO> getShoppingItems() {
        return shoppingItems;
    }

    public HouseholdDTO getHousehold() {
        return household;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShoppingItems(List<ShoppingItemDTO> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public void setHousehold(HouseholdDTO household) {
        this.household = household;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListDTO that = (ShoppingListDTO) o;
        return name.equals(that.name) &&
                Objects.equals(shoppingItems, that.shoppingItems) &&
                Objects.equals(household, that.household);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shoppingItems, household);
    }

    @Override
    public String toString() {
        return "ShoppingListDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shoppingItems=" + shoppingItems +
                ", household=" + household +
                '}';
    }
}
