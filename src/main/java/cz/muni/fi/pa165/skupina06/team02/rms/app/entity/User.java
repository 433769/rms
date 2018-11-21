package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.*;

/**
 * Class holding basic user info
 *
 * @author Martin Lacko
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "dedicatedBuyer")
    private List<ShoppingItem> items = new ArrayList<>();

    @ManyToMany(mappedBy = "tenants")
    private Set<Household> households = new HashSet<>();

    /**
     * Constructor with specific ID
     *
     * @param userID Specific ID for this user
     */
    public User(Long userID) {
        this.id = userID;
    }

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * @return Items, witch this user should buy
     */
    public List<ShoppingItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * @return Households in which this user lives
     */
    public Set<Household> getHouseholds() {
        return Collections.unmodifiableSet(households);
    }

    /**
     * Join existing household
     *
     * @param household household to join
     */
    public void joinHousehold(Household household) {
        this.households.add(household);
    }

    /**
     * @return ID of this user
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Email of this user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return First name of this user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return Last name of this user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return (Representation) of password for this user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param email Set email for this user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param firstName New first name of this user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName New last name of this user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param password New password for this user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, password);
    }
}
