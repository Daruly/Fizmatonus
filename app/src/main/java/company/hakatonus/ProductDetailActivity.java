package company.hakatonus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Product product;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Bundle extras = getIntent().getExtras();
        product = (Product) extras.get("Product");
//        Toast.makeText(this, product.getName(), Toast.LENGTH_LONG).show();
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.button:
                Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfsp3Z4sgvmifNQ4CMdRHbDl6NcKgg2gt-ClNjqvPHBZ9wfzw/viewform");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
        }
    }
}