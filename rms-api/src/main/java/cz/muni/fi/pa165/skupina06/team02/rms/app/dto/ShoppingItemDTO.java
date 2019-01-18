package cz.muni.fi.pa165.skupina06.team02.rms.app.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ShoppingItemDTO {
    private Long id;
    private String name;
    private Long quantity;
    private Boolean bought;
    
    private UserPublicDTO buyer;
    @JsonIgnoreProperties({"shoppingItems"})
    private ShoppingListDTO list;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }

    public void setBuyer(UserPublicDTO buyer) {
        this.buyer = buyer;
    }

    public void setList(ShoppingListDTO list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Boolean getBought() {
        return bought;
    }

    public UserPublicDTO getBuyer() {
        return buyer;
    }

    public ShoppingListDTO getList() {
        return list;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingItemDTO that = (ShoppingItemDTO) o;
        return name.equals(that.name) &&
                quantity.equals(that.quantity) &&
                bought.equals(that.bought) &&
                Objects.equals(buyer, that.buyer) &&
                Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, bought, buyer, list);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShoppingItemDTO [id=" + id + ", name=" + name + ", quantity=" + quantity + ", bought=" + bought
                + ", buyer=" + buyer + ", list=" + list + "]";
    }
}
