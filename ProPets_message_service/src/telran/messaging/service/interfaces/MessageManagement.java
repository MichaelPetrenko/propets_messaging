package telran.messaging.service.interfaces;

import telran.messaging.api.PageableDto;
import telran.messaging.api.RequestDto;
import telran.messaging.api.ResponceDto;

public interface MessageManagement {
	
	ResponceDto createPost(RequestDto dto); //get ret x-token
	ResponceDto update(RequestDto dto); //get ret x-token
	ResponceDto delete(String id); //get ret x-token
	ResponceDto getPostById(String id); //get ret x-token;
	PageableDto viewPostPageable(int items, int currentPage); //get ret x-token;
	ResponceDto[] getUserData(String[] listID);
	
}
