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
}

//@Test
//public void Task39()

// 32-39-28-13-15-31
