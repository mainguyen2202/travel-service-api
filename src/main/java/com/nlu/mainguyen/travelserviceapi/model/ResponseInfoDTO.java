package com.nlu.mainguyen.travelserviceapi.model;

import com.nlu.mainguyen.travelserviceapi.entities.Places;

// API danh sách
public class ResponseInfoDTO {
    public int Status;    // Kết quả  1 : thành công (có dữ liệu hoặc ), 2 : Thất bại 3 : Không có dữ liệu
    public String Message; // Câu thông báo "ID không tồn tại"
    public Places Item;

    public ResponseInfoDTO(int status, String message, Places item) {
        Status = status;
        Message = message;
        Item = item;
    }
}