package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRoleRepository extends JpaRepository<UserRole,Integer> {
}
