package JDBC;

import Utilities.DBUtility;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class Ozcan extends DBUtility {
    // 23-24-9-14-17-20
    @Test
    public void task9() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select departments.dept_name as DepartmanAdı, avg(salaries.salary) as MaaşOrtalaması from departments \n" +
                "left join dept_emp on departments.dept_no=dept_emp.dept_no \n" +
                "left join salaries on dept_emp.emp_no=salaries.emp_no group by dept_emp.dept_no");

        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.printf("%-20s", rsmd.getColumnName(i));
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("%-20s", rs.getString(i));
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void task14() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("SELECT e.first_name, e.last_name, d.dept_name, MAX(s.salary) AS highest_salary\n" +
                "FROM employees e\n" +
                "INNER JOIN dept_emp de ON e.emp_no = de.emp_no\n" +
                "INNER JOIN departments d ON de.dept_no = d.dept_no\n" +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "WHERE d.dept_name = 'Sales'\n" +
                "GROUP BY e.first_name, e.last_name,d.dept_name\n" +
                "ORDER BY highest_salary DESC\n" +
                "LIMIT 1");
        while (rs.next()) {
            System.out.println("First Name: " + rs.getString("first_name") +
                    "\nLast Name: " + rs.getString("last_name") +
                    "\nDepartment: " + rs.getString("dept_name") +
                    "\nSalary: " + rs.getString("highest_salary"));
        }
        DBConnectionClose();
    }

    @Test
    public void task17() {
        DBConnectionOpen();
        List<List<String>> returnedData = getListData("select dept_name as DepartmanAdı, first_name as Ad, last_name as Soyad, maasOrt as MaaşOrtalaması\n" +
                "from (\n" +
                "    select departments.dept_name, employees.first_name, employees.last_name, AVG(salaries.salary) as maasOrt\n" +
                "    from departments\n" +
                "    join dept_emp on departments.dept_no = dept_emp.dept_no\n" +
                "    join employees on employees.emp_no = dept_emp.emp_no\n" +
                "    join salaries on salaries.emp_no = employees.emp_no\n" +
                "    group by employees.emp_no\n" +
                "    order by maasOrt desc\n" +
                ") res\n" +
                "group by dept_name\n" +
                "order by MAX(maasOrt) desc");
        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-20s", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void task20() {
        DBConnectionOpen();
        List<List<String>> returnedData = getListData("select first_name, last_name, hire_date, salary from employees\n" +
                "left join salaries on salaries.emp_no=employees.emp_no\n" +
                "left join dept_emp on employees.emp_no=dept_emp.emp_no\n" +
                "where hire_date between '1985-01-01' and '1989-12-31' and dept_emp.dept_no = 'd007'\n" +
                "order by salary desc");
        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void task23() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select dept_emp.dept_no, COUNT(*) as employee_count from dept_emp group by dept_emp.dept_no");
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void task24() {
        DBConnectionOpen();
        List<List<String>> data = getListData("select * from employees where hire_date <= DATE_SUB('1990-02-20', INTERVAL 5 YEAR);");
        for (List<String> r : data) {
            for (String c : r) {
                System.out.printf("%-15s", c);
            }
            System.out.println();
        }
        DBConnectionClose();
    }
}
