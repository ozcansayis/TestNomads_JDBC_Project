package JDBC;

import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Umit extends _JDBCParent {

    @Test
    public void Task13() throws SQLException {

        DBConnectionOpen();

        ResultSet resultTable = sorguEkrani.executeQuery("select emp_no, salary, to_date from salaries " +
                " where (emp_no, to_date) in (select emp_no, max(to_date) from salaries group by emp_no) limit 1000");

        ResultSetMetaData rsmd = resultTable.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();

        while (resultTable.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.print(resultTable.getString(i) + "\t");

            System.out.println();
        }
        DBConnectionClose();

    }

    @Test
    public void Task15() throws SQLException {
        ResultSet resultTable= sorguEkrani.executeQuery("Select e.first_name, e.last_name, Max(s.salary) as max_salary " +
                "From employees e Inner join dept_emp de on e.emp_no = de.emp_no Inner join departments d on de.dept_no = d.dept_no " +
                "Inner join salaries s on e.emp_no = s.emp_no Where d.dept_name = 'Research' Group By e.first_name, e.last_name " +
                "Order By max_salary DESC Limit 1");

        ResultSetMetaData rsmd = resultTable.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();

        while (resultTable.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.print(resultTable.getString(i) + "\t");

            System.out.println();
        }
        DBConnectionClose();

    }

    @Test
    public void Task28() throws SQLException{

        ResultSet resultTable = sorguEkrani.executeQuery("Select e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date, t.title, s.salary " +
                "From employees e Join titles t ON e.emp_no = t.emp_no Join salaries s ON e.emp_no = s.emp_no " +
                "Where e.hire_date > '1994-02-24' and s.salary > 50000");

        ResultSetMetaData rsmd = resultTable.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();

        while (resultTable.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.print(resultTable.getString(i) + "\t");

            System.out.println();
        }
        DBConnectionClose();
    }
}

//@Test
//public void Task39()

// 32-39-28-13-15-31
