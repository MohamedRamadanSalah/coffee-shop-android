package android.bignerdranch.cofee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardActivity2 extends AppCompatActivity {
    private RecyclerView recyclerViewCoffee, recyclerViewCat;
    private ImageView banner , fav , Order , profile , explorer;
    TextView FavTxt , ord , prof ,exp;
    private ProgressBar progressBarBanner;
    private List<ItemsModel> itemsList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private ItemsAdapter itemsAdapter;
    private LinearLayout CarBtn;
    private static final String BANNER_URL = "https://res.cloudinary.com/dkikc5ywq/image/upload/v1740058030/banner_dku4m3.png";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        CarBtn = findViewById(R.id.CCart);
        hideSystemUI();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                hideSystemUI();
            }
        });

        initViews();
        loadBannerImage();
        initCategory();
        setupRecyclerView();
        initBottomMenu();
        FAvourite();
        Order();
        Profile();
        Explorer();
    }

    private void initBottomMenu() {
        CarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
    private void FAvourite() {
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, FavouriteActivity.class);
                startActivity(intent);
            }
        });
        FavTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, FavouriteActivity.class);
                startActivity(intent);
            }
        });

    }
    private void Order() {
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, MyOrderActivity.class);
                startActivity(intent);
            }
        });
        ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, MyOrderActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Profile() {
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
    private void Explorer() {
        explorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, ItemListActivity2.class);
                startActivity(intent);
            }
        });
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, ItemListActivity2.class);
                startActivity(intent);
            }
        });

    }

    private void initViews() {
        banner = findViewById(R.id.banner);
        fav = findViewById(R.id.FAVFAV);
        FavTxt = findViewById(R.id.textView9);
        progressBarBanner = findViewById(R.id.progressBar);
        recyclerViewCoffee = findViewById(R.id.recyclerViewCatPopular);
        recyclerViewCat = findViewById(R.id.recyclerViewCatCategory);
        Order = findViewById(R.id.Order);
        ord = findViewById(R.id.Ord);
        profile = findViewById(R.id.Prfile);
        prof = findViewById(R.id.prof);
        explorer = findViewById(R.id.explorer);
        exp = findViewById(R.id.exp);
    }

    private void loadBannerImage() {
        progressBarBanner.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(BANNER_URL)
                .into(banner);
        progressBarBanner.setVisibility(View.GONE);
    }

    private void initCategory() {
        List<String> categories = Arrays.asList("Espresso", "Cappuccino", "Latte", "Americano", "Hot Chocolate");

        if (categoryAdapter == null) {
            categoryAdapter = new CategoryAdapter(this, categories);
        }

        recyclerViewCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCat.setAdapter(categoryAdapter);
    }

    private void setupRecyclerView() {
        setupData();

        recyclerViewCoffee.setLayoutManager(new GridLayoutManager(this, 2));
        itemsAdapter = new ItemsAdapter(this, itemsList);
        recyclerViewCoffee.setAdapter(itemsAdapter);

        itemsAdapter.notifyDataSetChanged();
    }

    private void setupData() {

        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }


        itemsList.add(new ItemsModel(
                "Espresso",
                "Cappuccino is a traditional Italian coffee drink made with equal parts espresso, steamed milk, and milk foam. It has a strong espresso flavor, balanced by the creamy texture of the steamed milk and the light, airy foam on top. Cappuccinos are often garnished with a sprinkle of cocoa powder or cinnamon.",
                getString(R.string.Expresso),
                30,
                4.5,
                0,
                "No extra ingredients"
        ));

        itemsList.add(new ItemsModel(
                "Cappuccino",
                "Cappuccino is a concentrated form of coffee served in small, strong shots and is the base for many coffee drinks. It is made by forcing a small amount of nearly boiling water through finely-ground coffee beans under high pressure. The result is a rich, robust flavor with a thick, creamy layer on top called crema.",
                getString(R.string.Cappuccino),
                40,
                4.7,
                0,
                "Comes with cinnamon"
        ));

        itemsList.add(new ItemsModel(
                "Americano",
                "Americano is a concentrated form of coffee served in small, strong shots and is the base for many coffee drinks. It is made by forcing a small amount of nearly boiling water through finely-ground coffee beans under high pressure. The result is a rich, robust flavor with a thick, creamy layer on top called crema.",
                getString(R.string.Americano),
                35,
                4.3,
                0,
                "No extra ingredients"
        ));

        itemsList.add(new ItemsModel(
                "Affogato",
                "Affogato is a concentrated form of coffee served in small, strong shots and is the base for many coffee drinks. It is made by forcing a small amount of nearly boiling water through finely-ground coffee beans under high pressure. The result is a rich, robust flavor with a thick, creamy layer on top called crema.",
                getString(R.string.Affogato),
                50,
                4.8,
                0,
                "Vanilla ice cream included"
        ));
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
