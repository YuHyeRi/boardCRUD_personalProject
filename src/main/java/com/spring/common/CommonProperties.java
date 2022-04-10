package com.spring.common;

public class CommonProperties {
	
	// 기본 셋팅
	// 기본 리스트 사이즈 => 열개씩 보여주고
	public static final int VIEWCOUNT = 10;
	
	// 기본 페이지 사이즈 => 하단에 열개씩 뜨게 [이전] 1 2 3 4 5 6 7 8 9 10 [다음]
	public static final int PAGECOUNT = 10;
	
	// 검색 기준일 => 쓸수도 안쓸수도 있음
	public static final int SEARCHDATEAREA = 30;
	
	// ajax result
	public static final String RESULT_SUCCESS = "SUCCESS";
	public static final String RESULT_ERROR = "ERROR";
	
	// file upload
	// 파일 업로드 경로
	public static final String FILE_UPLOAD_PATH = "C:\\Java\\works\\Spring\\.metadata\\.plugins"
												+ "\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps"
												+ "\\SampleSpring\\resources\\upload";
	
	// 허용파일 확장자
	public static final String FILE_EXT = "xls|ppt|doc|xlsx|pptx|docx|hwp|csv|jpg|jpeg|png|gif|bmp|tld|txt|pdf|zip|alz|7z";
	public static final String IMG_EXT = "jpg|jpeg|png|gif|bmp";
	
	// 암호화키(AES기반 16글자)
	// 이 키(goodeesmart12345)는 암호를 해독하는데 쓰는 키이기 때문에 중요
	public static final String SECURE_KEY = "goodeesmart12345";

}
