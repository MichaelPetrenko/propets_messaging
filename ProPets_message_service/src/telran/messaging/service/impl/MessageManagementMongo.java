package telran.messaging.service.impl;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;

import telran.messaging.api.ResponcePageableDto;
import telran.messaging.api.codes.NoContentException;
import telran.messaging.api.codes.NotExistsException;
import telran.messaging.dao.MessagingRepository;
import telran.messaging.domain.entities.MessagingEntity;
import telran.messaging.api.codes.BadRequestException;
import telran.messaging.api.codes.BadTokenException;
import telran.messaging.api.codes.ForbiddenException;
import telran.messaging.api.codes.BadURIException;
import telran.messaging.api.RequestCreatePostDto;
import telran.messaging.api.ResponceMessagingDto;
import telran.messaging.service.interfaces.MessageManagement;

@Service
public class MessageManagementMongo implements MessageManagement {

	@Autowired
	MessagingRepository repo;
	
	@Autowired
	RestTemplate restTemplate;

	private ResponceMessagingDto entityToResponseDto(MessagingEntity entity) {
		ResponceMessagingDto dto = new ResponceMessagingDto(entity.id, entity.userLogin, entity.userName, entity.avatar,
				entity.datePost, entity.text, entity.images);
		return dto;
	}

	@Override
	public ResponceMessagingDto createPost(RequestCreatePostDto dto, String userLogin, String xToken) {

		if (dto == null) {
			throw new NoContentException("DTO not exists");
		}

		if (dto.text.length() == 0 || dto.images.length == 0) {
			throw new NoContentException("Text or photos is empty!");
		}

		MessagingEntity entity = new MessagingEntity(userLogin, dto.userName, dto.avatar, dto.text, dto.images);
		repo.save(entity);

		String id = entity.getId();

		try {
			addPostToActivites(userLogin, xToken, id);
		} catch (Exception e) {
			if (e instanceof Forbidden) {
				throw new ForbiddenException();
			} else if (e instanceof Unauthorized) {
				throw new BadTokenException();
			} else if (e instanceof BadRequest) {
				throw new BadRequestException();
			} else
				throw new NotExistsException();
		}

		ResponceMessagingDto resp = new ResponceMessagingDto(entity.id, entity.userLogin, entity.userName,
				entity.avatar, entity.datePost, entity.text, entity.images);

		return resp;
	}

	private void addPostToActivites(String userLogin, String xToken, String id) {
		String endpointAddActivity = 
				"https://propets-me.herokuapp.com/" 
				+ "account/en/v1/" 
				+ userLogin 
				+ "/activity/"
				+ id;

		URI uri;
		try {
			uri = new URI(endpointAddActivity);
		} catch (Exception e) {
			System.out.println("Error URI");
			throw new BadURIException();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("X-Token", xToken);
		headers.set("X-ServiceName", "message");

		HttpEntity<Void> request = new HttpEntity<>(headers);
		@SuppressWarnings("unused")
		ResponseEntity<Void> responceFromAddUserActivity = restTemplate.exchange(uri, HttpMethod.PUT, request,
				Void.class);

	}

	@Override
	public ResponceMessagingDto update(RequestCreatePostDto dto, String id) {

		if (dto == null || id == null) {
			throw new NoContentException("DTO or id not exists!");
		}

		MessagingEntity entity = repo.findById(id).orElse(null);
		if (entity == null) {
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
	public ResponceMessagingDto delete(String id, String xToken) {

		MessagingEntity entity = repo.findById(id).orElse(null);
		if (entity == null) {
			throw new NotExistsException();
		}
		try {
			removePostFromActivites(entity.userLogin, id, xToken);
		} catch (Exception e) {
			e.getStackTrace();
			if (e instanceof Forbidden) {
				throw new ForbiddenException();
			} else if (e instanceof Unauthorized) {
				System.out.println("if unauth case");
				throw new BadTokenException();
			} else if (e instanceof BadRequest) {
				throw new BadRequestException();
			} else
			throw new NotExistsException();
		}
		repo.deleteById(id);
		ResponceMessagingDto resp = new ResponceMessagingDto(entity.id, entity.userLogin, entity.userName,
				entity.avatar, entity.datePost, entity.text, entity.images);

		return resp;
	}

	private void removePostFromActivites(String userLogin, String id, String xToken) {
		String endpointRemoveActivity = 
				"https://propets-me.herokuapp.com/" //accounting service
				+ "account/en/v1/" 
				+ userLogin 
				+ "/activity/"																	
				+ id;

		URI uri;
		try {
			uri = new URI(endpointRemoveActivity);
		} catch (Exception e) {
			System.out.println("Error URI");
			throw new BadURIException();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("X-Token", xToken);
		headers.set("X-ServiceName", "message");
		
		HttpEntity<Void> request = new HttpEntity<>(headers);
		@SuppressWarnings("unused")
		ResponseEntity<Void> responceFromAddUserActivity = restTemplate.exchange(uri, HttpMethod.DELETE, request,
				Void.class);
		
	}

	@Override
	public ResponceMessagingDto getPostById(String id) {
		MessagingEntity entity = repo.findById(id).orElse(null);
		if (entity == null) {
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

		List<ResponceMessagingDto> res = postsList.stream().map(post -> entityToResponseDto(post))
				.collect(Collectors.toList());

		ResponcePageableDto pDto = new ResponcePageableDto(items, currentPage, itemsTotal, res);
		return pDto;
	}

	@Override
	public Object[] getUserData(String[] listID) {
		List<ResponceMessagingDto> list = new ArrayList<>();
		for (int i = 0; i < listID.length; i++) {
			MessagingEntity entity = repo.findById(listID[i]).orElse(null);
			if (entity != null) {
				ResponceMessagingDto dto = entityToResponseDto(entity);
				list.add(dto);
			}
		}
		return list.toArray();
	}

}
