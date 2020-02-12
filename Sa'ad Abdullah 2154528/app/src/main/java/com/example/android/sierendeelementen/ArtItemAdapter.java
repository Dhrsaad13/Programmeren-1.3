package com.example.android.sierendeelementen;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtItemAdapter extends RecyclerView.Adapter<ArtItemAdapter.ViewHolder> {
//      Tag voor logging
    private static final String TAG = ArtItem.class.getSimpleName();
//    Iniatilieren van de List item en de OnlclickListener
    private List<ArtItem> mArtItems;
    private ArtItemClickListener mArtItemClickListener;
// Constructor voor de adapter
    public ArtItemAdapter(List<ArtItem> artItems, ArtItemClickListener mArtItemClickListener) {
        this.mArtItems = artItems;
        this.mArtItemClickListener = mArtItemClickListener;
    }
// Methode om de lijsten aan elkaar te koppelen
    public void setmArtItems(List<ArtItem> artItems) {
        mArtItems = artItems;
        notifyDataSetChanged();
    }

    // onCreateViewHolder creates new views.
    @Override
    public ArtItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-class: " + viewGroup.getClass().getSimpleName());
        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-resourceName: " + viewGroup.getContext().getResources().getResourceName(viewGroup.getId()));
        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-resourceEntryName: " + viewGroup.getContext().getResources().getResourceEntryName(viewGroup.getId()));

        Context context = viewGroup.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);

        // create a new view
        View artListItem = inflator.inflate(R.layout.item_view, viewGroup, false);
        ArtItemAdapter.ViewHolder viewHolder = new ViewHolder(artListItem);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - gets element from your dataset at this position
        // - replaces the contents of the view with that element
        Log.v(TAG, "++++ onBindViewHolder: pos = " + position);
        ArtItem item = mArtItems.get(position);

        holder.name.setText(item.getTitle());
        holder.id.setText(item.getIdentificationNumber());
        holder.geoLocation.setText(item.getGeoLocation());

        Picasso.get().load(item.getImgUrl()).into(holder.image);
        Log.v(TAG, "Piccasso loaded");
    }

    // Return the size of our dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mArtItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // one artItem has 4 views
        // reference to each view

        public ImageView image;
        public TextView name;
        public TextView id;
        public TextView geoLocation;

        public ViewHolder(View listItemView) {
            super(listItemView);
//          Connecting the created view's to the views in the xml layout
            image = (ImageView) listItemView.findViewById(R.id.imageView);
            name = (TextView) listItemView.findViewById(R.id.textView);
            id = (TextView) listItemView.findViewById(R.id.textView6);
            geoLocation = (TextView) listItemView.findViewById(R.id.textView5);
//            Setting onclick listner voor the view's
            image.setOnClickListener(this);
            name.setOnClickListener(this);
            geoLocation.setOnClickListener(this);
            geoLocation.setOnClickListener(this);



            listItemView.setOnClickListener(this);
        }

        // To be implemented: go to item detail page
        @Override
        public void onClick(View view) {
//            Method for on click on a item, sends out the item position
            int itemIndex = getAdapterPosition();
            Log.v(TAG, "Item has been clicked on position " + itemIndex);
            mArtItemClickListener.onArtItemClick(itemIndex);




        }
    }
//    Interface for the clicklistner
    public interface ArtItemClickListener {
        void onArtItemClick(int position);
    }

}
