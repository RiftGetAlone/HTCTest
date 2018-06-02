package alexeykrasnov.htctest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private ListView employeeList;
    private CustomAdapter customAdapter;
    private final String URL = "http://www.mocky.io/v2/56fa31e0110000f920a72134";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeList = findViewById(R.id.employees_list);
        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Company company = gson.fromJson(response, Company.class);
                customAdapter = new CustomAdapter(MainActivity.this, company.getCompany().getEmployees());
                employeeList.setAdapter(customAdapter);
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


}