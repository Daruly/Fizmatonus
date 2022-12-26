package company.hakatonus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private ArrayList<Product> products;
    private Context context;
    private LayoutInflater inflater;

    public ShopAdapter(Context mContext, ArrayList<Product> mArray) {
        super();
        this.products = mArray;
        this.context = mContext;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("Product", product);
                context.startActivity(intent);
            }
        });

        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        holder.productImage.setImageResource(product.getImg());
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final CardView productCard;

        final TextView productName, productPrice;
        final ImageView productImage;

        ViewHolder(View view){
            super(view);
            this.productCard = view.findViewById(R.id.card);
            this.productName = view.findViewById(R.id.card_name);
            this.productPrice = view.findViewById(R.id.card_price);
            this.productImage = view.findViewById(R.id.card_image);
        }
    }
}
