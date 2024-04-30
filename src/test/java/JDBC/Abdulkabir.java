package JDBC;

import Utilities.DBUtility;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Abdulkabir extends _JDBCParent {
    //SORULAR :  35-11-29-30-8-10-41-1
    @Test
    public void test_1() throws SQLException {
        //1. List all employees in department D001
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("SELECT * FROM employees left JOIN dept_emp ON employees.emp_no = dept_emp.emp_no WHERE dept_no = 'D001'");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs.next()){
            for (int i = 1; i <= rsmd.getColumnCount() ; i++)
                System.out.print(rs.getString(i)+"\t");
                System.out.println();
            }
        DBConnectionClose();
    }
    //*******************************************************************************************

    @Test
    public void test_8() throws SQLException {
        //8. Calculate the average salary for each department (by department number or department name)
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select de.dept_no, AVG(s.salary) AS average_salary FROM dept_emp de left join salaries s ON de.emp_no = s.emp_no GROUP BY de.dept_no");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs.next()){
            for (int i = 1; i <= rsmd.getColumnCount() ; i++)
                System.out.print(rs.getString(i)+"\t");
            System.out.println();
        }
        DBConnectionClose();
    }
    //*******************************************************************************************
    @Test
    public void test_10() throws SQLException {
        //10. Find all salary changes for employee with emp. no '10102'
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select * FROM employees.salaries where emp_no='10102'");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs.next()){
            for (int i = 1; i <= rsmd.getColumnCount() ; i++)
                System.out.print(rs.getString(i)+"\t");
            System.out.println();
        }
        DBConnectionClose();
    }
    //*******************************************************************************************
    @Test
    public void test_11() throws SQLException {
        //11. Find the salary increases for employee with employee number '10102' (using the to_date column in salaries)
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("SELECT * FROM employees.salaries where emp_no='10102' order by salary");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.print(rs.getString(i) + "\t");
            System.out.println();
        }
        DBConnectionClose();
    }
    //*******************************************************************************************
    @Test
    public void test_29() throws SQLException {
        //29. List all employees working in the "Sales" department with the title "Manager"
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date FROM employees e left join dept_manager dm ON e.emp_no = dm.emp_no left join departments d ON dm.dept_no = d.dept_no left join titles t ON e.emp_no = t.emp_no WHERE d.dept_name = 'Sales' AND t.title = 'Manager'");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.print(rs.getString(i) + "\t");
            System.out.println();
        }
        DBConnectionClose();
    }
    //*******************************************************************************************
    @Test
    public void test_30() throws SQLException {
        //30.   Find the department where employee with '10102' has worked the longest
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select first_name , from_date, to_date, dept_name from employees left join dept_emp on dept_emp.emp_no=employees.emp_no left join departments on departments.dept_no=dept_emp.dept_no where employees.emp_no='10102'");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.print(rs.getString(i) + "\t");
            System.out.println();
        }
        DBConnectionClose();

    }
    //*******************************************************************************************
    @Test
    public void test_35() throws SQLException {
        // 35.  Finding the managerial history of employee with ID (emp. no) 110022
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select departments.dept_no, dept_manager. from_date, dept_manager.to_date, dept_name  as yönetinıBolupÇalışanBölüm from dept_manager left join departments on departments.dept_no=dept_manager.dept_no where dept_manager.emp_no='110022'");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.print(rs.getString(i) + "\t");
            System.out.println();
        }
        DBConnectionClose();
    }
    //*******************************************************************************************
    @Test
    public void test_41() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select dept_emp.emp_no, dept_emp.dept_no,departments.dept_name from dept_emp left join departments on departments.dept_no=dept_emp.dept_no where dept_emp.emp_no='10102'");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.print(rs.getString(i) + "\t");
            System.out.println();
        }
        DBConnectionClose();
    }
}
