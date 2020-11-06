package telran.lostfound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.lostfound.api.MessageApiConstants;
import telran.lostfound.api.PageableDto;
import telran.lostfound.api.RequestDto;
import telran.lostfound.api.ResponceDto;
import telran.lostfound.service.interfaces.MessageManagement;

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
	ResponceDto delete(@RequestParam String postID) {  //PathVariable? _!
		return messageService.delete(postID);
	}
	
	@GetMapping(value = MessageApiConstants.GET_POST_BY_ID)
	ResponceDto getPostById(@RequestParam String id) {	//PathVariable? _!
		return messageService.getPostById(id);
	}
	@GetMapping(value = MessageApiConstants.VIEW_POST_PAGEABLE)
	PageableDto viewPostPageable(@RequestParam int items, int currentPage) {	//PathVariable? _!
		return messageService.viewPostPageable(items, currentPage);
	}
	
	@PostMapping(value = MessageApiConstants.GET_USER_DATA)
	ResponceDto[] getUserData(String[] listID) {
		return getUserData(listID);
	}
	
}
