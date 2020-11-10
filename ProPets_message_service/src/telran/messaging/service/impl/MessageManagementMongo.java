package telran.messaging.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	private ResponceMessagingDto entityToResponseDto(MessagingEntity entity) {
		ResponceMessagingDto dto = new ResponceMessagingDto(entity.id, entity.userLogin, entity.userName,
				entity.avatar, entity.datePost, entity.text, entity.images);
		return dto;
	}
	
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
		MessagingEntity entity =  repo.findById(id).orElse(null);
		if(entity==null) {
			throw new NotExistsException();
		}
		ResponceMessagingDto resp = new ResponceMessagingDto(entity.id, entity.userLogin, entity.userName,
				entity.avatar, entity.datePost, entity.text, entity.images);
		
		return resp;
	}

	@Override
	public ResponcePageableDto viewPostPageable(int items, int currentPage) {
		Pageable pageable = PageRequest.of(currentPage, items);
		repo.findAll(pageable);
		
		int itemsTotal = repo.findAll(pageable).getNumberOfElements();
		List<MessagingEntity> postsList = repo.findAll(pageable).toList();
		
		List<ResponceMessagingDto> res = postsList.stream()
				.map(post -> entityToResponseDto(post))
				.collect(Collectors.toList());
		
		ResponcePageableDto pDto = new ResponcePageableDto(items, currentPage, itemsTotal, res);
		return pDto;
	}

	@Override
	public Object[] getUserData(String[] listID) {
		List<ResponceMessagingDto> list = new ArrayList<>();
		for(int i=0 ; i<listID.length; i++) {
			MessagingEntity entity =  repo.findById(listID[i]).orElse(null);
			if(entity!=null) {
				ResponceMessagingDto dto = entityToResponseDto(entity);
				list.add(dto);
			}
		}
		return  list.toArray();
	}

}
