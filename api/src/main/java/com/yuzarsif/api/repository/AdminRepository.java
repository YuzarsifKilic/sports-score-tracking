package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
