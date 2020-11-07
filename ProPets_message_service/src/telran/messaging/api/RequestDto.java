package telran.messaging.api;

public class RequestDto {
	
	public String text;
	public String[] images;
	
	public RequestDto(String text, String[] images) {
		super();
		this.text = text;
		this.images = images;
	}

	public RequestDto() {
	}
}
