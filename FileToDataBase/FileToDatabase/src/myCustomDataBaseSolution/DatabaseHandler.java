package myCustomDataBaseSolution;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * This program demonstrates making JDBC connection to a SQLite database.
 * @author www.codejava.net
 *
 */
public class DatabaseHandler {
 
	private Connection dbConnection=null;
	Statement stmt = null;
	String currentTableName=null;
	
    public DatabaseHandler() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:database/data.db";
            dbConnection = DriverManager.getConnection(dbURL);
            dbConnection.setAutoCommit(false);
            if (dbConnection != null) {
                System.out.println("Connected to the database");
                DatabaseMetaData dm = (DatabaseMetaData) dbConnection.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void close(){
    	try {
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void addTable(String tableName){
    	try {
			currentTableName=tableName;
    		stmt = dbConnection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS " + currentTableName +
			             "(PINNAME        TEXT    NOT NULL," +
			             " MONTH          INT     NOT NULL, " + 
			             " DAY            INT     NOT NULL, " + 
			             " HOUR           INT     NOT NULL, " + 
			             " MINUTE         INT     NOT NULL, " +
			             " SECOND         INT     NOT NULL, " +
			             " F1             INT     NOT NULL, " +
			             " F2             INT     NOT NULL, " +
			             " F3             INT     NOT NULL, " +
			             " F4             INT     NOT NULL, " +
			             " AUDIO000       INT     NOT NULL, " +
			             " AUDIO001       INT     NOT NULL, " +
			             " AUDIO002       INT     NOT NULL, " +
			             " AUDIO003       INT     NOT NULL, " +
			             " AUDIO004       INT     NOT NULL, " +
			             " AUDIO005       INT     NOT NULL, " +
			             " AUDIO006       INT     NOT NULL, " +
			             " AUDIO007       INT     NOT NULL, " +
			             " AUDIO008       INT     NOT NULL, " +
			             " AUDIO009       INT     NOT NULL, " +
			             " AUDIO010       INT     NOT NULL, " +
			             " AUDIO011       INT     NOT NULL, " +
			             " AUDIO012       INT     NOT NULL, " +
			             " AUDIO013       INT     NOT NULL, " +
			             " AUDIO014       INT     NOT NULL, " +
			             " AUDIO015       INT     NOT NULL, " +
			             " AUDIO016       INT     NOT NULL, " +
			             " AUDIO017       INT     NOT NULL, " +
			             " AUDIO018       INT     NOT NULL, " +
			             " AUDIO019       INT     NOT NULL, " +
			             " AUDIO020       INT     NOT NULL, " +
			             " AUDIO021       INT     NOT NULL, " +
			             " AUDIO022       INT     NOT NULL, " +
			             " AUDIO023       INT     NOT NULL, " +
			             " AUDIO024       INT     NOT NULL, " +
			             " AUDIO025       INT     NOT NULL, " +
			             " AUDIO026       INT     NOT NULL, " +
			             " AUDIO027       INT     NOT NULL, " +
			             " AUDIO028       INT     NOT NULL, " +
			             " AUDIO029       INT     NOT NULL, " +
			             " AUDIO030       INT     NOT NULL, " +
			             " AUDIO031       INT     NOT NULL, " +
			             " AUDIO032       INT     NOT NULL, " +
			             " AUDIO033       INT     NOT NULL, " +
			             " AUDIO034       INT     NOT NULL, " +
			             " AUDIO035       INT     NOT NULL, " +
			             " AUDIO036       INT     NOT NULL, " +
			             " AUDIO037       INT     NOT NULL, " +
			             " AUDIO038       INT     NOT NULL, " +
			             " AUDIO039       INT     NOT NULL, " +
			             " AUDIO040       INT     NOT NULL, " +
			             " AUDIO041       INT     NOT NULL, " +
			             " AUDIO042       INT     NOT NULL, " +
			             " AUDIO043       INT     NOT NULL, " +
			             " AUDIO044       INT     NOT NULL, " +
			             " AUDIO045       INT     NOT NULL, " +
			             " AUDIO046       INT     NOT NULL, " +
			             " AUDIO047       INT     NOT NULL, " +
			             " AUDIO048       INT     NOT NULL, " +
			             " AUDIO049       INT     NOT NULL, " +
			             " AUDIO050       INT     NOT NULL, " +
			             " AUDIO051       INT     NOT NULL, " +
			             " AUDIO052       INT     NOT NULL, " +
			             " AUDIO053       INT     NOT NULL, " +
			             " AUDIO054       INT     NOT NULL, " +
			             " AUDIO055       INT     NOT NULL, " +
			             " AUDIO056       INT     NOT NULL, " +
			             " AUDIO057       INT     NOT NULL, " +
			             " AUDIO058       INT     NOT NULL, " +
			             " AUDIO059       INT     NOT NULL, " +
			             " AUDIO060       INT     NOT NULL, " +
			             " AUDIO061       INT     NOT NULL, " +
			             " AUDIO062       INT     NOT NULL, " +
			             " AUDIO063       INT     NOT NULL, " +
			             " AUDIO064       INT     NOT NULL, " +
			             " AUDIO065       INT     NOT NULL, " +
			             " AUDIO066       INT     NOT NULL, " +
			             " AUDIO067       INT     NOT NULL, " +
			             " AUDIO068       INT     NOT NULL, " +
			             " AUDIO069       INT     NOT NULL, " +
			             " AUDIO070       INT     NOT NULL, " +
			             " AUDIO071       INT     NOT NULL, " +
			             " AUDIO072       INT     NOT NULL, " +
			             " AUDIO073       INT     NOT NULL, " +
			             " AUDIO074       INT     NOT NULL, " +
			             " AUDIO075       INT     NOT NULL, " +
			             " AUDIO076       INT     NOT NULL, " +
			             " AUDIO077       INT     NOT NULL, " +
			             " AUDIO078       INT     NOT NULL, " +
			             " AUDIO079       INT     NOT NULL, " +
			             " AUDIO080       INT     NOT NULL, " +
			             " AUDIO081       INT     NOT NULL, " +
			             " AUDIO082       INT     NOT NULL, " +
			             " AUDIO083       INT     NOT NULL, " +
			             " AUDIO084       INT     NOT NULL, " +
			             " AUDIO085       INT     NOT NULL, " +
			             " AUDIO086       INT     NOT NULL, " +
			             " AUDIO087       INT     NOT NULL, " +
			             " AUDIO088       INT     NOT NULL, " +
			             " AUDIO089       INT     NOT NULL, " +
			             " AUDIO090       INT     NOT NULL, " +
			             " AUDIO091       INT     NOT NULL, " +
			             " AUDIO092       INT     NOT NULL, " +
			             " AUDIO093       INT     NOT NULL, " +
			             " AUDIO094       INT     NOT NULL, " +
			             " AUDIO095       INT     NOT NULL, " +
			             " AUDIO096       INT     NOT NULL, " +
			             " AUDIO097       INT     NOT NULL, " +
			             " AUDIO098       INT     NOT NULL, " +
			             " AUDIO099       INT     NOT NULL, " +
			             " AUDIO100       INT     NOT NULL, " +
			             " AUDIO101       INT     NOT NULL, " +
			             " AUDIO102       INT     NOT NULL, " +
			             " AUDIO103       INT     NOT NULL, " +
			             " AUDIO104       INT     NOT NULL, " +
			             " AUDIO105       INT     NOT NULL, " +
			             " AUDIO106       INT     NOT NULL, " +
			             " AUDIO107       INT     NOT NULL, " +
			             " AUDIO108       INT     NOT NULL, " +
			             " AUDIO109       INT     NOT NULL, " +
			             " AUDIO110       INT     NOT NULL, " +
			             " AUDIO111       INT     NOT NULL, " +
			             " AUDIO112       INT     NOT NULL, " +
			             " AUDIO113       INT     NOT NULL, " +
			             " AUDIO114       INT     NOT NULL, " +
			             " AUDIO115       INT     NOT NULL, " +
			             " AUDIO116       INT     NOT NULL, " +
			             " AUDIO117       INT     NOT NULL, " +
			             " AUDIO118       INT     NOT NULL, " +
			             " AUDIO119       INT     NOT NULL, " +
			             " AUDIO120       INT     NOT NULL, " +
			             " AUDIO121       INT     NOT NULL, " +
			             " AUDIO122       INT     NOT NULL, " +
			             " AUDIO123       INT     NOT NULL, " +
			             " AUDIO124       INT     NOT NULL, " +
			             " AUDIO125       INT     NOT NULL, " +
			             " AUDIO126       INT     NOT NULL, " +
			             " AUDIO127       INT     NOT NULL, " +
			             " FFT00          INT     NOT NULL, " +
			             " FFT01          INT     NOT NULL, " +
			             " FFT02          INT     NOT NULL, " +
			             " FFT03          INT     NOT NULL, " +
			             " FFT04          INT     NOT NULL, " +
			             " FFT05          INT     NOT NULL, " +
			             " FFT06          INT     NOT NULL, " +
			             " FFT07          INT     NOT NULL, " +
			             " FFT08          INT     NOT NULL, " +
			             " FFT09          INT     NOT NULL, " +
			             " FFT10          INT     NOT NULL, " +
			             " FFT11          INT     NOT NULL, " +
			             " FFT12          INT     NOT NULL, " +
			             " FFT13          INT     NOT NULL, " +
			             " FFT14          INT     NOT NULL, " +
			             " FFT15          INT     NOT NULL, " +
			             " FFT16          INT     NOT NULL, " +
			             " FFT17          INT     NOT NULL, " +
			             " FFT18          INT     NOT NULL, " +
			             " FFT19          INT     NOT NULL, " +
			             " FFT20          INT     NOT NULL, " +
			             " FFT21          INT     NOT NULL, " +
			             " FFT22          INT     NOT NULL, " +
			             " FFT23          INT     NOT NULL, " +
			             " FFT24          INT     NOT NULL, " +
			             " FFT25          INT     NOT NULL, " +
			             " FFT26          INT     NOT NULL, " +
			             " FFT27          INT     NOT NULL, " +
			             " FFT28          INT     NOT NULL, " +
			             " FFT29          INT     NOT NULL, " +
			             " FFT30          INT     NOT NULL, " +
			             " FFT31          INT     NOT NULL, " +
			             " FFT32          INT     NOT NULL, " +
			             " FFT33          INT     NOT NULL, " +
			             " FFT34          INT     NOT NULL, " +
			             " FFT35          INT     NOT NULL, " +
			             " FFT36          INT     NOT NULL, " +
			             " FFT37          INT     NOT NULL, " +
			             " FFT38          INT     NOT NULL, " +
			             " FFT39          INT     NOT NULL, " +
			             " FFT40          INT     NOT NULL, " +
			             " FFT41          INT     NOT NULL, " +
			             " FFT42          INT     NOT NULL, " +
			             " FFT43          INT     NOT NULL, " +
			             " FFT44          INT     NOT NULL, " +
			             " FFT45          INT     NOT NULL, " +
			             " FFT46          INT     NOT NULL, " +
			             " FFT47          INT     NOT NULL, " +
			             " FFT48          INT     NOT NULL, " +
			             " FFT49          INT     NOT NULL, " +
			             " FFT50          INT     NOT NULL, " +
			             " FFT51          INT     NOT NULL, " +
			             " FFT52          INT     NOT NULL, " +
			             " FFT53          INT     NOT NULL, " +
			             " FFT54          INT     NOT NULL, " +
			             " FFT55          INT     NOT NULL, " +
			             " FFT56          INT     NOT NULL, " +
			             " FFT57          INT     NOT NULL, " +
			             " FFT58          INT     NOT NULL, " +
			             " FFT59          INT     NOT NULL, " +
			             " FFT60          INT     NOT NULL, " +
			             " FFT61          INT     NOT NULL, " +
			             " FFT62          INT     NOT NULL, " +
			             " FFT63          INT     NOT NULL)"; 
			
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void addRow(String sqlValues){
    	try {
			stmt = dbConnection.createStatement();
			String sql = "INSERT INTO " + currentTableName + " (PINNAME,MONTH,DAY,HOUR,MINUTE,SECOND,F1,F2,F3,F4,AUDIO000,AUDIO001,AUDIO002,AUDIO003,AUDIO004,AUDIO005,AUDIO006,AUDIO007,AUDIO008,AUDIO009,AUDIO010,AUDIO011,AUDIO012,AUDIO013,AUDIO014,AUDIO015,AUDIO016,AUDIO017,AUDIO018,AUDIO019,AUDIO020,AUDIO021,AUDIO022,AUDIO023,AUDIO024,AUDIO025,AUDIO026,AUDIO027,AUDIO028,AUDIO029,AUDIO030,AUDIO031,AUDIO032,AUDIO033,AUDIO034,AUDIO035,AUDIO036,AUDIO037,AUDIO038,AUDIO039,AUDIO040,AUDIO041,AUDIO042,AUDIO043,AUDIO044,AUDIO045,AUDIO046,AUDIO047,AUDIO048,AUDIO049,AUDIO050,AUDIO051,AUDIO052,AUDIO053,AUDIO054,AUDIO055,AUDIO056,AUDIO057,AUDIO058,AUDIO059,AUDIO060,AUDIO061,AUDIO062,AUDIO063,AUDIO064,AUDIO065,AUDIO066,AUDIO067,AUDIO068,AUDIO069,AUDIO070,AUDIO071,AUDIO072,AUDIO073,AUDIO074,AUDIO075,AUDIO076,AUDIO077,AUDIO078,AUDIO079,AUDIO080,AUDIO081,AUDIO082,AUDIO083,AUDIO084,AUDIO085,AUDIO086,AUDIO087,AUDIO088,AUDIO089,AUDIO090,AUDIO091,AUDIO092,AUDIO093,AUDIO094,AUDIO095,AUDIO096,AUDIO097,AUDIO098,AUDIO099,AUDIO100,AUDIO101,AUDIO102,AUDIO103,AUDIO104,AUDIO105,AUDIO106,AUDIO107,AUDIO108,AUDIO109,AUDIO110,AUDIO111,AUDIO112,AUDIO113,AUDIO114,AUDIO115,AUDIO116,AUDIO117,AUDIO118,AUDIO119,AUDIO120,AUDIO121,AUDIO122,AUDIO123,AUDIO124,AUDIO125,AUDIO126,AUDIO127,FFT00,FFT01,FFT02,FFT03,FFT04,FFT05,FFT06,FFT07,FFT08,FFT09,FFT10,FFT11,FFT12,FFT13,FFT14,FFT15,FFT16,FFT17,FFT18,FFT19,FFT20,FFT21,FFT22,FFT23,FFT24,FFT25,FFT26,FFT27,FFT28,FFT29,FFT30,FFT31,FFT32,FFT33,FFT34,FFT35,FFT36,FFT37,FFT38,FFT39,FFT40,FFT41,FFT42,FFT43,FFT44,FFT45,FFT46,FFT47,FFT48,FFT49,FFT50,FFT51,FFT52,FFT53,FFT54,FFT55,FFT56,FFT57,FFT58,FFT59,FFT60,FFT61,FFT62,FFT63) " +sqlValues;
			//System.out.println(sql);
			stmt.executeUpdate(sql);

			/*sql = "INSERT INTO " + currentTableName + " (ID,NAME,AGE,ADDRESS,SALARY) " +
			      "VALUES (14, 'Allen', 25, 'Texas', 15000.00 );"; 
			stmt.executeUpdate(sql);

			sql = "INSERT INTO " + currentTableName + " (ID,NAME,AGE,ADDRESS,SALARY) " +
			      "VALUES (15, 'Teddy', 23, 'Norway', 20000.00 );"; 
			stmt.executeUpdate(sql);

			sql = "INSERT INTO " + currentTableName + " (ID,NAME,AGE,ADDRESS,SALARY) " +
			      "VALUES (16, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
			stmt.executeUpdate(sql);*/

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void commit(){
    	try {
			dbConnection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}