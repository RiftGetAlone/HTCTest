package alexeykrasnov.htctest;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import static android.view.View.inflate;

public class CustomAdapter extends BaseAdapter {

    private List<Company.CompanyBean.EmployeesBean> mEmployees;
    private Context mContext;

    public CustomAdapter(Context mContext, List<Company.CompanyBean.EmployeesBean> mEmployees) {
        this.mEmployees = mEmployees;
        this.mContext = mContext;
    }

    @Override public int getCount() {
        return mEmployees.size();
    }

    @Override public Object getItem(int position) {
        return mEmployees.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflate(mContext, R.layout.employee_item, null);
        TextView name = view.findViewById(R.id.tvName);
        TextView phoneNumber = view.findViewById(R.id.tvPhoneNumber);
        TextView skills = view.findViewById(R.id.tvSkills);

        name.setText(mEmployees.get(position).getName());
        phoneNumber.setText(Html.fromHtml("<strong>Phone: </strong>"
                + mEmployees.get(position).getPhone_number()));
        skills.setText(Html.fromHtml("<strong>Skills: </strong>"
                + parseToString(mEmployees.get(position).getSkills())));
        return view;
    }

    private String parseToString(List<String> list) {
        return list.toString().substring(1, list.toString().length() - 1);
    }
}
