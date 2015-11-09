package pomona.android.aloke.weather;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WeatherActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        final EditText etCity = (EditText) findViewById(R.id.etCity);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the text for the city
                String cityName = etCity.getText().toString();
                Toast.makeText(WeatherActivity.this, cityName, Toast.LENGTH_LONG).show();
            }
        });
    }
}
