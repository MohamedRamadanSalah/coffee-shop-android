package android.bignerdranch.cofee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class DetailItemActivity extends AppCompatActivity {
    private ItemsModel item;
    private ManagementCart MC;
    private  ManagementFavourite Mf;
    private TextView title;
    private TextView Description;
    private TextView price;
    private TextView rate;
    ConstraintLayout addCart;
    private TextView numitem;
    private TextView bacBtn;
    private TextView PlusCart;
    ImageView imageView  , FavImage ;
    private TextView MinusCart;
    private TextView Small;
    private TextView Medium;
    private TextView Large;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        hideSystemUI();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                hideSystemUI();
            }
        });
        Intialize();
        Apply();
        initSizeList();




    }
    public void Intialize()
    {

        imageView = findViewById(R.id.picMain1);
        title = findViewById(R.id.TitleTxtTxt);
        Description = findViewById(R.id.descrption);
        rate = findViewById(R.id.ratingtxt);
        price = findViewById(R.id.pricetext);
        addCart = findViewById(R.id.addtoCartBtn);
        numitem = findViewById(R.id.numberitems);
        MC = new ManagementCart(this);
        Mf = new ManagementFavourite(this);
        bacBtn = findViewById(R.id.back);
        PlusCart = findViewById(R.id.pluscart);
        MinusCart = findViewById(R.id.minusBtn);
        Small = findViewById(R.id.smallbtn);
        Medium = findViewById(R.id.mediumbtn);
        Large = findViewById(R.id.largerbtn);
        FavImage = findViewById(R.id.favorite);
    }

    @SuppressLint("SetTextI18n")
    public void Apply() {
        Intent intent = getIntent();
        if (intent != null) {
            String titleText = intent.getStringExtra("title");
            double priceValue = intent.getDoubleExtra("price", 0.0);
            String descriptionText = intent.getStringExtra("description");
            double ratingValue = intent.getDoubleExtra("rating", 0.0);
            String imageUrl = intent.getStringExtra("imageUrl");
            if (title != null) title.setText(titleText);
            if (price != null) price.setText(String.format(Locale.US, "$%.2f", priceValue));
            if (Description != null) Description.setText(descriptionText);
            if (rate != null) rate.setText(String.format(Locale.US, "Rating: %.1f", ratingValue));
            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView);

            if (item == null) {
                item = new ItemsModel();
                item.setTitle(titleText);
                item.setPrice(priceValue);
                item.setDescription(descriptionText);
                item.setRating(ratingValue);
                item.setPicUrl(imageUrl);
            }


            if (addCart != null) {
                addCart.setOnClickListener(v -> {
                    if (numitem != null) {
                        numitem.setText(String.format(Locale.US, "%d", item.numberInCart));
                    }
                    MC.insertItems(item);
                    Toast.makeText(v.getContext(), "Item added to cart successfully!", Toast.LENGTH_SHORT).show();
                });
            }
            // Favourite Pic
            final boolean[] isFavorite = {false};
            FavImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isFavorite[0] = !isFavorite[0];
                    if (isFavorite[0]) {
                        FavImage.setImageResource(R.drawable.fav2);
                        Mf.insertFavItems(item);
                        Toast.makeText(DetailItemActivity.this,"Add Successful",Toast.LENGTH_SHORT).show();
                    } else {
                        FavImage.setImageResource(R.drawable.favorite_white);
                    }
                }
            });



            if (bacBtn != null) {
                bacBtn.setOnClickListener(v -> {
                    Intent backIntent = new Intent(DetailItemActivity.this, DashboardActivity2.class);
                    startActivity(backIntent);
                });
            }


            if (PlusCart != null) {
                PlusCart.setOnClickListener(v -> {
                    item.numberInCart++;
                    if (numitem != null) {
                        numitem.setText(String.format(Locale.US, "%d", item.numberInCart));
                    }
                });
            }


            if (MinusCart != null) {
                MinusCart.setOnClickListener(v -> {
                    if (item.numberInCart > 0) {
                        item.numberInCart--;
                        if (numitem != null) {
                            numitem.setText(String.format(Locale.US, "%d", item.numberInCart));
                        }
                    }
                });
            }
        }
    }
    public void initSizeList(){
        Small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Small.setBackgroundResource(R.drawable.stroke_brown);
                Medium.setBackgroundResource(0);
                Large.setBackgroundResource(0);
            }
        });
        Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Small.setBackgroundResource(0);
                Medium.setBackgroundResource(R.drawable.stroke_brown);
                Large.setBackgroundResource(0);
            }
        });
        Large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Small.setBackgroundResource(0);
                Medium.setBackgroundResource(0);
                Large.setBackgroundResource(R.drawable.stroke_brown);
            }
        });

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