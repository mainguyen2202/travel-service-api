package com.nlu.mainguyen.travelserviceapi.model;

import lombok.*;

@Data
public class ResetPasswordInputDTO { // response <> output
    private String token;
    private String password;
}