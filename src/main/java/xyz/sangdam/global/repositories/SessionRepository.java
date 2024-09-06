package xyz.sangdam.global.repositories;

import xyz.sangdam.global.entities.SessionEntity;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<SessionEntity, String> {

}
