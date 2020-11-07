package telran.messaging.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.messaging.api.MessageApiConstants;
import telran.messaging.api.ResponcePageableDto;
import telran.messaging.api.RequestDto;
import telran.messaging.api.ResponceMessagingDto;
import telran.messaging.service.interfaces.MessageManagement;

@RestController
public class MessageController {

	@Autowired
	MessageManagement messageService;
	
	@PostMapping(value = MessageApiConstants.CREATE_POST)
	ResponceMessagingDto createPost(@RequestBody RequestDto dto, 
			@PathVariable("login") String login, HttpServletRequest request) {
		System.out.println(" = = = createPost Controller");
		String userName = request.getHeader("X-Username");
		String avatar = request.getHeader("X-Avatar");
		return messageService.createPost(dto, login, userName, avatar);
	};
	
//	@PutMapping(value = MessageApiConstants.UPDATE_POST)
//	ResponceMessagingDto update(@RequestBody RequestDto dto) {
//		return messageService.update(dto);
//	}
//	
//	@DeleteMapping(value = MessageApiConstants.DELETE_POST)
//	ResponceMessagingDto delete(@PathVariable String postID) { 
//		return messageService.delete(postID);
//	}
//	
////	"/message/en/v1/{id}";
//	@GetMapping(value = MessageApiConstants.GET_POST_BY_ID)
//	ResponceMessagingDto getPostById(@PathVariable String id) {	
//		return messageService.getPostById(id);
//	}
//	@GetMapping(value = MessageApiConstants.VIEW_POST_PAGEABLE)
//	ResponcePageableDto viewPostPageable(@PathVariable int items, int currentPage) {
//		return messageService.viewPostPageable(items, currentPage);
//	}
//	
//	@PostMapping(value = MessageApiConstants.GET_USER_DATA)
//	ResponceMessagingDto[] getUserData(String[] listID) {
//		return getUserData(listID);
//	}
	
}
