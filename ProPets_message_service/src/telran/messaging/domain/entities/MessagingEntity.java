package telran.messaging.domain.entities;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="messages")
public class MessagingEntity {
	
	@Id
	public String id;
	public String userLogin;
	public String userName;
	public String avatar;
	public String datePost;
	public String text;
	public String[] images;
	
	public String getId() {
		return id;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getDatePost() {
		return datePost;
	}
	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	
	public MessagingEntity(String userLogin, String userName, String avatar, String text,
			String[] images) {
		super();
		this.userLogin = userLogin;
		this.userName = userName;
		this.avatar = avatar;
		this.datePost = Instant.now().toString();
		this.text = text;
		this.images = images;
	}
	
	public MessagingEntity() {
	}
}
