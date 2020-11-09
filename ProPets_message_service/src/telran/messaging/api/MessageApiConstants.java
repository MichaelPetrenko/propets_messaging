package telran.messaging.api;

public interface MessageApiConstants {
	
	String CREATE_POST = 						"/message/en/v1/login/{login}"; // - Attention! changed from /v1/login/
//	String CREATE_POST = 						"/message/en/v1/login/{login}";
//	String CREATE_POST = 						"/message/en/v1/{login}";
	
	String UPDATE_POST = 						"/message/en/v1/{id}";
//	String UPDATE_POST = 						"/message/en/v1/{id}";
	
	String DELETE_POST = 						"/message/en/v1/{id}";
//	String DELETE_POST = 						"/message/en/v1/{id}";
	
	String GET_POST_BY_ID = 					"/message/en/v1/{idPost}";
//	String GET_POST_BY_ID = 					"/message/en/v1/{idPost}";

	String VIEW_POST_PAGEABLE = 				"/message/en/v1/view/";
//	String VIEW_POST_PAGEABLE = 				"/message/en/v1/view/?itemsOnPage=10&currentPage=0";
	
	String GET_USER_DATA = 						"/message/en/v1/userdata";


}
