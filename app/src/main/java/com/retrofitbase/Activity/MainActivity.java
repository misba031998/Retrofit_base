package com.retrofitbase.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.retrofitbase.Adapter.UserAdapter;
import com.retrofitbase.Model.user.UserResponseItem;
import com.retrofitbase.Network.ApiClient;
import com.retrofitbase.Network.ApiConstants;
import com.retrofitbase.Network.ApiInterface;
import com.retrofitbase.Network.ApiResponse;
import com.retrofitbase.databinding.ActivityMainBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements ApiResponse {
    private ActivityMainBinding binding;
    private ApiInterface apiInterface;
    private ArrayList<UserResponseItem> userItem = new ArrayList<>();
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setAdapter();
        CallApi();
    }

    private void setAdapter() {
        adapter = new UserAdapter(this, userItem);
        binding.rvUser.setAdapter(adapter);
    }

    private void CallApi() {
        apiInterface = ApiClient.getApiInterFace(this);
        Call<ResponseBody> users = apiInterface.userData();
        ApiClient.callApi(users, this, ApiConstants.apiRequest.USERS);
    }

    @Override
    public void OnResponse(String response, int apiRequest) {
        if (apiRequest == ApiConstants.apiRequest.USERS) {
            //UserResponse ur= ApiClient.getPayload(UserResponse.class,response);
            Type collectionType = new TypeToken<List<UserResponseItem>>() {
            }.getType();
            List<UserResponseItem> ur = new Gson()
                    .fromJson(response, collectionType);
            userItem.addAll(ur);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnError(String errorResponse, int apiRequest) {
        Log.e("RESPONSE", errorResponse.toString());
        Toast.makeText(this, errorResponse.toString(), Toast.LENGTH_SHORT).show();
    }
}