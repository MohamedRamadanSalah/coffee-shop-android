package android.bignerdranch.cofee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<ItemsModel> listItemSelected;
    private Context context;
    private ChangeNumberItemsListener changeNumberItemListener;
    private ManagementCart managementCart;


// ................................... The Constructor of Cart Adapter ..............................................................
    public CartAdapter(ArrayList<ItemsModel> listItemSelected, Context context, ChangeNumberItemsListener changeNumberItemListener)
    {
        this.listItemSelected = listItemSelected;
        this.context = context;
        this.changeNumberItemListener = changeNumberItemListener; // Important Questions??????
        this.managementCart = new ManagementCart(context);
    }
// ..................................................................................................................................



    // ..................................... The ViewHolder Class ...................................................................
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titlecart, feeEachitem, numberitem, totalEachitem, minusEachitem, plusEachitem;
        ImageView picCart, remveitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titlecart = itemView.findViewById(R.id.titlecarttext);
            feeEachitem = itemView.findViewById(R.id.feeEachitem);
            numberitem = itemView.findViewById(R.id.numberitem);
            totalEachitem = itemView.findViewById(R.id.totalEachitem);
            minusEachitem = itemView.findViewById(R.id.minusEachitem);
            plusEachitem = itemView.findViewById(R.id.plusEachitem);
            picCart = itemView.findViewById(R.id.picCart);
            remveitem = itemView.findViewById(R.id.remveitem);
        }
    }
// ...................................................................................................................................



    // .................................. On Create View Function ...................................................................
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(view);
    }
// .................................................................................................................................




    // ..................................... The On Bind View Function ...........................................................
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Hold Data item
        ItemsModel item = listItemSelected.get(position);
        holder.titlecart.setText(item.getTitle());
        holder.feeEachitem.setText("$"+String.valueOf(item.getPrice()));
        holder.numberitem.setText(String.valueOf(item.getNumberInCart()));
        holder.totalEachitem.setText("$"+String.valueOf(Math.round(item.getNumberInCart() * item.getPrice())));

       // Load The image
        Glide.with(holder.itemView.getContext())
                .load(item.getPicUrl())
                .into(holder.picCart);


        // Hold with Minus Function in Management Cart class
        holder.minusEachitem.setOnClickListener(v -> {
            managementCart.minusItem(listItemSelected, position, () -> {
                // Update The Item In RecycleView
                 notifyItemChanged(position);
                changeNumberItemListener.onChanged();
            });
        });

        // Hold with Plus Function in Management Cart class
        holder.plusEachitem.setOnClickListener(v -> {
            managementCart.plusItem(listItemSelected, position, () -> {
                // Update The Item In RecycleView
                notifyItemChanged(position);
                changeNumberItemListener.onChanged();
            });
        });

        // Hold with Remove Function in Management Cart class
        holder.remveitem.setOnClickListener(v -> {
            managementCart.removeItem(listItemSelected, position, () -> {
                // Update The Item In RecycleView
                notifyItemRemoved(position);
                /* After removing the item update the positions of all items below it
                   to ensure RecyclerView reflects the correct order and avoids UI glitches */
                notifyItemRangeChanged(position, listItemSelected.size());
                changeNumberItemListener.onChanged();
            });
        });
    }
// ..................................................................................................................................


    @Override
    public int getItemCount() {
        return listItemSelected == null ? 0 : listItemSelected.size();
    }
}
