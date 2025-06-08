package android.bignerdranch.cofee;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<String> categories = null;

    private final Context context;
    private int selectedPosition = -1;

    public CategoryAdapter(Context context, List<String> categories) {
        this.context = context;
        this.categories = categories;
    }

    public CategoryAdapter(ItemListActivity2 context, List<ItemsModel> itemsList, Context context1) {
        this.context = context1;
    }


    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleCategory = itemView.findViewById(R.id.titleCategory);
        }
    }
    @NonNull
    @Override
    // .................... Inflate Process ....................................................
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(view);
    }
    // ............................................................................................
    //.................... Binding Data ................................................
    // Binds the data to the ViewHolder for a specific position in the RecyclerView................................
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String category = categories.get(position);
        holder.titleCategory.setText(category);


        boolean isSelected = (selectedPosition == position);

        if (isSelected) {
            holder.titleCategory.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.titleCategory.setBackgroundResource(R.drawable.dark_brown);
        } else {
            holder.titleCategory.setTextColor(ContextCompat.getColor(context, R.color.darkBrown));
            holder.titleCategory.setBackgroundResource(R.drawable.category_background);

        }

        holder.titleCategory.setOnClickListener(v -> {
            int previousSelectedPosition = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(previousSelectedPosition);
            notifyItemChanged(selectedPosition);


            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent intent = new Intent(context, ItemListActivity2.class);
                context.startActivity(intent);
            }, 20);
        });
    }



}



//

    // .............................................................................................................


