package com.nlu.mainguyen.travelserviceapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// API update, delete, create
@Data
public class ResponseDTO {

    @JsonProperty("status")
    public int Status;    // Kết quả  1 : thành công, 2 : Thất bại

    @JsonProperty("message")
    public String Message; // Câu thông báo "ID không tồn tại"

    @JsonProperty("data")
    public UserOutputDTO Data;

    public ResponseDTO(int status, String message) {
        Status = status;
        Message = message;
    }

    public ResponseDTO(int status, String message, UserOutputDTO data) {
        Status = status;
        Message = message;
        Data = data;
    }
}