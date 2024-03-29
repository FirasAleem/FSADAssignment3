import java.sql.*;

public class Database {
    private static Connection con;

    public int getTitles(String artistName) {
        int titleNum = 0;
        ResultSet rs;
        String sql = "SELECT COUNT(*) AS c FROM album INNER JOIN artist ON album.artistid = artist.artistid WHERE name = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, artistName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                titleNum = rs.getInt(1);
            }
        } catch (SQLException e) {}
        return titleNum;
    }

    public boolean establishDBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);
            return con.isValid(5);
        } catch (Exception e) {e.printStackTrace();}
        return false;
    }
}