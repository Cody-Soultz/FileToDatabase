package myCustomDataBaseSolution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.stream.Stream;

/**
 * @author Cody this allows for the program to read from a file
 */
public class ProcessFile {
	/**
	 * file path to use
	 */
	private String file = "";
	String penName=null;
	private DatabaseHandler myHandler= new DatabaseHandler();
	private String line=null;
	private int lineOffsetAudioArray=6;
	private int lineSpaceBetweenUpperAndLowerAudioArray=128;
	private int lineOffsetFFT=262;
	private String previousSecond="00";
	private StringBuilder currentSecond=new StringBuilder("00");
	private int indexOfLastFour=0;
	
	public ProcessFile(String tableName) throws FileNotFoundException, UnsupportedEncodingException {
		penName=tableName;
		myHandler.addTable();
	}

	public void ProcessSingleFile(String file) {
		System.out.println(file);
		Path path = Paths.get(file);
		this.file = path.getFileName().toString();
		try (Stream<String> lines = Files.lines(path)) {
			lines.forEachOrdered(line -> ProcessLine(line));
		} catch (IOException e) {
			// error happened
		}
	}

	private void ProcessLine(String line) {
		try {
			if(line.length()>300){
				format1(line);
			}
			else{
				otherFormat(line);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			file=null;
		}
	}

	private void format1(String line) throws SQLException
	{
		//currentSecond.(Character.toString(line.charAt(0)));
		//currentSecond.concat(Character.toString(line.charAt(1)));
		currentSecond.setCharAt(0, line.charAt(0));
		currentSecond.setCharAt(1, line.charAt(1));
		if(!previousSecond.equals(currentSecond.toString()))
		{
			indexOfLastFour=0;
			previousSecond=currentSecond.toString();
		}
		this.line=line;
		StringBuilder sqlValues= new StringBuilder("VALUES (");
		sqlValues.append(penName);//penname first 4 numbers of primary key
		sqlValues.append(file.charAt(0));//month next 2 numbers of primary key
		sqlValues.append(file.charAt(1));
		sqlValues.append(file.charAt(2));//day next 2 numbers of primary key
		sqlValues.append(file.charAt(3));
		sqlValues.append(file.charAt(4));//hour next 2 numbers of primary key
		sqlValues.append(file.charAt(5));
		sqlValues.append(file.charAt(6));//minute next 2 numbers of primary key
		sqlValues.append(file.charAt(7));
		sqlValues.append(currentSecond);//second next 2 numbers of primary key
		//sqlValues.append(Integer.toString(indexOfLastFour));//last 4 of primary key
		sqlValues.append(String.format("%04d",indexOfLastFour));//last 4 of primary key
		sqlValues.append(",");
		sqlValues.append(line.charAt(2));//F1
		sqlValues.append(",");
		sqlValues.append(line.charAt(3));//F2
		sqlValues.append(",");
		sqlValues.append(line.charAt(4));//F3
		sqlValues.append(",");
		sqlValues.append(line.charAt(5));//F4
		
		for(int i=0;i<128;i++){
			sqlValues.append(",");
			sqlValues.append(AudioArray(i));//AUDIO000-AUDIO127
		}
		for(int i=0;i<64;i++){
			sqlValues.append(",");
			sqlValues.append(FFTArray(i));//FFT00-FFT63
		}
		sqlValues.append(");");//end of sql string
		myHandler.addRowMain(sqlValues.toString());
		indexOfLastFour++;
	}
	
	private String FFTArray(int i) {
		return Integer.toString(((int)line.charAt(i+lineOffsetFFT)-32));
	}

	private String AudioArray(int audioIndex) {
		Integer value=0;
		char upperChar = line.charAt(audioIndex+lineOffsetAudioArray);
		char lowerChar = line.charAt(audioIndex+lineOffsetAudioArray+lineSpaceBetweenUpperAndLowerAudioArray);
		value = ((int)upperChar*64+(int)lowerChar)-2080;
		return value.toString();
	}

	private void otherFormat(String line) throws SQLException{
		StringBuilder otherValues= new StringBuilder("VALUES (NULL,");
		otherValues.append("'");
		otherValues.append(penName);//penname
		otherValues.append("'");
		otherValues.append(",");
		otherValues.append(file.charAt(0));//month
		otherValues.append(file.charAt(1));
		otherValues.append(",");
		otherValues.append(file.charAt(2));//day
		otherValues.append(file.charAt(3));
		otherValues.append(",");
		otherValues.append(file.charAt(4));//hour
		otherValues.append(file.charAt(5));
		otherValues.append(",");
		otherValues.append(file.charAt(6));//minute
		otherValues.append(file.charAt(7));
		otherValues.append(",");
		otherValues.append("\""+line+"\"");
		otherValues.append(");");//end of insert
		myHandler.addRowOther(otherValues.toString());
		//writer.println(otherValues);
	}
	
	public void ProcessMultibleFiles(Path path) throws IOException {
		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
		for (Path p : directoryStream)
			ProcessSingleFile(p.toString());
	}

	public void closeDB() {
		// TODO Auto-generated method stub
		myHandler.commit();
		myHandler.close();
		System.out.println("End of Processing");
	}
}
