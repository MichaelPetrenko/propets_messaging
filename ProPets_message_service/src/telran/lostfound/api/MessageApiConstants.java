package telran.lostfound.api;

public interface MessageApiConstants {
	
	String CREATE_POST = 						"/message/en/v1/";
//	String CREATE_POST = 						"/message/en/v1/{login}";
	
	String UPDATE_POST = 						"/message/en/v1/";
//	String UPDATE_POST = 						"/message/en/v1/{id}";
	
	String DELETE_POST = 						"/message/en/v1/";
//	String DELETE_POST = 						"/message/en/v1/{id}";
	
	String GET_POST_BY_ID = 					"/message/en/v1/{idPost}";
//	String GET_POST_BY_ID = 					"/message/en/v1/{idPost}";

	String VIEW_POST_PAGEABLE = 				"/message/en/v1/view/?itemsOnPage=10&currentPage=0";
//	String VIEW_POST_PAGEABLE = 				"/message/en/v1/view/?itemsOnPage=10&currentPage=0";
	
	String GET_USER_DATA = 						"/message/en/v1/userdata";


}
