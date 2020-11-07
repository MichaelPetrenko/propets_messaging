package telran.messaging.service.impl;

import org.springframework.stereotype.Service;

import telran.messaging.api.ResponcePageableDto;
import telran.messaging.api.RequestDto;
import telran.messaging.api.ResponceMessagingDto;
import telran.messaging.service.interfaces.MessageManagement;

@Service
public class MessageManagementMongo implements MessageManagement {

	@Override
	public ResponceMessagingDto createPost(RequestDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceMessagingDto update(RequestDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceMessagingDto delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceMessagingDto getPostById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponcePageableDto viewPostPageable(int items, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceMessagingDto[] getUserData(String[] listID) {
		// TODO Auto-generated method stub
		return null;
	}

}
