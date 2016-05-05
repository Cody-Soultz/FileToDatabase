package myCustomDataBaseSolution;

import java.io.File;
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
	String sqlInsertStatmentMain=null;
	String sqlInsertStatmentOther=null;
	int totalNumOfElementsF=4;
	int digitsF=1;
	int totalNumOfElementsAudio=128;
	int digitsAudio=3;
	int totalNumOfElementsFFT=64;
	int digitsFFT=2;
	
    public DatabaseHandler() {
        try {
            Class.forName("org.sqlite.JDBC");
            makeDirIfNotExists("database");
            String dbURL = "jdbc:sqlite:database/dataSet.db";
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
    
    private void makeDirIfNotExists(String directoryName) {
		// TODO Auto-generated method stub
		File directory = new File(directoryName);
		if(!directory.exists()){
			System.out.println("Creating Directory " + directoryName);
			boolean result =false;
			
			try{
				directory.mkdirs();
				result=true;
			}
			catch(SecurityException se){
				se.printStackTrace();
			}
			if(result){
				System.out.println("DIR created");
			}
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
    
    public void addTableMain(String tableName){
    	try {
    		stmt = dbConnection.createStatement();
    		StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
    		sql.append(tableName);
    		sql.append("(PRIMARYKEY INTEGER PRIMARY KEY,");
    		sql.append("UNIQUEINDEX INTEGER NOT NULL,");
//    		sql.append("MONTH INTEGER NOT NULL,"); 
//    		sql.append("DAY INTEGER NOT NULL,");
//    		sql.append("HOUR INTEGER NOT NULL,");
//    		sql.append("MINUTE INTEGER NOT NULL,");
//    		sql.append("SECOND INTEGER NOT NULL,");
    		sql.append(appendCustom("F",totalNumOfElementsF,digitsF,"INTEGER"));
    		sql.append(",");
    		sql.append(appendCustom("AUDIO",totalNumOfElementsAudio,digitsAudio , "INTEGER"));
    		sql.append(",");
    		sql.append("AUDIOOFFSET TEXT NOT NULL");
    		sql.append(",");
    		sql.append("AUDIOAVERAGE TEXT NOT NULL");
    		sql.append(",");
    		sql.append(appendCustom("FFT",totalNumOfElementsFFT,digitsFFT,"INTEGER"));
    		sql.append(",SOWNAME TEXT");
    		sql.append(");");
    		
			//System.out.println(sql.toString());
			stmt.executeUpdate(sql.toString());
			stmt.close();
			stmt = dbConnection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	StringBuilder sql = new StringBuilder("INSERT INTO "); 
    	sql.append(tableName);
		sql.append("(PRIMARYKEY,");
		sql.append("UNIQUEINDEX,");
//		sql.append("MONTH,"); 
//		sql.append("DAY,");
//		sql.append("HOUR,");
//		sql.append("MINUTE,");
//		sql.append("SECOND,");
		sql.append(appendCustom("F",totalNumOfElementsF,digitsF));
		sql.append(",");
		sql.append(appendCustom("AUDIO",totalNumOfElementsAudio,digitsAudio));
		sql.append(",");
		sql.append("AUDIOOFFSET");
		sql.append(",");
		sql.append("AUDIOAVERAGE");
		sql.append(",");
		sql.append(appendCustom("FFT",totalNumOfElementsFFT,digitsFFT));
		sql.append(",SOWNAME");
		sql.append(") ");
    	sqlInsertStatmentMain=sql.toString();
    }
    
//    public void addTableOther(String tableName){
//    	try {
//    		stmt = dbConnection.createStatement();
//    		StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
//    		sql.append(tableName);
//    		sql.append("(PRIMARYKEY INTEGER PRIMARY KEY,");
//    		sql.append("PENNAME TEXT NOT NULL,");
//    		sql.append("MONTH INTEGER NOT NULL,"); 
//    		sql.append("DAY INTEGER NOT NULL,");
//    		sql.append("HOUR INTEGER NOT NULL,");
//    		sql.append("MINUTE INTEGER NOT NULL,");
//    		sql.append("LINE TEXT NOT NULL");
//    		sql.append(");");
//    		
//			//System.out.println(sql.toString());
//			stmt.executeUpdate(sql.toString());
//			stmt.close();
//			stmt = dbConnection.createStatement();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	StringBuilder sql = new StringBuilder("INSERT INTO "); 
//    	sql.append(tableName);
//		sql.append("(PRIMARYKEY,");
//		sql.append("PENNAME,");
//		sql.append("MONTH,"); 
//		sql.append("DAY,");
//		sql.append("HOUR,");
//		sql.append("MINUTE,");
//		sql.append("LINE");
//		sql.append(") ");
//    	sqlInsertStatmentOther=sql.toString();
//    }
    
    private Object appendCustom(String fieldName,int numElements, Integer digits) {
		// TODO Auto-generated method stub
    	StringBuilder returnString= new StringBuilder();
    	for (int i=0;i<numElements;i++){
    		returnString.append(fieldName);
    		returnString.append(String.format("%0"+digits.toString()+"d", i));
    		if(i!=numElements-1) returnString.append(",");
    	}
		return returnString.toString();
	}

	private String appendCustom(String fieldName,int numElements, Integer digits, String dataType) {
		// TODO Auto-generated method stub
    	StringBuilder returnString= new StringBuilder();
    	for (int i=0;i<numElements;i++){
    		returnString.append(fieldName);
    		returnString.append(String.format("%0"+digits.toString()+"d", i));
    		returnString.append(" "+dataType+" NOT NULL");
    		if(i!=numElements-1) returnString.append(",");
    	}
		return returnString.toString();
	}

	public void addRowMain(String sqlValues) throws SQLException{
		//stmt = dbConnection.createStatement();
		//String sql = "INSERT INTO " + currentTableName + " (PRIMARYKEY,PENNAME,MONTH,DAY,HOUR,MINUTE,SECOND,F0,F1,F2,F3,AUDIO000,AUDIO001,AUDIO002,AUDIO003,AUDIO004,AUDIO005,AUDIO006,AUDIO007,AUDIO008,AUDIO009,AUDIO010,AUDIO011,AUDIO012,AUDIO013,AUDIO014,AUDIO015,AUDIO016,AUDIO017,AUDIO018,AUDIO019,AUDIO020,AUDIO021,AUDIO022,AUDIO023,AUDIO024,AUDIO025,AUDIO026,AUDIO027,AUDIO028,AUDIO029,AUDIO030,AUDIO031,AUDIO032,AUDIO033,AUDIO034,AUDIO035,AUDIO036,AUDIO037,AUDIO038,AUDIO039,AUDIO040,AUDIO041,AUDIO042,AUDIO043,AUDIO044,AUDIO045,AUDIO046,AUDIO047,AUDIO048,AUDIO049,AUDIO050,AUDIO051,AUDIO052,AUDIO053,AUDIO054,AUDIO055,AUDIO056,AUDIO057,AUDIO058,AUDIO059,AUDIO060,AUDIO061,AUDIO062,AUDIO063,AUDIO064,AUDIO065,AUDIO066,AUDIO067,AUDIO068,AUDIO069,AUDIO070,AUDIO071,AUDIO072,AUDIO073,AUDIO074,AUDIO075,AUDIO076,AUDIO077,AUDIO078,AUDIO079,AUDIO080,AUDIO081,AUDIO082,AUDIO083,AUDIO084,AUDIO085,AUDIO086,AUDIO087,AUDIO088,AUDIO089,AUDIO090,AUDIO091,AUDIO092,AUDIO093,AUDIO094,AUDIO095,AUDIO096,AUDIO097,AUDIO098,AUDIO099,AUDIO100,AUDIO101,AUDIO102,AUDIO103,AUDIO104,AUDIO105,AUDIO106,AUDIO107,AUDIO108,AUDIO109,AUDIO110,AUDIO111,AUDIO112,AUDIO113,AUDIO114,AUDIO115,AUDIO116,AUDIO117,AUDIO118,AUDIO119,AUDIO120,AUDIO121,AUDIO122,AUDIO123,AUDIO124,AUDIO125,AUDIO126,AUDIO127,FFT00,FFT01,FFT02,FFT03,FFT04,FFT05,FFT06,FFT07,FFT08,FFT09,FFT10,FFT11,FFT12,FFT13,FFT14,FFT15,FFT16,FFT17,FFT18,FFT19,FFT20,FFT21,FFT22,FFT23,FFT24,FFT25,FFT26,FFT27,FFT28,FFT29,FFT30,FFT31,FFT32,FFT33,FFT34,FFT35,FFT36,FFT37,FFT38,FFT39,FFT40,FFT41,FFT42,FFT43,FFT44,FFT45,FFT46,FFT47,FFT48,FFT49,FFT50,FFT51,FFT52,FFT53,FFT54,FFT55,FFT56,FFT57,FFT58,FFT59,FFT60,FFT61,FFT62,FFT63) " +sqlValues;
		String sql = sqlInsertStatmentMain +sqlValues;
		//System.out.println(sql);
		stmt.executeUpdate(sql);
    }
    
//	public void addRowOther(String sqlValues) throws SQLException{
//		//stmt = dbConnection.createStatement();
//		//String sql = "INSERT INTO " + currentTableName + " (PRIMARYKEY,PENNAME,MONTH,DAY,HOUR,MINUTE,SECOND,F0,F1,F2,F3,AUDIO000,AUDIO001,AUDIO002,AUDIO003,AUDIO004,AUDIO005,AUDIO006,AUDIO007,AUDIO008,AUDIO009,AUDIO010,AUDIO011,AUDIO012,AUDIO013,AUDIO014,AUDIO015,AUDIO016,AUDIO017,AUDIO018,AUDIO019,AUDIO020,AUDIO021,AUDIO022,AUDIO023,AUDIO024,AUDIO025,AUDIO026,AUDIO027,AUDIO028,AUDIO029,AUDIO030,AUDIO031,AUDIO032,AUDIO033,AUDIO034,AUDIO035,AUDIO036,AUDIO037,AUDIO038,AUDIO039,AUDIO040,AUDIO041,AUDIO042,AUDIO043,AUDIO044,AUDIO045,AUDIO046,AUDIO047,AUDIO048,AUDIO049,AUDIO050,AUDIO051,AUDIO052,AUDIO053,AUDIO054,AUDIO055,AUDIO056,AUDIO057,AUDIO058,AUDIO059,AUDIO060,AUDIO061,AUDIO062,AUDIO063,AUDIO064,AUDIO065,AUDIO066,AUDIO067,AUDIO068,AUDIO069,AUDIO070,AUDIO071,AUDIO072,AUDIO073,AUDIO074,AUDIO075,AUDIO076,AUDIO077,AUDIO078,AUDIO079,AUDIO080,AUDIO081,AUDIO082,AUDIO083,AUDIO084,AUDIO085,AUDIO086,AUDIO087,AUDIO088,AUDIO089,AUDIO090,AUDIO091,AUDIO092,AUDIO093,AUDIO094,AUDIO095,AUDIO096,AUDIO097,AUDIO098,AUDIO099,AUDIO100,AUDIO101,AUDIO102,AUDIO103,AUDIO104,AUDIO105,AUDIO106,AUDIO107,AUDIO108,AUDIO109,AUDIO110,AUDIO111,AUDIO112,AUDIO113,AUDIO114,AUDIO115,AUDIO116,AUDIO117,AUDIO118,AUDIO119,AUDIO120,AUDIO121,AUDIO122,AUDIO123,AUDIO124,AUDIO125,AUDIO126,AUDIO127,FFT00,FFT01,FFT02,FFT03,FFT04,FFT05,FFT06,FFT07,FFT08,FFT09,FFT10,FFT11,FFT12,FFT13,FFT14,FFT15,FFT16,FFT17,FFT18,FFT19,FFT20,FFT21,FFT22,FFT23,FFT24,FFT25,FFT26,FFT27,FFT28,FFT29,FFT30,FFT31,FFT32,FFT33,FFT34,FFT35,FFT36,FFT37,FFT38,FFT39,FFT40,FFT41,FFT42,FFT43,FFT44,FFT45,FFT46,FFT47,FFT48,FFT49,FFT50,FFT51,FFT52,FFT53,FFT54,FFT55,FFT56,FFT57,FFT58,FFT59,FFT60,FFT61,FFT62,FFT63) " +sqlValues;
//		String sql = sqlInsertStatmentOther +sqlValues;
//		//System.out.println(sql);
//		stmt.executeUpdate(sql);
//    }
	
	public void commit(){
    	try {
    		stmt.close();
			dbConnection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void addTable() {
		addTableMain("Main");
		//addTableOther("OtherData");
	}
    
}