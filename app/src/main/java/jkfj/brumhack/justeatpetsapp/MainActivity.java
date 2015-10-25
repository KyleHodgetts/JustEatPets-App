package jkfj.brumhack.justeatpetsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnShootPet;
    private ImageButton btnCat;
    private ImageButton btnDog;
    private ImageButton btnFish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShootPet = (ImageButton) findViewById(R.id.btnShootPets);
        btnCat = (ImageButton) findViewById(R.id.btnCat);
        btnDog = (ImageButton) findViewById(R.id.btnDog);
        btnFish = (ImageButton) findViewById(R.id.btnFish);
    }

    @Override
    public void onClick(View v) {

    }
}
