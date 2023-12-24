package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query(value = "select a from Advertisement a " +
            "where (a.aircraft.type = :type or :type = '') " +
            "and (a.aircraft.manufacturer = :manufacturer or :manufacturer  = '') " +
            "and (a.aircraft.model = :model or :model = '') " +
            "and (a.aircraft.year = :year or :year = '0') " +
            "and (a.price = :price or :price = '0') ORDER BY a.publicationDate desc")
    List<Advertisement> findAdvertisementsByParameters(@Param("type") String type,
                                                       @Param("manufacturer") String manufacturer,
                                                       @Param("model") String model,
                                                       @Param("year") int year,
                                                       @Param("price") int price);
}
