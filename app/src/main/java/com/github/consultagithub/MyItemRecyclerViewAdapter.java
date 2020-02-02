package com.github.consultagithub;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.consultagithub.view.RepositoryListFragment.OnListFragmentInteractionListener;
import com.github.consultagithub.dummy.DummyContent.DummyItem;
import com.github.consultagithub.entity.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Item> itemList;
    private OnListFragmentInteractionListener mListener;
    Context context;

    public MyItemRecyclerViewAdapter(List<Item> items, OnListFragmentInteractionListener listener, Context context) {
        itemList = items;
        mListener = listener;
        this.context = context;
    }

    public MyItemRecyclerViewAdapter(List<Item> items) {
        itemList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = itemList.get(position);
        holder.txtNombre.setText(String.valueOf(itemList.get(position).getOwner().getId()));
        holder.txtUserName.setText(itemList.get(position).getOwner().getLogin());
        String url = itemList.get(position).getOwner().getAvatarUrl();
        Picasso.get().load(url).into(holder.imageView2);
        holder.txtNombreRepo.setText(itemList.get(position).getName());
        holder.txtDescripcion.setText(itemList.get(position).getDescription());
        holder.txtBranch.setText(String.valueOf(itemList.get(position).getForksCount()));
        holder.txtValoracion.setText(String.valueOf(itemList.get(position).getStargazersCount()));
        


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
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtNombre;
        public final TextView txtUserName;
        public final ImageView imageView2;
        public final TextView txtNombreRepo;
        public final TextView txtDescripcion;
        public final TextView txtBranch;
        public final TextView txtValoracion;
        public final RecyclerView recyclerView;
        public Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtNombre = (TextView) view.findViewById(R.id.txtNombrePull);
            txtUserName = (TextView) view.findViewById(R.id.txtUserNamePull);
            imageView2 = (ImageView) view.findViewById(R.id.imageView2);
            txtNombreRepo = (TextView) view.findViewById(R.id.txtNombreRepo);
            txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
            txtBranch = (TextView) view.findViewById(R.id.txtBranch);
            txtValoracion = (TextView) view.findViewById(R.id.txtValoracion);
            recyclerView = (RecyclerView) view.findViewById(R.id.list);

        }
    }
}
