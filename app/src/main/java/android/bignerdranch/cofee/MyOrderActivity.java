package android.bignerdranch.cofee;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MyOrderActivity extends AppCompatActivity {
    double itemTotal,delivery,tax,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);


        SharedPreferences sharedPreferences = getSharedPreferences("OrderPrefs", MODE_PRIVATE);

        if (savedInstanceState != null) {
            itemTotal = savedInstanceState.getDouble("ITEM_TOTAL");
            tax = savedInstanceState.getDouble("TAX");
            delivery = savedInstanceState.getDouble("DELIVERY");
            total = savedInstanceState.getDouble("TOTAL");
        } else {

            itemTotal = getIntent().getDoubleExtra("ITEM_TOTAL", sharedPreferences.getFloat("ITEM_TOTAL", 0.0f));
            tax = getIntent().getDoubleExtra("TAX", sharedPreferences.getFloat("TAX", 0.0f));
            delivery = getIntent().getDoubleExtra("DELIVERY", sharedPreferences.getFloat("DELIVERY", 0.0f));
            total = getIntent().getDoubleExtra("TOTAL", sharedPreferences.getFloat("TOTAL", 0.0f));
        }


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("ITEM_TOTAL", (float) itemTotal);
        editor.putFloat("TAX", (float) tax);
        editor.putFloat("DELIVERY", (float) delivery);
        editor.putFloat("TOTAL", (float) total);
        editor.apply();


        hideSystemUI();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                hideSystemUI();
            }
        });

        TextView totalItemsText = findViewById(R.id.tv_subtotal_amount);
        TextView taxText = findViewById(R.id.tv_tax_amount);
        TextView deliveryText = findViewById(R.id.tv_delivery_amount);
        TextView totalText = findViewById(R.id.tv_total_amount);

        totalItemsText.setText(String.format(Locale.US, "$%.2f", itemTotal));
        taxText.setText(String.format(Locale.US, "$%.2f", tax));
        deliveryText.setText(String.format(Locale.US, "$%.2f", delivery));
        totalText.setText(String.format(Locale.US, "$%.2f", total));
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
