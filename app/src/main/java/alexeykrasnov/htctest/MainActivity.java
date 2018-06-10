package alexeykrasnov.htctest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private LinearLayout linearLayout;
    private Button retryButton;
    private TextView companyAge;
    private TextView competences;
    private static final String URL = "http://www.mocky.io/v2/56fa31e0110000f920a72134";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestQueue queue = Volley.newRequestQueue(this);
        linearLayout = findViewById(R.id.linear_layout);
        retryButton = new Button(this);
        companyAge = findViewById(R.id.tvCompanyAge);
        competences = findViewById(R.id.tvCompetences);
        employeesList = findViewById(R.id.employees_list);

        employeesList.setLayoutManager(new LinearLayoutManager(this));

        final StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
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
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                companyAge.setText(R.string.network_error);
                retryButton.setText(R.string.retry);
                linearLayout.addView(retryButton, lp);
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(v);
                queue.add(request);
            }
        };
        retryButton.setOnClickListener(onClickListener);
        queue.add(request);
    }
}