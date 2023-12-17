package com.dm.planetradehub.repository;


import com.dm.planetradehub.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    @Query(value = "select a from Aircraft a where (:type = '' or a.type = :type) and (:brand  = '' or a.brand = :brand) and (:model = '' or a.model = :model)")
    List<Aircraft> findAircraftsByParameters(@Param("type") String type, @Param("brand") String brand, @Param("model") String model);
}
