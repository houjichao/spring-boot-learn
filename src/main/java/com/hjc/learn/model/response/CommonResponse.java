package com.hjc.learn.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.hjc.learn.model.User;
import com.hjc.learn.model.view.View;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author houjichao
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse {
    @JsonProperty(value = "is_success")
    @JsonView(View.BasicView.class)
    private boolean isSuccess;

    @JsonView(View.BasicView.class)
    @ApiModelProperty(hidden = true)
    private String message;

    @JsonView(View.User.class)
    private List<User> users;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
