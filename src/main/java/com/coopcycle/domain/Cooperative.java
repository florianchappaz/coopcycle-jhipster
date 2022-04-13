package com.coopcycle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cooperative.
 */
@Entity
@Table(name = "cooperative")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cooperative implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "name", length = 30, nullable = false, unique = true)
    private String name;

    @JsonIgnoreProperties(value = { "cities", "cooperative" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Zone zone;

    @OneToMany(mappedBy = "cooperative")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "cooperative" }, allowSetters = true)
    private Set<DeliverMan> deliverMen = new HashSet<>();

    @OneToMany(mappedBy = "cooperative")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "city", "meals", "cooperative" }, allowSetters = true)
    private Set<Restaurant> restaurants = new HashSet<>();

    @OneToMany(mappedBy = "cooperative")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "city", "cooperative" }, allowSetters = true)
    private Set<Customer> customers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cooperative id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Cooperative name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Zone getZone() {
        return this.zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Cooperative zone(Zone zone) {
        this.setZone(zone);
        return this;
    }

    public Set<DeliverMan> getDeliverMen() {
        return this.deliverMen;
    }

    public void setDeliverMen(Set<DeliverMan> deliverMen) {
        if (this.deliverMen != null) {
            this.deliverMen.forEach(i -> i.setCooperative(null));
        }
        if (deliverMen != null) {
            deliverMen.forEach(i -> i.setCooperative(this));
        }
        this.deliverMen = deliverMen;
    }

    public Cooperative deliverMen(Set<DeliverMan> deliverMen) {
        this.setDeliverMen(deliverMen);
        return this;
    }

    public Cooperative addDeliverMan(DeliverMan deliverMan) {
        this.deliverMen.add(deliverMan);
        deliverMan.setCooperative(this);
        return this;
    }

    public Cooperative removeDeliverMan(DeliverMan deliverMan) {
        this.deliverMen.remove(deliverMan);
        deliverMan.setCooperative(null);
        return this;
    }

    public Set<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        if (this.restaurants != null) {
            this.restaurants.forEach(i -> i.setCooperative(null));
        }
        if (restaurants != null) {
            restaurants.forEach(i -> i.setCooperative(this));
        }
        this.restaurants = restaurants;
    }

    public Cooperative restaurants(Set<Restaurant> restaurants) {
        this.setRestaurants(restaurants);
        return this;
    }

    public Cooperative addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
        restaurant.setCooperative(this);
        return this;
    }

    public Cooperative removeRestaurant(Restaurant restaurant) {
        this.restaurants.remove(restaurant);
        restaurant.setCooperative(null);
        return this;
    }

    public Set<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<Customer> customers) {
        if (this.customers != null) {
            this.customers.forEach(i -> i.setCooperative(null));
        }
        if (customers != null) {
            customers.forEach(i -> i.setCooperative(this));
        }
        this.customers = customers;
    }

    public Cooperative customers(Set<Customer> customers) {
        this.setCustomers(customers);
        return this;
    }

    public Cooperative addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.setCooperative(this);
        return this;
    }

    public Cooperative removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.setCooperative(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cooperative)) {
            return false;
        }
        return id != null && id.equals(((Cooperative) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cooperative{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
