package telran.messaging.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.messaging.api.MessageApiConstants;
import telran.messaging.api.ResponcePageableDto;
import telran.messaging.api.RequestCreatePostDto;
import telran.messaging.api.ResponceMessagingDto;
import telran.messaging.service.interfaces.MessageManagement;

@RestController
public class MessageController {

	@Autowired
	MessageManagement messageService;
	
	@PostMapping(value = MessageApiConstants.CREATE_POST)
	ResponceMessagingDto createPost(@RequestBody RequestCreatePostDto dto, 
			@PathVariable("login") String login, HttpServletRequest request) {
		return messageService.createPost(dto, login);
	};
	
	@PutMapping(value = MessageApiConstants.UPDATE_POST)
	ResponceMessagingDto update(@RequestBody RequestCreatePostDto dto, @PathVariable("id") String id) {
		return messageService.update(dto, id);
	}
	
	@DeleteMapping(value = MessageApiConstants.DELETE_POST)
	ResponceMessagingDto delete(@PathVariable("id") String id) { 
		return messageService.delete(id);
	}
	
	@GetMapping(value = MessageApiConstants.GET_POST_BY_ID)
	ResponceMessagingDto getPostById(@PathVariable("idPost") String idPost) {	
		return messageService.getPostById(idPost);
	}
	
	@GetMapping(value = MessageApiConstants.VIEW_POST_PAGEABLE)
	ResponcePageableDto viewPostPageable(@RequestParam("itemsOnPage") int itemsOnPage, @RequestParam("currentPage") int currentPage) {
		return messageService.viewPostPageable(itemsOnPage, currentPage);
	}
	
	@PostMapping(value = MessageApiConstants.GET_USER_DATA)
	Object[] getUserData(@RequestBody String[] listID) {
		return messageService.getUserData(listID);
	}
	
}
