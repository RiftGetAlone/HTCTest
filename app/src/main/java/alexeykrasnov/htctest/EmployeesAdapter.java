package alexeykrasnov.htctest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import static alexeykrasnov.htctest.ListUtilities.parseToString;

public class EmployeesAdapter extends  RecyclerView.Adapter<EmployeesAdapter.CustomViewHolder> {

    private List<Company.CompanyBean.EmployeesBean> mEmployees;

    public EmployeesAdapter (List<Company.CompanyBean.EmployeesBean> mEmployees) {
        this.mEmployees = mEmployees;
    }

    @NonNull @Override public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                  int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item, parent,false));
    }

    @Override public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Company.CompanyBean.EmployeesBean employee = mEmployees.get(position);

        holder.name.setText(employee.getName());
        holder.phoneNumber.setText(Html.fromHtml("<strong>Phone: </strong>"
                + employee.getPhone_number()));
        holder.skills.setText(Html.fromHtml("<strong>Skills: </strong>"
                + parseToString(employee.getSkills())));
    }

    @Override public int getItemCount() {
        return mEmployees.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView phoneNumber;
        TextView skills;

        public CustomViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            phoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            skills = itemView.findViewById(R.id.tvSkills);
        }
    }
}