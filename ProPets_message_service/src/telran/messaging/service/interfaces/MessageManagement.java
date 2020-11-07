package telran.messaging.service.interfaces;

import telran.messaging.api.ResponcePageableDto;
import telran.messaging.api.RequestDto;
import telran.messaging.api.ResponceMessagingDto;

public interface MessageManagement {
	
	ResponceMessagingDto createPost(RequestDto dto); //get ret x-token
	ResponceMessagingDto update(RequestDto dto); //get ret x-token
	ResponceMessagingDto delete(String id); //get ret x-token
	ResponceMessagingDto getPostById(String id); //get ret x-token;
	ResponcePageableDto viewPostPageable(int items, int currentPage); //get ret x-token;
	ResponceMessagingDto[] getUserData(String[] listID);
	
}
