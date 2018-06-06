package alexeykrasnov.htctest;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private TextView companyAge;
    private TextView competences;
    private ListView employeeList;
    private CustomAdapter customAdapter;
    private final String URL = "http://www.mocky.io/v2/56fa31e0110000f920a72134";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        companyAge = findViewById(R.id.tvCompanyAge);
        competences = findViewById(R.id.tvCompetences);
        employeeList = findViewById(R.id.employees_list);

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Company company = gson.fromJson(response, Company.class);
                setTitle(company.getCompany().getName());
                companyAge.setText(Html.fromHtml("<strong>Company age: </strong>"
                        + company.getCompany().getAge()));
                competences.setText(Html.fromHtml("<strong>Competences: </strong>"
                        + CustomAdapter.parseToString(company.getCompany().getCompetences())));
                customAdapter = new CustomAdapter(MainActivity.this, company.getCompany().getEmployees());
                employeeList.setAdapter(customAdapter);
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
                companyAge.setText(R.string.network_error);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
