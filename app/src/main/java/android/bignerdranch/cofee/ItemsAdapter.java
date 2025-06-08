package android.bignerdranch.cofee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private List<ItemsModel> itemsList;
    Context context ;

    public ItemsAdapter(List<ItemsModel> itemsList) {
        this.itemsList = itemsList;
    }
    public ItemsAdapter(Context context, List<ItemsModel> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price ,title , Describtion , rating;
        ImageView image , image2 ;

        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.pic);// ok
            name = itemView.findViewById(R.id.titletext);// Ok
            price = itemView.findViewById(R.id.pricetext);//ok
            title = itemView.findViewById(R.id.TitleTxtTxt);
            price = itemView.findViewById(R.id.pricetext);//ok
            Describtion = itemView.findViewById(R.id.descrption);//ok
            rating = itemView.findViewById(R.id.ratingtxt);//ok
            image2 = itemView.findViewById(R.id.picMain1);//ok


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemsModel Item = itemsList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(Item.getPicUrl())
                .into(holder.image);

            holder.name.setText(Item.getTitle());
            holder.price.setText("$" + Item.getPrice());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {

                    Intent intent = new Intent(context, DetailItemActivity.class);
                    intent.putExtra("title", Item.getTitle());
                    intent.putExtra("price", Item.getPrice());
                    intent.putExtra("description", Item.getDescription());
                    intent.putExtra("rating", Item.getRating());
                    intent.putExtra("imageUrl", Item.getPicUrl());
                    Log.e("ItemsAdapter", "Data is send");
                    context.startActivity(intent);
                } else {
                    Log.e("ItemsAdapter", "Context is null!");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList == null ? 0 : itemsList.size();
    }
}
