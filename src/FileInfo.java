import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class FileInfo {

	private long modifiedTime = 0;
	private long fileSize = 0;
	private String path = "";
	private String md5Hash = "";
	
	public FileInfo() {
		
	}

	/*
	public boolean equals(FileInfo fi) {
		
		if(this.fileSize != fi.fileSize)
			return false;
		
		return false;

	}*/

	public void calculateMd5Hash() {

		if(path.length() == 0)
			return;

		try (InputStream is = Files.newInputStream(Paths.get(path))) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			DigestInputStream dis = new DigestInputStream(is, md);
			while(dis.read() != -1);
			md5Hash = new String(md.digest());
		}
		catch(IOException | NoSuchAlgorithmException e) {
			;
		}

		return;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(path);
		
		return str.toString();
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
