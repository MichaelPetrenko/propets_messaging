package telran.messaging.api;

import java.util.List;

public class ResponcePageableDto {
	
	public int itemsOnPage;
	public int currentPage;
	public int itemsTotal;
	public List<ResponceMessagingDto> posts;
	
	public ResponcePageableDto(int itemsOnPage, int currentPage, int itemsTotal, List<ResponceMessagingDto> posts) {
		super();
		this.itemsOnPage = itemsOnPage;
		this.currentPage = currentPage;
		this.itemsTotal = itemsTotal;
		this.posts = posts;
	}
	
	public ResponcePageableDto() {
	}
}
