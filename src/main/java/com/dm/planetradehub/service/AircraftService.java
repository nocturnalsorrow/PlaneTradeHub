package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Aircraft;

import java.util.List;
import java.util.Optional;

public interface AircraftService {
    List<Aircraft> getAllAircrafts();

    List<Aircraft> findAircraftsByParameters(Aircraft aircraft);

    Optional<Aircraft> getAircraftById(Long id);

    Aircraft saveAircraft(Aircraft aircraft);

    void deleteAdvertisementById(Long id);
}
