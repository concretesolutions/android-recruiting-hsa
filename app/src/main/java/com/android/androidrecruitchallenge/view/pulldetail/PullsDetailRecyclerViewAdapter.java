package com.android.androidrecruitchallenge.view.pulldetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.androidrecruitchallenge.R;
import com.android.androidrecruitchallenge.entity.pullsdetail.PullRequest;
import com.android.androidrecruitchallenge.view.pulldetail.PullsDetailFragment.OnListFragmentInteractionListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class PullsDetailRecyclerViewAdapter extends RecyclerView.Adapter<PullsDetailRecyclerViewAdapter.ViewHolder> {

    private final List<PullRequest> pullList;
    private final OnListFragmentInteractionListener mListener;
    Context context;

    String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    String pattern2 = "dd-MM-yyyy";
    Date date;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);

    public PullsDetailRecyclerViewAdapter(List<PullRequest> pullList, OnListFragmentInteractionListener listener, Context context) {
        this.pullList = pullList;
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pulldetail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = pullList.get(position);
        holder.pullRequestTitle.setText(pullList.get(position).getTitle().trim());
        holder.pullRequestDesc.setText(pullList.get(position).getBody().trim());
        holder.pullRequestUserName.setText(pullList.get(position).getUser().getLogin().trim());
        Glide.with(this.context)
                .load(pullList.get(position).getUser().getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pullRequestAvatar);
        try{
            date = simpleDateFormat.parse(pullList.get(position).getCreatedAt());
            holder.pullRequestCreateDate.setText(simpleDateFormat2.format(date));
        }catch(Exception ex){
            holder.pullRequestCreateDate.setText("");
        }

        holder.pullRequestStatus.setText(pullList.get(position).getState());
        switch (pullList.get(position).getState()){
            case "open":
                holder.pullRequestStatus.setTextColor(ContextCompat.getColor(this.context,R.color.colorPullRequestOpen));
                holder.pullRequestStatusIcon.setColorFilter(ContextCompat.getColor(this.context,R.color.colorPullRequestOpen));
                break;
            case "closed":
                holder.pullRequestStatus.setTextColor(ContextCompat.getColor(this.context,R.color.colorPullRequestClosed));
                holder.pullRequestStatusIcon.setColorFilter(ContextCompat.getColor(this.context,R.color.colorPullRequestClosed));
                break;
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
        return pullList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView pullRequestTitle;
        public final TextView pullRequestDesc;
        public final TextView pullRequestUserName;
        public final TextView pullRequestCreateDate;
        public final TextView pullRequestStatus;
        public final ImageView pullRequestAvatar;
        public final ImageView pullRequestStatusIcon;
        public PullRequest mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            pullRequestTitle = (TextView) view.findViewById(R.id.pullRequestTitle);
            pullRequestDesc = (TextView) view.findViewById(R.id.pullRequestDescription);
            pullRequestUserName = (TextView) view.findViewById(R.id.pullRequestOwnerName);
            pullRequestCreateDate = (TextView) view.findViewById(R.id.pullRequestCreateDate);
            pullRequestStatus = (TextView) view.findViewById(R.id.pullRequestStatus);
            pullRequestAvatar = (ImageView) view.findViewById(R.id.pullRequestAvatarOwner);
            pullRequestStatusIcon = (ImageView) view.findViewById(R.id.pullRequestIcon);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + pullRequestDesc.getText() + "'";
        }
    }
}
