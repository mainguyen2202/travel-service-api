package com.nlu.mainguyen.travelserviceapi.config;


public enum StatusCode {
    SUCCESS,//1
    FAILED,//2
}


/*
StatusCode của http

OK : 200 thành công
BAD_REQUEST : 400 lỗi
CREATED : 201 insert thành công
UNAUTHORIZED : 401 authen thất bại <> login thất bại <> không có token bearer jwt
*/