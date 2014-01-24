
public class FileInfo {
	
	private long modifiedTime = 0;
	private long fileSize = 0;
	private String path = "";
	private String md5Hash = "";
	
	public FileInfo() {
		
	}

	public boolean equals(FileInfo fi) {
		
		if(this.fileSize != fi.fileSize)
			return false;
		
		return false;

	}

}
