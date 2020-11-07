package telran.messaging.api;

public class ResponceMessagingDto {
	
	public String id;
	public String userLogin;
	public String userName;
	public String avatar;
	public String datePost;
	public String text;
	public String[] images;
	
	public ResponceMessagingDto(String id, String userLogin, String userName, String avatar, String timeStamp, String text,
			String[] images) {
		super();
		this.id = id;
		this.userLogin = userLogin;
		this.userName = userName;
		this.avatar = avatar;
		this.datePost = timeStamp;
		this.text = text;
		this.images = images;
	}
	
	public ResponceMessagingDto() {
		super();
	}
	
}
