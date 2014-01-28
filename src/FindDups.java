
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

		// sort list, and reverse order
		Collections.sort(sizeList);
		Collections.reverse(sizeList);

		try {

			while(!sizeList.isEmpty()) {
				long currentSize = sizeList.remove(0);
				List<FileInfo> fis = sizeTable.get(currentSize);
	
				if(fis.size() > 1) {
					for(FileInfo next : fis) {
						next.calculateMd5Hash();
					}
					
					while(!fis.isEmpty()) {
						ArrayList<FileInfo> dupList = new ArrayList<>();
						String searchHash = fis.get(0).getMd5Hash();
						dupList.add(fis.remove(0));
						for(FileInfo entry : fis) {
							if(entry.getMd5Hash().equals(searchHash)) {
								dupList.add(entry);
							}
						}

						if(dupList.size() > 1) {
							reportFileWriter.write("File size:" + currentSize + "\n");
							for(FileInfo entry : dupList) {
								reportFileWriter.write(entry.toString() + "\n");
							}
							reportFileWriter.write("\n");
						}
					}
				}
			}

			reportFileWriter.write("Total files processed:" + fileCount);

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
				fileCount++;
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
					List<FileInfo> fiList = new ArrayList<>();
					fiList.add(nextFileInfo);
					sizeTable.put(fileSize, fiList);
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
