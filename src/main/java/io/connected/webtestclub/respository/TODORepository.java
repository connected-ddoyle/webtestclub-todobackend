package io.connected.webtestclub.respository;

import io.connected.webtestclub.respository.entity.TODOEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface TODORepository extends JpaRepository<TODOEntity, Long>, Repository<TODOEntity, Long> {
	TODOEntity findTODOEntityByTodo(String todo);
}
