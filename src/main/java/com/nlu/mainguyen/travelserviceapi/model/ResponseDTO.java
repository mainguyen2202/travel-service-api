package com.nlu.mainguyen.travelserviceapi.model;


// API update, delete
public class ResponseDTO {
    public int Status;    // Kết quả  1 : thành công, 2 : Thất bại
    public String Message; // Câu thông báo "ID không tồn tại"

    public ResponseDTO(int status, String message) {
        Status = status;
        Message = message;
    }
}