package com.nlu.mainguyen.travelserviceapi.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseListItineraryArticleDTO {// bài viết của nhiều địa điểm và nhiều topics

    public int Status;
    public String Message;
    public List<ItineraryArticlesDTO> Item;
    private double TotalDistance;

    public ResponseListItineraryArticleDTO(int status, String message) {
        Status = status;
        Message = message;
    }

    public ResponseListItineraryArticleDTO(int status, String message, List<ItineraryArticlesDTO> item,
            double totalDistance) {
        Status = status;
        Message = message;
        Item = item;
        TotalDistance = totalDistance;
    }

}
