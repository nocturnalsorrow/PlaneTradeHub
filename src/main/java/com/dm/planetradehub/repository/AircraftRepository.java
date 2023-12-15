package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

}
