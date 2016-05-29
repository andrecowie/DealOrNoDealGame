import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Andre Cowie on 24/04/2016.
 */
public class DBInteractions {
    Connection conn = null;
    String url = "jdbc:derby://localhost:1527/games";
    String username ="root";
    String password ="root";
    
    public void establishConnection(){
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e){
            Logger.getLogger(DBInteractions.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    //Working as expected
    public void newPlayerLoad(String playerName){
        ResultSet rs=null;
        try {
            Statement a = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = "SELECT * FROM GAMES";
            rs=a.executeQuery(sqlQuery);
            rs.beforeFirst();
            System.out.println("Previous games for: "+playerName);
            while(rs.next()){
                int match=rs.getInt("MATCHNUM");
                int prize=rs.getInt("PRIZE");
                int casenum=rs.getInt("CASENUM");
                System.out.println("#"+match+" Won: "+prize+" Case: "+casenum);
            }
            a.close();
            rs.close();
        } catch (SQLException e){
            Logger.getLogger(DBInteractions.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    //Working as expected
    public void savePlayersGame(String playerName, int caseSelected, int prizeRecieved){
        ResultSet rs=null;
        try{
            Statement a=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=a.executeQuery("SELECT MAX(MATCHNUM) FROM GAMES");            
            int matchId = 0;            
            while(rs.next()){
                matchId = (rs.getInt(1))+1;
            }
            String newGame = "INSERT INTO GAMES(USERN, MATCHNUM, PRIZE, CASENUM)"+
                    "VALUES ('"+playerName+"', "+matchId+","+prizeRecieved+", "+caseSelected+")";
            a.executeUpdate(newGame);
        }catch (SQLException e){
            System.err.println("SQL Exception Error " + e.getErrorCode() + "\n" + e.getSQLState());
        }
    }
    //Need to find sql query to select top 5 games based on prize won.
    public String highscoreBoard(){
        String highscores = "";
        ResultSet rs=null;
        try{
            Statement a=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery="SELECT * FROM GAMES WHERE(PRIZE IN(SELECT TOP (5) PRIZE "
                    + "FROM GAMES "
                    + "GROUP BY PRIZE "
                    + "ORDER BY PRIZE DESC))";
            rs=a.executeQuery(sqlQuery);
            int prize;
            int casenum;
            String user;
            while(rs.next()){                
                prize=rs.getInt("PRIZE");
                casenum=rs.getInt("CASENUM");
                user=rs.getString("USERN");
                highscores += user+" Case: "+casenum+" Prize: "+prize+"\n";
            }
        } catch (SQLException e) {
            Logger.getLogger(DBInteractions.class.getName()).log(Level.SEVERE, null, e);
        }
        return highscores;
    }
}