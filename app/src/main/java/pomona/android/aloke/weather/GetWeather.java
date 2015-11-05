package pomona.android.aloke.weather;

import android.content.Context;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Aloke on 10/28/15.
 */
public class GetWeather extends AsyncTask<String, Void, String> {
    private Context context;
    public static final String URL_BASE = "http://api.worldweatheronline.com/free/v2/weather.ashx?q=%s&format=json&num_of_days=5&key=34f871d6e4bda3d289d3892cf69d6";

    public GetWeather(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        InputStream is = null;
        String result = "";

        try {
            String city = URLEncoder.encode(params[0], "UTF-8");
            URL url = new URL(String.format(URL_BASE, city));
            connection = (HttpURLConnection) url.openConnection();

            setTimeoutValues(connection);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = connection.getInputStream();
                result = getResultFromConnection(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (connection != null) {
                    connection.disconnect();
                }
            }

        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        ((WeatherActivity) context).updateWeather(result);
    }

    private String getResultFromConnection(InputStream is) throws IOException{
        int ch;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        while ((ch = is.read()) != -1) {
            bos.write(ch);
        }

        return new String(bos.toByteArray(), "UTF-8");
    }

    private void setTimeoutValues(HttpURLConnection connection) {
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
    }
}
