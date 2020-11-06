package telran.lostfound.service.interfaces;

import telran.lostfound.api.PageableDto;
import telran.lostfound.api.RequestDto;
import telran.lostfound.api.ResponceDto;

public interface MessageManagement {
	
	ResponceDto createPost(RequestDto dto); //get ret x-token
	ResponceDto update(RequestDto dto); //get ret x-token
	ResponceDto delete(String id); //get ret x-token
	ResponceDto getPostById(String id); //get ret x-token;
	PageableDto viewPostPageable(int items, int currentPage); 
	ResponceDto[] getUserData(String[] listID); //???
	
}
