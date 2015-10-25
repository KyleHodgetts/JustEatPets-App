package jkfj.brumhack.justeatpetsapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class ProductsActivity extends Activity {

    private ListView listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        listProducts = (ListView)findViewById(R.id.listProducts);
    }
}
