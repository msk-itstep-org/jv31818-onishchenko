package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRoleRepository extends CrudRepository<UserRole,Integer> {
}
