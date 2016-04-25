package myCustomDataBaseSolution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Cody this allows for the program to read from a file
 */
public class ProcessFile {
	/**
	 * file path to use
	 */
	private String file = "";
	String pinName=null;
	private DatabaseHandler myHandler= new DatabaseHandler();
	private PrintWriter writer=null;
	private String line=null;
	private int lineOffsetAudioArray=6;
	private int lineSpaceBetweenUpperAndLowerAudioArray=128;
	private int lineOffsetFFT=262;
	
	public ProcessFile(String tableName) throws FileNotFoundException, UnsupportedEncodingException {
		pinName=tableName;
		myHandler.addTable(tableName);
		writer = new PrintWriter("database/otherText.txt", "UTF-8");
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
		if(line.length()>300){
			format1(line);
		}
		else{
			otherFormat(line);
		}
	}

	private void format1(String line)
	{
		this.line=line;
		StringBuilder sqlValues= new StringBuilder("VALUES (");
		sqlValues.append("'");
		sqlValues.append(pinName);//pinname
		sqlValues.append("'");
		sqlValues.append(",");
		sqlValues.append(file.charAt(0));//month
		sqlValues.append(file.charAt(1));
		sqlValues.append(",");
		sqlValues.append(file.charAt(2));//day
		sqlValues.append(file.charAt(3));
		sqlValues.append(",");
		sqlValues.append(file.charAt(4));//hour
		sqlValues.append(file.charAt(5));
		sqlValues.append(",");
		sqlValues.append(file.charAt(6));//minute
		sqlValues.append(file.charAt(7));
		sqlValues.append(",");
		sqlValues.append(line.charAt(0));//second
		sqlValues.append(line.charAt(1));
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
		myHandler.addRow(sqlValues.toString());
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

	private void otherFormat(String line){
		StringBuilder otherValues= new StringBuilder();
		otherValues.append(pinName);//pinname
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
		otherValues.append(line);
		writer.println(otherValues);
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
		writer.close();
		System.out.println("End of Processing");
	}
}
