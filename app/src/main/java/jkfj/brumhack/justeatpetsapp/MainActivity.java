package jkfj.brumhack.justeatpetsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String FILTER = "filter";

    private ImageButton btnLogo;
    private ImageButton btnShootPet;
    private ImageButton btnCat;
    private ImageButton btnDog;
    private ImageButton btnFish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogo = (ImageButton) findViewById(R.id.btnLogo);
        btnLogo.setOnClickListener(this);
        btnShootPet = (ImageButton) findViewById(R.id.btnShootPets);
        btnCat = (ImageButton) findViewById(R.id.btnCat);
        btnCat.setOnClickListener(this);
        btnDog = (ImageButton) findViewById(R.id.btnDog);
        btnDog.setOnClickListener(this);
        btnFish = (ImageButton) findViewById(R.id.btnFish);
        btnFish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ImageButton pressed = (ImageButton) v;
        Intent i = new Intent(MainActivity.this, ProductsActivity.class);
        if(pressed.getId() == R.id.btnCat) {
            i.putExtra(FILTER, "cat");
        }
        else if(pressed.getId() == R.id.btnDog){
            i.putExtra(FILTER, "dog");
        }
        else if(pressed.getId() == R.id.btnFish) {
            i.putExtra(FILTER, "fish");
        }
        startActivity(i);
    }

    public void startShootPetsActivity(View view) {
        Intent i = new Intent(MainActivity.this, RecognitionActivity.class);
        i.putExtra(FILTER, "#nofilter");
        startActivity(i);
    }
}
