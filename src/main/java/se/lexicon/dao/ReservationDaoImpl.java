package se.lexicon.dao;

import se.lexicon.model.Reservation;
import se.lexicon.dao.sequencer.ReservationIdSequencer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {

    private List<Reservation> storage = new ArrayList<>();

    @Override
    public Reservation create(Reservation reservation) {
        // Validate the parameter
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation should not be null.");
        }
        if (reservation.getId() == null || reservation.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("Reservation ID should not be null or empty.");
        }
        if (reservation.getAssociatedVehicle() == null) {
            throw new IllegalArgumentException("Associated Vehicle should not be null.");
        }
        if (reservation.getParkingSpot() == null) {
            throw new IllegalArgumentException("Parking Spot should not be null.");
        }

        // Set a unique ID for the reservation and add to storage
        reservation.setId(ReservationIdSequencer.nextId()); // Generate unique ID
        storage.add(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> find(String id) {
        for (Reservation reservation : storage) {
            if (reservation.getId().equals(id)) {
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }

    // Add more methods as needed for your application

}
