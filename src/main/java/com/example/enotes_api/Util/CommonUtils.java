package com.example.enotes_api.Util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.enotes_api.Handller.GenericResponse;

public class CommonUtils {
    
    public static ResponseEntity<?> createBuilderResponse (Object data , HttpStatus status){
        GenericResponse response = GenericResponse.builder().responseStatus(status).status("success").message("success").data(data).build();
        return response.create();
    }

    public static ResponseEntity<?> createBuilderResponseMeessage (String message ,HttpStatus status){
        GenericResponse response = GenericResponse.builder().responseStatus(status).status("success").message(message).build();
        return response.create();
    }

    public static ResponseEntity<?> createErrorResponse (Object data , HttpStatus status){
        GenericResponse response = GenericResponse.builder().responseStatus(status).status("failed").message("failed").build();
        return response.create();
    }
    public static ResponseEntity<?> createErrorBuilderResponse (String message , HttpStatus status){
        GenericResponse response = GenericResponse.builder().responseStatus(status).status("failed").message(message).build();
        return response.create();
    }


}
