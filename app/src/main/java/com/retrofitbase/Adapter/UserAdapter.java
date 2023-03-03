package com.retrofitbase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrofitbase.Model.user.UserResponseItem;
import com.retrofitbase.R;
import com.retrofitbase.databinding.SingleDesignItemBinding;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private ArrayList<UserResponseItem> userItem;

    public UserAdapter(Context context, ArrayList<UserResponseItem> userItem) {
        this.context = context;
        this.userItem = userItem;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleDesignItemBinding binding = SingleDesignItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.binding.nameonline.setText(userItem.get(position).getName());
        holder.binding.eamil.setText(userItem.get(position).getEmail());
       if (userItem.get(position).getGender().equals("male")){
           holder.binding.profileimage.setImageResource(R.drawable.male);
       }else {
           holder.binding.profileimage.setImageResource(R.drawable.female);
       }
       if (userItem.get(position).getStatus().equals("active")){
           holder.binding.card.setBackgroundResource(R.color.font);
       }else {
           holder.binding.card.setBackgroundResource(R.color.redBack);
       }
    }

    @Override
    public int getItemCount() {
        return userItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SingleDesignItemBinding binding;

        public ViewHolder(@NonNull SingleDesignItemBinding mkmkmk) {
            super(mkmkmk.getRoot());
            binding = mkmkmk;
        }
    }
}
