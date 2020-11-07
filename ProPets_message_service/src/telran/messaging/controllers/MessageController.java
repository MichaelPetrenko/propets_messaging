package telran.messaging.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.messaging.api.MessageApiConstants;
import telran.messaging.api.PageableDto;
import telran.messaging.api.RequestDto;
import telran.messaging.api.ResponceDto;
import telran.messaging.service.interfaces.MessageManagement;

@RestController
public class MessageController {

	@Autowired
	MessageManagement messageService;
	
	@PostMapping(value = MessageApiConstants.CREATE_POST)
	ResponceDto createPost(@RequestBody RequestDto dto) {
		return messageService.createPost(dto);
	};
	
	@PutMapping(value = MessageApiConstants.UPDATE_POST)
	ResponceDto update(@RequestBody RequestDto dto) {
		return messageService.update(dto);
	}
	
	@DeleteMapping(value = MessageApiConstants.DELETE_POST)
	ResponceDto delete(@PathVariable String postID) { 
		return messageService.delete(postID);
	}
	
//	"/message/en/v1/{id}";
	@GetMapping(value = MessageApiConstants.GET_POST_BY_ID)
	ResponceDto getPostById(@PathVariable String id) {	
		return messageService.getPostById(id);
	}
	@GetMapping(value = MessageApiConstants.VIEW_POST_PAGEABLE)
	PageableDto viewPostPageable(@PathVariable int items, int currentPage) {
		return messageService.viewPostPageable(items, currentPage);
	}
	
	@PostMapping(value = MessageApiConstants.GET_USER_DATA)
	ResponceDto[] getUserData(String[] listID) {
		return getUserData(listID);
	}
	
}
