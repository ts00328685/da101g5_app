package testtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadFileToOracle {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "DA101G5";
		String passwd = "123456";
		String picName = "1533999199969.gif";
		String res_id = "RS00004";
		byte[] picbytes = null;
		
		Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
        	Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            File pic = new File("D:\\uploadTest", picName); 
                                                     		
//            long flen = pic.length();
//            InputStream is = new FileInputStream(pic);  
//            System.out.println("\n\nUpdate the database... ");
//            pstmt = con.prepareStatement(
//             "update question set quefile = ? where que_id = ?");	
//            pstmt.setBinaryStream(1, is, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
//            pstmt.setString(2, "Q00002");
//            int rowsUpdated = pstmt.executeUpdate();
            
            
            picbytes = new byte[(int)pic.length()];
            InputStream is = new FileInputStream(pic);
            is.read(picbytes);/*把檔案讀到陣列*/
            pstmt = con.prepareStatement(
                  "update response set resfile = ? where res_id = ?");	
                 pstmt.setBytes(1, picbytes); 
                 pstmt.setString(2, res_id);
            int rowsUpdated = pstmt.executeUpdate();
            
            System.out.print("Changed " + rowsUpdated);

            if (1 == rowsUpdated)
                System.out.println(" row.");
            else
                System.out.println(" rows.");

            is.close();
            pstmt.close();

          } catch (Exception e) {
                e.printStackTrace();
          } finally {
                try {
                  con.close();
                } catch (SQLException e) {
                }
         }
  }

	}


