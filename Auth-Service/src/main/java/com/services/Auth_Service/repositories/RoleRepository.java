package com.services.Auth_Service.repositories;

import com.services.Auth_Service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);

}
