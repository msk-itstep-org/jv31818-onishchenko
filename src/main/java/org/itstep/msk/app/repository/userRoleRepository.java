package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.userRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRoleRepository extends CrudRepository<userRole,Integer> {
}
