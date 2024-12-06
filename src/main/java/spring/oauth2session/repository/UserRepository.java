package spring.oauth2session.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.oauth2session.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
}
