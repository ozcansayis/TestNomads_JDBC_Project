package JDBC;

import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Ayse extends _JDBCParent {
    // 2-6-12-18-22-26-27
    @Test
    public void task2() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select employees.emp_no, first_name, last_name from dept_emp " +
                "left join employees ON dept_emp.emp_no=employees.emp_no where dept_no='d003'");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
        DBConnectionClose();
    }

    @Test
    public void task6() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select dept_emp.emp_no, dept_no, first_name, last_name , salary from dept_emp " +
                "left join employees ON dept_emp.emp_no=employees.emp_no " +
                "left join salaries ON dept_emp.emp_no=salaries.emp_no where dept_no='d007' and salary>70000 limit 1000");
        while (rs.next()) {
            System.out.println(rs.getString("emp_no") + " " + rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getString("salary"));
        }
        DBConnectionClose();
    }

    @Test
    public void task12() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select employees.emp_no, first_name, last_name, salary " +
                "from employees " +
                "left join salaries ON employees.emp_no=salaries.emp_no " +
                "where salary=(select max(salary) from salaries)");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
        }
        DBConnectionClose();
    }

    @Test
    public void task18() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select first_name as Ad, last_name as Soyad, hire_date as 'İşe Alım Tarihi' " +
                "from employees where hire_date<'1990-01-01' order by hire_date,first_name limit 1000");

        for (int i = 1; i <= 1000; i++) {
            rs.absolute(i);
            System.out.println(rs.getString("Ad") + " " + rs.getString("Soyad") + " | " + rs.getString("İşe Alım Tarihi"));
        }
        DBConnectionClose();
    }

    @Test
    public void task22() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select count(distinct(first_name)) as 'Benzersiz İlk Ad Sayısı' from employees");
        rs.next();
        System.out.println("Benzersiz İlk Ad Sayısı = " + rs.getString(1));

        ResultSet rs2 = sorguEkrani.executeQuery("select count(distinct(dept_name)) as 'Departman Sayısı' from departments");
        rs2.next();
        System.out.println("Departman Sayısı = " + rs2.getString("Departman Sayısı"));

        DBConnectionClose();
    }

    @Test
    public void task26() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select employees.*, salaries.salary, departments.dept_name, title from departments left join dept_emp ON departments.dept_no=dept_emp.dept_no " +
                "left join employees ON dept_emp.emp_no=employees.emp_no " +
                "left join salaries ON employees.emp_no=salaries.emp_no " +
                "left join titles ON titles.emp_no=salaries.emp_no " +
                "where first_name='Annemarie' and last_name='Redmiles' and salaries.from_date between titles.from_date and titles.to_date");
        rs.last();
        int lastRowNo = rs.getRow();
        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        for (int i = 1; i <= lastRowNo; i++) {
            rs.absolute(i);
            for (int j = 1; j <= numberOfColumns; j++) {
                System.out.print(rs.getString(j) + " ");
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void task27() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select dept_emp.dept_no, employees.*, salaries.salary, title from departments " +
                "left join dept_emp ON departments.dept_no=dept_emp.dept_no " +
                "left join employees ON dept_emp.emp_no=employees.emp_no " +
                "left join salaries ON employees.emp_no=salaries.emp_no " +
                "left join titles ON titles.emp_no=salaries.emp_no " +
                "where dept_emp.dept_no='d005' and salaries.from_date between titles.from_date and titles.to_date limit 1000");
        while (rs.next()) {
            System.out.println(rs.getString("emp_no") + " | " + rs.getString("first_name") + " " + rs.getString("last_name") + " | " + rs.getString("title") + " " + rs.getString("salary"));
        }
        DBConnectionClose();
    }
}
