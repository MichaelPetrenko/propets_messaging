package telran.lostfound.service.impl;

import org.springframework.stereotype.Service;

import telran.lostfound.api.PageableDto;
import telran.lostfound.api.RequestDto;
import telran.lostfound.api.ResponceDto;
import telran.lostfound.service.interfaces.MessageManagement;

@Service
public class MessageManagementMongo implements MessageManagement {

	@Override
	public ResponceDto createPost(RequestDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceDto update(RequestDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceDto delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceDto getPostById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageableDto viewPostPageable(int items, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceDto[] getUserData(String[] listID) {
		// TODO Auto-generated method stub
		return null;
	}

}
