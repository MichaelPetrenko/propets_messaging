package telran.messaging.api;

public class RequestCreatePostDto {
	
	public String userName;
	public String avatar;
	public String text;
	public String[] images;
	
	public RequestCreatePostDto(String userName, String avatar, String text, String[] images) {
		super();
		this.userName = userName;
		this.avatar = avatar;
		this.text = text;
		this.images = images;
	}

	public RequestCreatePostDto() {
	}
}
