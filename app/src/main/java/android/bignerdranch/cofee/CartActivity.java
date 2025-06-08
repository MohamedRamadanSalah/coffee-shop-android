package android.bignerdranch.cofee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;

import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    private ManagementCart managementCart;
    private double itemTotal, Tax, total, delivary = 15.0;
    private Button Checkout;
    private RecyclerView recyclerViewCoffee;
    private TextView TotalFeeText, deliverText, taxText, totalTxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // ..................................... To Full Screen .............................................
        hideSystemUI();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                hideSystemUI();
            }
        });

        // ....................................................................................................
        // Link The widgets
        TotalFeeText = findViewById(R.id.TotalFeetText);
        deliverText = findViewById(R.id.delivertext);
        taxText = findViewById(R.id.taxText);
        totalTxt = findViewById(R.id.Totaltxt);
        Checkout = findViewById(R.id.button3);

        // create Management Cart
        managementCart = new ManagementCart(this);
        calculateCart();
        initCartList();


        Checkout.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MyOrderActivity.class);
            intent.putExtra("ITEM_TOTAL", itemTotal);
            intent.putExtra("TAX", Tax);
            intent.putExtra("DELIVERY", delivary);
            intent.putExtra("TOTAL", total);
            startActivity(intent);
        });
    }

    // .............................................. The InitCartList Function .....................................................

    // Initialize and display cart items in the RecyclerView
    private void initCartList() {
        CartAdapter cartAdapter = new CartAdapter(managementCart.getListCart(), this, this::calculateCart);

        // Create recycle View
        recyclerViewCoffee = findViewById(R.id.cartview2);
        recyclerViewCoffee.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewCoffee.setAdapter(cartAdapter);
    }

    // ........................................ The Calculate Cart Function .........................................................

    private void calculateCart() {
        double percentTax = 0.02;
        Tax = Math.round(managementCart.getTotalFee() * percentTax * 100) / 100.0d;
        total = Math.round(managementCart.getTotalFee() + Tax + delivary);
        itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100.0;

        TotalFeeText.setText(String.format(Locale.US, "$%.2f", itemTotal));
        taxText.setText(String.format(Locale.US, "$%.2f", Tax));
        deliverText.setText(String.format(Locale.US, "$%.2f", delivary));
        totalTxt.setText(String.format(Locale.US, "$%.2f", total));
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
