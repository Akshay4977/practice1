package com.dev.demo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dev.demo.R;
import com.dev.demo.models.Characters;


import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private List<Characters> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    public CharacterAdapter(Context context, List<Characters> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Characters characters = mData.get(position);
        holder.name.setText(characters.getName());
        holder.birthday.setText(characters.getBirthday());
        Glide.with(context)
                .load(characters.getImg())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    Characters getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    public void addItems(List<Characters> postItems) {
        mData.addAll(postItems);
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position, Characters character);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, birthday;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            birthday = itemView.findViewById(R.id.birthday);
            image = itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition(), mData.get(getAdapterPosition()));
        }
    }
}

