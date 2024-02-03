import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "myusername";
        String password = "mypassword";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT NAME FROM mytable";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("NAME");
                System.out.println(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void printNames(Connection c) throws SQLException {
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT NAME FROM mytable");
        while (rs.next()){
            rs.getString("NAME");
        }
    }

}

