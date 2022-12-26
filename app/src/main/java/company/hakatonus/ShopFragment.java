package company.hakatonus;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Product> shopProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        mRecyclerView = view.findViewById(R.id.shop_list_view);

        shopProducts = new ArrayList<Product>();


        // Считываем дб продуктов в магазине и заполняем массив считанными данными
        initShopProducts();

        ShopAdapter adapter = new ShopAdapter(getContext(), shopProducts);

        mRecyclerView.setAdapter(adapter);

        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    public Drawable getDrawable(View view, String name) {
        Context context = view.getContext();
        int resourceId = context.getResources().getIdentifier(name, "drawable", view.getContext().getPackageName());
        return context.getResources().getDrawable(resourceId);
    }

    private void initShopProducts() {
        String [] titles = getResources().getStringArray(R.array.title);
        String [] prices = getResources().getStringArray(R.array.price);
        int []images = {
                R.drawable.fizmat_cap,
                        R.drawable.fizmat_hoody,
                        R.drawable.fizmat_note,
                        R.drawable.fizmat_plcover,
                        R.drawable.fizmat_scarf,
                        R.drawable.fizmat_sweater,
                        R.drawable.fizmat_tie,
                        R.drawable.fizmat_icon,
                        R.drawable.fizmat_polo
        };
        for (int i=0;i<8;i++) shopProducts.add(new Product(titles[i], prices[i], images[i]));
    }
}