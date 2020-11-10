package telran.messaging.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

//import java.util.List;

import telran.messaging.domain.entities.MessagingEntity;

//@Repository
//Можно не писать аннотацию, потому-что аннотация есть в монго репозиторий
public interface MessagingRepository extends MongoRepository<MessagingEntity, String> {

//	List<MessagingEntity> findAllBy(Pageable pageable);
//	List<MessagingEntity> findAllByTypePost(boolean typePost);
	
}
