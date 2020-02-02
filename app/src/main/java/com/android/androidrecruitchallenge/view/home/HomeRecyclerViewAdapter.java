package com.android.androidrecruitchallenge.view.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.androidrecruitchallenge.R;
import com.android.androidrecruitchallenge.entity.home.Item;
import com.android.androidrecruitchallenge.view.home.HomeFragment.OnListFragmentInteractionListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private final List<Item> repositoryList;
    private final OnListFragmentInteractionListener mListener;
    Context context;

    String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    String pattern2 = "dd-MM-yyyy";
    Date date;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);

    public HomeRecyclerViewAdapter(List<Item> items, OnListFragmentInteractionListener listener, Context context) {
        repositoryList = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = repositoryList.get(position);
        holder.repositoryName.setText(repositoryList.get(position).getName());
        holder.repositoryDesc.setText(repositoryList.get(position).getDescription().trim());
        holder.repositoryUserName.setText(repositoryList.get(position).getOwner().getLogin());
        holder.repositoryStargazersCount.setText(String.valueOf(repositoryList.get(position).getStargazersCount()));
        holder.repositoryForksCount.setText(String.valueOf(repositoryList.get(position).getForksCount()));
        Glide.with(this.context)
                .load(repositoryList.get(position).getOwner().getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.repositoryOwnerAvatar);

        try{
            date = simpleDateFormat.parse(repositoryList.get(position).getCreatedAt());
            holder.repositoryCreateDate.setText(simpleDateFormat2.format(date));
        }catch(Exception ex){
            holder.repositoryCreateDate.setText("");
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView repositoryName;
        public final TextView repositoryDesc;
        public final TextView repositoryUserName;
        public final TextView repositoryCreateDate;
        public final TextView repositoryStargazersCount;
        public final TextView repositoryForksCount;
        public final ImageView repositoryOwnerAvatar;
        public Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            repositoryName = (TextView) view.findViewById(R.id.repositoryName);
            repositoryDesc = (TextView) view.findViewById(R.id.repositoryDescription);
            repositoryUserName = (TextView) view.findViewById(R.id.repositoryOwnerName);
            repositoryCreateDate = (TextView) view.findViewById(R.id.repositoryCreateDate);
            repositoryStargazersCount = (TextView) view.findViewById(R.id.repositoryStargazersCount);
            repositoryForksCount = (TextView) view.findViewById(R.id.repositoryForksCount);
            repositoryOwnerAvatar = (ImageView) view.findViewById(R.id.repositoryAvatarOwner);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + repositoryDesc.getText() + "'";
        }
    }
}
