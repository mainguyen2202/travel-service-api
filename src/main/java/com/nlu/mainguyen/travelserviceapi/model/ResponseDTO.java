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

    @JsonProperty("dataItinerariesDTO")
    public ItinerariesDTO DataItinerariesDTO;

    @JsonProperty("dataArticlesDTO")
    public ArticlesDTO DataArticlesDTO;

    @JsonProperty("dataItineraryArticlesDTO")
    public ItineraryArticlesDTO DataItineraryArticlesDTO;

    @JsonProperty("dataLikesDTO")
    public LikesDTO DataLikesDTO;

    @JsonProperty("dataFeedbacksDTO")
    public FeedbacksDTO DataFeedbacksDTO;

    @JsonProperty("dataShareItinerariesDTO")
    public ShareItinerariesDTO DataShareItinerariesDTO;

    @JsonProperty("dataHistoryArticlesDTO")
    public HistoryArticlesDTO DataHistoryArticlesDTO;

    @JsonProperty("dataContactsDTO")
    public ContactsDTO DataContactsDTO;


    @JsonProperty("dataPlacesDTO")
    public PlacesDTO DataPlacesDTO;

    @JsonProperty("dataCoordinatesDTO")
    public CoordinatesDTO DataCoordinatesDTO;

    public ResponseDTO(int status, String message) {
        Status = status;
        Message = message;
    }

    public ResponseDTO(int status, String message, UserOutputDTO data) {
        Status = status;
        Message = message;
        Data = data;
    }
    public ResponseDTO(int status, String message, ItinerariesDTO dataItinerariesDTO) {
        Status = status;
        Message = message;
        DataItinerariesDTO = dataItinerariesDTO;
    }
    public ResponseDTO(int status, String message, ArticlesDTO dataArticlesDTO) {
        Status = status;
        Message = message;
        DataArticlesDTO = dataArticlesDTO;
    }
    public ResponseDTO(int status, String message, ItineraryArticlesDTO dataItineraryArticlesDTO) {
        Status = status;
        Message = message;
        DataItineraryArticlesDTO = dataItineraryArticlesDTO;
    }
    public ResponseDTO(int status, String message, LikesDTO dataLikesDTO) {
        Status = status;
        Message = message;
        DataLikesDTO = dataLikesDTO;
    }
    public ResponseDTO(int status, String message, FeedbacksDTO dataFeedbacksDTO) {
        Status = status;
        Message = message;
        DataFeedbacksDTO = dataFeedbacksDTO;
    }
    
    public ResponseDTO(int status, String message, ShareItinerariesDTO dataShareItinerariesDTO) {
        Status = status;
        Message = message;
        DataShareItinerariesDTO = dataShareItinerariesDTO;
    }

    public ResponseDTO(int status, String message, HistoryArticlesDTO dataHistoryArticlesDTO) {
        Status = status;
        Message = message;
        DataHistoryArticlesDTO = dataHistoryArticlesDTO;
    }

    public ResponseDTO(int status, String message, ContactsDTO dataContactsDTO) {
        Status = status;
        Message = message;
        DataContactsDTO = dataContactsDTO;
    }

    public ResponseDTO(int status, String message, PlacesDTO dataPlacesDTO) {
        Status = status;
        Message = message;
        DataPlacesDTO = dataPlacesDTO;
    }

    public ResponseDTO(int status, String message, CoordinatesDTO dataCoordinatesDTO) {
        Status = status;
        Message = message;
        DataCoordinatesDTO = dataCoordinatesDTO;
    }
    
    
}