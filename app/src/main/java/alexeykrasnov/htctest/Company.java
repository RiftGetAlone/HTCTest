package alexeykrasnov.htctest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {

    private CompanyBean company;

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public static class CompanyBean {

        private String name;
        private String age;
        private List<String> competences;
        private List<EmployeesBean> employees;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public List<String> getCompetences() {
            return competences;
        }

        public void setCompetences(List<String> competences) {
            this.competences = competences;
        }

        public List<EmployeesBean> getEmployees() {
            Collections.sort(employees, new Comparator<EmployeesBean>() {
                public int compare(EmployeesBean obj1, EmployeesBean obj2) {
                    return obj1.name.compareToIgnoreCase(obj2.name);
                }
            });
            return employees;
        }

        public void setEmployees(List<EmployeesBean> employees) {
            this.employees = employees;
        }

        public static class EmployeesBean {

            private String name;
            private String phone_number;
            private List<String> skills;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public List<String> getSkills() {
                return skills;
            }

            public void setSkills(List<String> skills) {
                this.skills = skills;
            }
        }
    }
}