package alexeykrasnov.htctest;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView employeesList;
    private TextView companyAge;
    private TextView competences;
    private static final String URL = "http://www.mocky.io/v2/56fa31e0110000f920a72134";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companyAge = findViewById(R.id.tvCompanyAge);
        competences = findViewById(R.id.tvCompetences);
        employeesList = findViewById(R.id.employees_list);
        employeesList.setLayoutManager(new LinearLayoutManager(this));

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Company.CompanyBean company = gson.fromJson(response, Company.class).getCompany();
                setTitle(company.getName());
                companyAge.setText(Html.fromHtml("<strong>Company age: </strong>"
                        + company.getAge()));
                competences.setText(Html.fromHtml("<strong>Competences: </strong>"
                        + ListUtilities.parseToString(company.getCompetences())));
                employeesList.setAdapter(new EmployeesAdapter(company.getEmployees()));
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