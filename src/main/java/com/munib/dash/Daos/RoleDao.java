package com.munib.dash.Daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.munib.dash.models.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
Optional<Role> findByAuthority(String findByAuthority);
}
