package com.example.mydota.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydota.Data.Model.Hero;
import com.example.mydota.R;

import java.util.List;

public class HeroListAdapter extends RecyclerView.Adapter<HeroListAdapter.ViewHolder>{
    private OnListItemClickedListener onListItemClickedListener;
    private List<Hero> heroes;


    public HeroListAdapter(OnListItemClickedListener listener) {
        onListItemClickedListener = listener;
    }

    @NonNull
    @Override
    public HeroListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hero_item, parent, false);
        return new ViewHolder(view, onListItemClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroListAdapter.ViewHolder holder, int position) {
        if (heroes != null) {
            Hero heroPosition = heroes.get(position);
            String name =heroPosition.getName().replace("_"," ");
            holder.heroName.setText(name);
            Glide.with(holder.itemView.getContext()).load(heroPosition.getImageURL()).into(holder.heroImage);
        }
    }

    public void setHeroes(List<Hero> heroes) {
            this.heroes = heroes;
            notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (heroes != null) {
            return heroes.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView heroName;
        ImageView heroImage;
        RelativeLayout parentLayout;
        OnListItemClickedListener onListItemClickedListener;

        private ViewHolder(@NonNull View itemView, OnListItemClickedListener listener) {
            super(itemView);
            heroName = itemView.findViewById(R.id.heroName);
            heroImage = itemView.findViewById(R.id.heroImage);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            onListItemClickedListener = listener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onListItemClickedListener.onListItemClicked(heroes.get(getAdapterPosition()).getId(),heroes.get(getAdapterPosition()).getName());
        }

    }

    public interface OnListItemClickedListener {
        void onListItemClicked(int heroId,String heroName);
    }
}
