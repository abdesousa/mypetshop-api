package com.mypetshop.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "message"
})
public class ApiErrorDTO extends MainDTO implements Serializable {

    private final static long serialVersionUID = 5743458502302735571L;
    
    @JsonProperty("message")
    private String message;
    
    public ApiErrorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiErrorDTO( String message) {
		super();
		this.message = message;
	}

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }
}
