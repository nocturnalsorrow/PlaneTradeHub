package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

}
