package android.bignerdranch.cofee;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private ArrayList<ItemsModel> listItemSelected;
    private Context context;
    private ChangeNumberItemsListener changeNumberItemListener;
    private ManagementFavourite managementFav;
    // ................................... The Constructor of Fav Adapter ..............................................................
    public FavouriteAdapter(ArrayList<ItemsModel> listItemSelected, Context context, ChangeNumberItemsListener changeNumberItemListener)
    {
        this.listItemSelected = listItemSelected;
        this.context = context;
        this.changeNumberItemListener = changeNumberItemListener;
        this.managementFav = new ManagementFavourite(context);
    }
// ..................................................................................................................................

    // ..................................... The ViewHolder Class ...................................................................
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleFav , totalEachitem;
        ImageView picFav, remveitemFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleFav = itemView.findViewById(R.id.titleFavttext);
            totalEachitem = itemView.findViewById(R.id.totalEachitemFav);
            picFav = itemView.findViewById(R.id.picFav);
            remveitemFav = itemView.findViewById(R.id.remveitemFav);
        }
    }
    // ...................................................................................................................................
    // .................................. On Create View Function ...................................................................
    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_viewholder, parent, false);
        return new FavouriteAdapter.ViewHolder(view);
    }
    // .................................................................................................................................
    // ..................................... The On Bind View Function ...........................................................
    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {
        // Hold Data item
        ItemsModel item = listItemSelected.get(position);
        holder.titleFav.setText(item.getTitle());
        holder.totalEachitem.setText("$"+String.valueOf(item.getPrice()));
        // Load The image
        Glide.with(holder.itemView.getContext())
                .load(item.getPicUrl())
                .into(holder.picFav);

        holder.remveitemFav.setOnClickListener(v -> {

            if (listItemSelected != null && position >= 0 && position < listItemSelected.size()) {
                managementFav.removeItem(listItemSelected, position, () -> {
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, listItemSelected.size());
                });
            }
        });





    }
    // ..................................................................................................................................
    @Override
    public int getItemCount() {
        return listItemSelected == null ? 0 : listItemSelected.size();
    }
}
