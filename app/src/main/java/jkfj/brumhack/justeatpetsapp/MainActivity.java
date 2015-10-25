package jkfj.brumhack.justeatpetsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnShootPet;
    private Button btnCat;
    private Button btnDog;
    private Button btnFish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShootPet = (Button) findViewById(R.id.btnShootPet);
        btnCat = (Button) findViewById(R.id.btnCat);
        btnDog = (Button) findViewById(R.id.btnDog);
        btnFish = (Button) findViewById(R.id.btnFish);
    }
}
