
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

	
	public long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMd5Hash() {
		return md5Hash;
	}

	public void setMd5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
	}
	
}
