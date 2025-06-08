package android.bignerdranch.cofee;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

public class ListDaper extends RecyclerView.Adapter<ListDaper.ViewHolder> {
    private final List<Category> dataList;
    private static final int VIEW_TYPE_ODD = 1;
    private static final int VIEW_TYPE_EVEN = 2;

    private final Context context;

    public ListDaper(Context context, List<Category> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 2 == 0) ? VIEW_TYPE_EVEN : VIEW_TYPE_ODD;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_ODD) {
            view = LayoutInflater.from(context).inflate(R.layout.viewholder_item_pic_right, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.viewholder_item_pic_left, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position < dataList.size()) {
            Category category = dataList.get(position);
            holder.titleCategory.setText(category.getName());
            holder.Pricv.setText(String.format(Locale.US, "$%.2f", category.getPrice()));
            holder.rate.setRating((float) dataList.get(position).getRating());
            if(holder.Picut1!=null)
            {
                Glide.with(context)
                        .load(category.getImageUrl())
                        .into(holder.Picut1);
            }
            if(holder.Picut2!=null)
            {
                Glide.with(context)
                        .load(category.getImageUrl())
                        .into(holder.Picut2);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context != null) {

                        Intent intent = new Intent(context, DetailItemActivity.class);
                        intent.putExtra("title", category.getTitle());
                        intent.putExtra("price", category.getPrice());
                        intent.putExtra("description", category.getDescription());
                        intent.putExtra("rating", category.getRating());
                        intent.putExtra("imageUrl", category.getImageUrl());
                        Log.e("ItemsAdapter", "Data is send");
                        context.startActivity(intent);
                    } else {
                        Log.e("ItemsAdapter", "Context is null!");
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleCategory, Pricv;
        RatingBar rate;
        ImageView Picut1 , Picut2;
        TextView name, price ,title , Describtion , rating;
        ImageView image , image2 ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleCategory = itemView.findViewById(R.id.TitleTxtTxt2);
            Pricv = itemView.findViewById(R.id.PriceTextText);
            Picut1 = itemView.findViewById(R.id.ListImageRight);
            Picut2 = itemView.findViewById(R.id.LedftImage);
            rate = itemView.findViewById(R.id.ratingBar);
            image = itemView.findViewById(R.id.pic);
            title = itemView.findViewById(R.id.TitleTxtTxt);
            name = itemView.findViewById(R.id.titletext);
            price = itemView.findViewById(R.id.pricetext);
            price = itemView.findViewById(R.id.pricetext);
            Describtion = itemView.findViewById(R.id.descrption);
            rating = itemView.findViewById(R.id.ratingtxt);
            image2 = itemView.findViewById(R.id.picMain1);
        }
    }
}
