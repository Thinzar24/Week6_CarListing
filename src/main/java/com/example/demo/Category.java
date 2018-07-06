package com.example.demo;

import com.example.demo.Car;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String vehicleCatagory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleCatagory() {
        return vehicleCatagory;
    }

    public void setVehicleCatagory(String vehicleCatagory) {
        this.vehicleCatagory = vehicleCatagory;
    }

    @OneToMany(mappedBy = "Category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public Set<Car> cars;

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
