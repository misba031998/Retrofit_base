package com.retrofitbase.Model.user;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserResponse{

	@SerializedName("UserResponse")
	private List<UserResponseItem> userResponse;

	public List<UserResponseItem> getUserResponse(){
		return userResponse;
	}
}