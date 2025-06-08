package android.bignerdranch.cofee;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavouriteActivity extends AppCompatActivity {
    private ManagementFavourite managementFav;
    private RecyclerView recyclerViewCoffee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        // ..................................... To Full Screen .............................................
        hideSystemUI();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                hideSystemUI();
            }
        });
        // ....................................................................................................

        managementFav = new ManagementFavourite(this);
        FavouriteAdapter FavAdapter = new FavouriteAdapter(managementFav.getListFav(), this, new ChangeNumberItemsListener() {
            @Override
            public void onChanged() {
                Toast.makeText(FavouriteActivity.this,"Hello",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewCoffee = findViewById(R.id.Favview);
        recyclerViewCoffee.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewCoffee.setAdapter(FavAdapter);

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