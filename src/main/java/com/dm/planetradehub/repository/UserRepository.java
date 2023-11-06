package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
