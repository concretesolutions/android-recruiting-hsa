package com.github.consultagithub;

import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.consultagithub.entity.Item;
import com.github.consultagithub.entity.pull.PullRequest;
import com.github.consultagithub.view.PullRepositoryFragment.OnListFragmentInteractionListener;
import com.github.consultagithub.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPullRepositoryRecyclerViewAdapter extends RecyclerView.Adapter<MyPullRepositoryRecyclerViewAdapter.ViewHolder> {

    private final List<PullRequest> pullRequests;
    private final OnListFragmentInteractionListener mListener;

    public MyPullRepositoryRecyclerViewAdapter(List<PullRequest> items, OnListFragmentInteractionListener listener) {
        pullRequests = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pullrepository, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = pullRequests.get(position);
        if(pullRequests.get(position).getUser().getId() != null){
            holder.txtNombreUser.setText(String.valueOf(pullRequests.get(position).getUser().getId()));
        }
        holder.txtUserName.setText(pullRequests.get(position).getUser().getLogin());
        holder.txtDesc.setText(pullRequests.get(position).getBody());
        holder.txtTitulo.setText(pullRequests.get(position).getTitle());

        if(pullRequests.get(position).getUser().getAvatarUrl().isEmpty()){

        }
        else {
            String url = pullRequests.get(position).getUser().getAvatarUrl();
            Picasso.get().load(url).into(holder.imageView);
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
        return pullRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtTitulo;
        public final TextView txtDesc;
        public final TextView txtUserName;
        public final TextView txtNombreUser;
        public final ImageView imageView;
        public final TextView mContentView;
        public PullRequest mItem;
        public final TextView txtAbiertos;
        public final TextView txtCerrados;
        public final TextView txtStAb;
        public final TextView txtStCe;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mContentView = (TextView) view.findViewById(R.id.content);
            txtTitulo = (TextView) view.findViewById(R.id.txtTituloPull);
            txtDesc = (TextView) view.findViewById(R.id.txtDescPull);
            txtUserName = (TextView) view.findViewById(R.id.txtUserNamePull);
            txtNombreUser = (TextView) view.findViewById(R.id.txtNombrePull);
            txtAbiertos = (TextView) view.findViewById(R.id.txtCantAbiertos);
            txtStAb = (TextView) view.findViewById(R.id.txtAbiertos);
            txtCerrados  = (TextView) view.findViewById(R.id.txtCerrados);
            txtStCe = (TextView) view.findViewById(R.id.txtClosed);
            imageView = (ImageView) view.findViewById(R.id.imagePull);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
