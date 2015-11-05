package pomona.android.aloke.weather;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class WeatherActivity extends ActionBarActivity {
    public static final String API_KEY = "34f871d6e4bda3d289d3892cf69d6";
    public static final String DEGREE_SYMBOL = "°";

    private TextView tvCurrentCity;
    private TextView tvCurrentTemp;
    private TextView tvLow;
    private TextView tvHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        final EditText etCity = (EditText) findViewById(R.id.etCity);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        tvCurrentCity = (TextView) findViewById(R.id.tvCurrentCity);
        tvCurrentTemp = (TextView) findViewById(R.id.tvCurrentTemp);
        tvLow = (TextView) findViewById(R.id.tvLow);
        tvHigh = (TextView) findViewById(R.id.tvHigh);

        getWeather("Claremont");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the text for the city
                String cityName = etCity.getText().toString();
                getWeather(cityName);
            }
        });
    }

    private void getWeather(String cityName) {
        AsyncTask<String, Void, String> getWeather = new GetWeather(WeatherActivity.this);
        getWeather.execute(cityName);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void updateWeatherColor(String currentTemp) {
        int temp = Integer.parseInt(currentTemp);

        int color;
        if (temp < 0) {
            color = getResources().getColor(R.color.primary_indigo);
        } else if (temp < 10) {
            color = getResources().getColor(R.color.primary_blue);
        } else if (temp < 20) {
            color = getResources().getColor(R.color.primary_light_blue);
        } else if (temp < 30) {
            color = getResources().getColor(R.color.primary_teal);
        } else if (temp < 40) {
            color = getResources().getColor(R.color.primary_light_green);
        } else if (temp < 50) {
            color = getResources().getColor(R.color.primary_green);
        } else if (temp < 60) {
            color = getResources().getColor(R.color.primary_lime);
        } else if (temp < 70) {
            color = getResources().getColor(R.color.primary_yellow);
        } else if (temp < 80) {
            color = getResources().getColor(R.color.primary_amber);
        } else if (temp < 90) {
            color = getResources().getColor(R.color.primary_orange);
        } else {
            color = getResources().getColor(R.color.primary_red);
        }

        tvCurrentTemp.setTextColor(color);
    }

    public void updateWeather(String rawResult) {
        try {
            JSONObject data = new JSONObject(rawResult).getJSONObject("data");

            // update current temp
            JSONObject currentWeather = data.getJSONArray("current_condition").getJSONObject(0);
            tvCurrentTemp.setText(currentWeather.getString("temp_F"));
            updateWeatherColor(currentWeather.getString("temp_F"));

            // update min and max temp
            JSONObject minMaxTemp = data.getJSONArray("weather").getJSONObject(0);
            tvHigh.setText(minMaxTemp.getString("maxtempF") + DEGREE_SYMBOL);
            tvLow.setText(minMaxTemp.getString("mintempF") + DEGREE_SYMBOL);

            // update city text
            JSONObject request = data.getJSONArray("request").getJSONObject(0);
            tvCurrentCity.setText(request.getString("query"));


        } catch (JSONException e) {
            Toast.makeText(WeatherActivity.this, "There was an error updating the weather!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }
}
