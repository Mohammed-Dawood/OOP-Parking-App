package se.lexicon.dao;

import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private final List<ParkingSpot> storage = new ArrayList<>();

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        if (parkingSpot == null) {
            throw new IllegalArgumentException("ParkingSpot should not be null.");
        }

        if (find(parkingSpot.getSpotNumber(), parkingSpot.getAreaCode()).isPresent()) {
            throw new IllegalArgumentException("ParkingSpot with the same spotNumber and areaCode already exists.");
        }

        storage.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public Optional<ParkingSpot> find(int spotNumber, int areaCode) {
        for (ParkingSpot spot : storage) {
            if (spot.getSpotNumber() == spotNumber && spot.getAreaCode() == areaCode) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
        List<ParkingSpot> foundSpots = new ArrayList<>();
        for (ParkingSpot spot : storage) {
            if (spot.getAreaCode() == areaCode) {
                foundSpots.add(spot);
            }
        }
        return foundSpots;
    }

    @Override
    public void occupyParkingSpot(int spotNumber, int areaCode) {
        Optional<ParkingSpot> spotOptional = find(spotNumber, areaCode);
        spotOptional.ifPresent(ParkingSpot::occupy);
    }

    @Override
    public void vacateParkingSpot(int spotNumber, int areaCode) {
        Optional<ParkingSpot> spotOptional = find(spotNumber, areaCode);
        spotOptional.ifPresent(ParkingSpot::vacate);
    }
}
