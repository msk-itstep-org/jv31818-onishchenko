package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select username,password from users u join user_roles r on u.id=r.user_id where u.id=?", nativeQuery = true)
    User findByUsername(String username);


    //User findById(Integer i);

}
