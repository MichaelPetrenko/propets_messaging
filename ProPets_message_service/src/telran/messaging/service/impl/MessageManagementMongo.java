package telran.messaging.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.messaging.api.ResponcePageableDto;
import telran.messaging.api.codes.NoContentException;
import telran.messaging.api.codes.NotExistsException;
import telran.messaging.dao.MessagingRepository;
import telran.messaging.domain.entities.MessagingEntity;
import telran.messaging.api.RequestCreatePostDto;
import telran.messaging.api.ResponceMessagingDto;
import telran.messaging.service.interfaces.MessageManagement;

@Service
public class MessageManagementMongo implements MessageManagement {
	
	@Autowired
	MessagingRepository repo;

	@Override
	public ResponceMessagingDto createPost(RequestCreatePostDto dto, String userLogin) {
		
		if (dto == null) {
			throw new NoContentException("DTO not exists");
		}
		
		if(dto.text.length()==0 || dto.images.length==0) {
			throw new NoContentException("Text or photos is empty!");
		}
		
		MessagingEntity entity = new MessagingEntity(userLogin, dto.userName, dto.avatar, dto.text, dto.images);
		repo.save(entity);
		
		ResponceMessagingDto resp = new ResponceMessagingDto(entity.id, entity.userLogin, entity.userName,
				entity.avatar, entity.datePost, entity.text, entity.images);
		
		return resp;
	}

	@Override
	public ResponceMessagingDto update(RequestCreatePostDto dto, String id) {
		
		if(dto==null || id==null) {
			throw new NoContentException("DTO or id not exists!");
		}
		
		MessagingEntity entity = repo.findById(id).orElse(null);
		if(entity==null) {
			throw new NotExistsException();
		}
		
		entity.setText(dto.text);
		entity.setImages(dto.images);
		entity.setUserName(dto.userName);
		entity.setAvatar(dto.avatar);
		entity.setDatePost(Instant.now().toString());
		
		repo.save(entity);
		ResponceMessagingDto resp = new ResponceMessagingDto(entity.id, entity.userLogin, entity.userName,
				entity.avatar, entity.datePost, entity.text, entity.images);
		
		return resp;
	}

	@Override
	public ResponceMessagingDto delete(String id) {
		
		MessagingEntity entity = repo.findById(id).orElse(null);
		if(entity==null) {
			throw new NotExistsException();
		}
		
		repo.deleteById(id);
		ResponceMessagingDto resp = new ResponceMessagingDto(entity.id, entity.userLogin, entity.userName,
				entity.avatar, entity.datePost, entity.text, entity.images);
		
		return resp;
	}

	@Override
	public ResponceMessagingDto getPostById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponcePageableDto viewPostPageable(int items, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceMessagingDto[] getUserData(String[] listID) {
		// TODO Auto-generated method stub
		return null;
	}

}
