package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;

public interface UserRepository extends CrudRepository<User,Integer> {
}
