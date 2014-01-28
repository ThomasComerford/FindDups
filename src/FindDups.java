
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class FindDups {
	
	private int fileCount = 0;
	private FileWriter reportFileWriter;
	HashMap<Long, List<FileInfo>> sizeTable = new HashMap<>();

	public static void main(String[] args) {
		
		FindDups myInstance = new FindDups();
		
		myInstance.findDuplicates(args);
		
	}
	
	public void findDuplicates(String args[]) {
		
		try {
			reportFileWriter = new FileWriter("Report.txt");
		} catch(IOException e) {
			System.err.println("Unable to write to report file.");
		}

		for(int i = 0;i < args.length;i++) {
			
			Path currentPath = Paths.get(args[i]);

			processFile(currentPath.toFile());

		
		}
		

		// Get keys from hashtable
		Set<Long> mapKeys = sizeTable.keySet();
		List<Long> sizeList = new ArrayList<>();
		
		for(long l : mapKeys) {
			sizeList.add(l);
		}

		// sort list
		Collections.sort(sizeList);

		// Create sorted list of filesizes

		while(!sizeList.isEmpty()) {
			long currentSize = sizeList.remove(sizeList.size() - 1);
			List<FileInfo> fis = sizeTable.get(currentSize);
			//xxxx   sizeTable.get(currentSize);
		}
		// for each key, get all values
			// for each FileInfo, get md5 hash
				// print list of matching sets to report file
		
		
		// search through data structure
		// if filesize is matching, check md5 hash

		try {
			reportFileWriter.flush();
			reportFileWriter.close();
		}
		catch(IOException e) {
			;
		}
	
	}
	

	private void processFile(File file) {
		
		File[] fileList = file.listFiles();
		
		for(File nextFile : fileList) {
			if(nextFile.isDirectory()) {
				processFile(nextFile);
			}
			else if(nextFile.isFile()) {
				long fileSize = nextFile.length();
				FileInfo nextFileInfo = new FileInfo();
				nextFileInfo.setPath(nextFile.getAbsolutePath());
				nextFileInfo.setModifiedTime(nextFile.lastModified());
				nextFileInfo.setFileSize(fileSize);

				if(sizeTable.containsKey(fileSize)) {
					List<FileInfo> fiList = sizeTable.get(fileSize);
					fiList.add(nextFileInfo);
				}
				else {
					List<File> fiList = new ArrayList<>();
					sizeTable.put(fiList);
				}

			}
			else {
				System.err.println("Other filetype encountered.");
			}
		}
		// Call processDirectory for all sub dirs
		// Handle all files in this directory
		
	}

}
