package com.nlu.mainguyen.travelserviceapi.model;

import com.nlu.mainguyen.travelserviceapi.entities.Places;

// API danh sách
public class ResponseListDTO {
    public int Status;    // Kết quả  1 : thành công, 2 : Thất bại
    public String Message; // Câu thông báo "ID không tồn tại"

    public Iterable<Places> Item;

    public ResponseListDTO(int status, String message, Iterable<Places> item) {
        Status = status;
        Message = message;
        Item = item;
    }
}