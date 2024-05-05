package JDBC;

import org.testng.annotations.Test;

import java.sql.*;
import java.sql.SQLException;

public class Samed extends _JDBCParent {
    // 34-16-33-38-7-36-3

    @Test
    public void _7() throws SQLException {
        DBConnectionOpen();

        ResultSet rs = sorguEkrani.executeQuery("select distinct first_name,last_name,salary from employees left join salaries ON employees.emp_no = salaries.emp_no where salary between 50000 AND 100000 order by salary");
        ResultSetMetaData rsd = rs.getMetaData();
        for (int i = 1; i <= rsd.getColumnCount(); i++) {
            System.out.print(rsd.getColumnName(i) + "  ");
        }
        while (rs.next()) {
            for (int i = 1; i <= rsd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "  ");
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void _16_() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select departments.dept_name, employees.first_name, employees.last_name, MAX(salaries.salary) AS maxSal from departments left join dept_manager ON departments.dept_no = dept_manager.dept_no left join employees ON dept_manager.emp_no = employees.emp_no left join salaries ON employees.emp_no = salaries.emp_no group by departments.dept_name order by maxSal");
        ResultSetMetaData rsd = rs.getMetaData();
        for (int i = 1; i <= rsd.getColumnCount(); i++) {
            System.out.print(rsd.getColumnName(i) + "  ");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsd.getColumnCount(); i++) {
                System.out.print(" | | " + rs.getString(i));
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void _33_() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select avg((2024-left(birth_date,4))) as ortalamaYas from employees");
        rs.next();
        System.out.println(rs.getString(1));
        DBConnectionClose();
    }

    @Test
    void _34_() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select departments.dept_name,count(*) from employees left join dept_emp ON dept_emp.emp_no=employees.emp_no left join departments ON departments.dept_no=dept_emp.dept_no group by departments.dept_no;");
        ResultSetMetaData rsd = rs.getMetaData();
        for (int i = 1; i <= rsd.getColumnCount(); i++) {
            System.out.print("|| " + rsd.getColumnName(i));
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsd.getColumnCount(); i++) {
                System.out.print(" = " + rs.getString(i));
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    void _36_() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select concat(employees.first_name,' ',employees.last_name) as Employee,  concat(2024-left(from_date,4),' Years') as Duration  from dept_emp left join employees ON employees.emp_no=dept_emp.emp_no order by Duration");
        ResultSetMetaData rsd = rs.getMetaData();
        rs.next();
        System.out.println(rs.getString(2));
        for (int i = 1; i <= rsd.getColumnCount(); i++) {
            System.out.print(rsd.getColumnName(i) + " ");
        }
        System.out.println();
        while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.print("  " + rs.getString(2));
        }
        System.out.println();
        DBConnectionClose();
    }

    @Test
    void _38_() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select concat(employees.first_name,' ',employees.last_name) as Employees,titles.title, dept_emp.dept_no as Department_Number from titles left join dept_emp ON dept_emp.emp_no=titles.emp_no left join employees ON dept_emp.emp_no=employees.emp_no where dept_no = 'd005' and title='Manager';");
        ResultSetMetaData rsd = rs.getMetaData();
        int columnCount = rsd.getColumnCount();
        for (int i = 1; i < columnCount; i++) {
            System.out.print(rsd.getColumnName(i) + " ");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
        DBConnectionClose();
    }
}