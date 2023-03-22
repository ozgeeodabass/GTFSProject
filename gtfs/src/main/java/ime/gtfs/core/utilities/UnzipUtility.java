package ime.gtfs.core.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipUtility {
	
	  private static final int BUFFER_SIZE = 4096;

	  
	  public static void unzip(String zipFilePath, String destDirectory) throws IOException{
		  File destDir = new File(destDirectory);
		  
		  if (!destDir.exists())
          {
              destDir.mkdir();
          }
		  
		  ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));
		  ZipEntry zipEntry = zipInputStream.getNextEntry();
		  
		  while(zipEntry!=null) {
			  String filePath = destDirectory + File.separator + zipEntry.getName();
			  
			  if (!zipEntry.isDirectory()) {
                  // if the entry is a file, extracts it
                  extractFile(zipInputStream, filePath);
              } else {
                  // if the entry is a directory, make the directory
                  File dir = new File(filePath);
                  dir.mkdir();
              }
			  
			  zipInputStream.closeEntry();
		  }
		  
		  zipInputStream.close();
		  
		  
		  
	    }


	private static void extractFile(ZipInputStream zipInputStream, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipInputStream.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
		
	}
}
