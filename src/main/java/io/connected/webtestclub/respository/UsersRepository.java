package io.connected.webtestclub.respository;

import io.connected.webtestclub.respository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UsersRepository extends JpaRepository<UserEntity, Long>, Repository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}
