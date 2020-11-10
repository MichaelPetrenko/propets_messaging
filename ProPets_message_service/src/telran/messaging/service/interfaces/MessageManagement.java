package telran.messaging.service.interfaces;

import telran.messaging.api.ResponcePageableDto;
import telran.messaging.api.RequestCreatePostDto;
import telran.messaging.api.ResponceMessagingDto;

public interface MessageManagement {
	
	ResponceMessagingDto createPost(RequestCreatePostDto dto, String userLogin); //get ret x-token
	ResponceMessagingDto update(RequestCreatePostDto dto, String id); //get ret x-token
	ResponceMessagingDto delete(String id); //get ret x-token
	ResponceMessagingDto getPostById(String id); //get ret x-token;
	ResponcePageableDto viewPostPageable(int items, int currentPage); //get ret x-token;
	Object[] getUserData(String[] listID);
	
}
