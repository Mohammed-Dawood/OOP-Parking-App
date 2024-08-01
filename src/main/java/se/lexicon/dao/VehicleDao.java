package se.lexicon.dao;

import se.lexicon.model.Vehicle;

import java.util.Optional;
import java.util.Collection;

public interface VehicleDao {
    Optional<Vehicle> find(String licensePlate, int customerId);

    Vehicle create(Vehicle vehicle);

    boolean remove(String licensePlate, int customerId);

    void update(Vehicle vehicle);

    Collection<Vehicle> findByCustomerId(int customerId);
}
