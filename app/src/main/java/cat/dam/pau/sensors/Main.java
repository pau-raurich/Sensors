package cat.dam.pau.sensors;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class Main extends AppCompatActivity {
    ObjectePilota pilota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pilota = new ObjectePilota(this);
        setContentView(pilota);
        setContentView(R.layout.activity_main);
    }
}