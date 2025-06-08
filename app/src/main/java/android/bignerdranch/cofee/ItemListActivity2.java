package android.bignerdranch.cofee;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity2 extends AppCompatActivity {
    private List<Category> dataList;
    private RecyclerView recyclerViewCoffee;
    private ListDaper itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list2);

        hideSystemUI();

        dataList = new ArrayList<Category>();
        dataList.add(new Category(
                "Espresso",
                getString(R.string.DeExpresso),
                getString(R.string.Expresso),
                30,
                4.5,
                0,
                "No extra ingredients"
        ));

        dataList.add(new Category(
                "Cappuccino",
                getString(R.string.DescCapp),
                getString(R.string.Cappuccino),
                40,
                4.7,
                0,
                "Comes with cinnamon"
        ));

        dataList.add(new Category(
                "Americano",
                getString(R.string.DescAmerica),
                getString(R.string.Americano),
                35,
                4.3,
                0,
                "No extra ingredients"
        ));

        dataList.add(new Category(
                "Affogato",
                getString(R.string.DecAffog),
                getString(R.string.Affogato),
                50,
                5,
                0,
                "Vanilla ice cream included"
        ));

        dataList.add(new Category(
                "Black tea",
                 getString(R.string.DecrpBla),
                 getString(R.string.PicBla),
                25,
                4,
                0,
                "Vanilla ice cream included"
        ));
        dataList.add(new Category(
                "Matcha Latte",
                getString(R.string.Mact),
                getString(R.string.Picma),
                14,
                4.8,
                0,
                "Vanilla ice cream included"
        ));









        recyclerViewCoffee = findViewById(R.id.cartview);
        recyclerViewCoffee.setLayoutManager(new GridLayoutManager(this, 1));
        itemsAdapter = new ListDaper(this, dataList);
        recyclerViewCoffee.setAdapter(itemsAdapter);
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
    }
}
