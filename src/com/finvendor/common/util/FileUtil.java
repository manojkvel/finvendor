package com.finvendor.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * File Utility class
 * 
 * @author ayush
 * @since 07-Jan-2018
 */
public final class FileUtil {
	public static final long FIXED_FILE_SIZE_OF_10_MB = 10L * 1024L * 1024L; // 10MB

	public enum FileSizeEnum {
		MB
	}

	// forbidden instantiation
	private FileUtil() {
	}

	public static void writeByteArrayToFile(String destinationPath, byte[] bytes) throws IOException {
		File file = new File(destinationPath);
		FileUtils.writeByteArrayToFile(file, bytes);
	}

	public static void deleteFile(String path) throws IOException {
		// Delete if report file exists
		File fileTemp = new File(path);
		if (fileTemp.exists()) {
			fileTemp.delete();
		}

	}

	public static void validateFileSize(long threshold, long fileSize) throws Exception {
		if (fileSize > threshold) {
			// TBD Need to be localized and get it from ENUM
			throw new Exception("Research report offering file size must not be more than 10 MB");
		}
	}

	public static void validateEmptyFile(CommonsMultipartFile multiPartFile) throws Exception {
		if (multiPartFile == null) {
			// TBD Need to be localized and get it from ENUM
			throw new Exception("Research report offering file is empty");
		}
	}

	public static double getFileSize(FileSizeEnum enums, double bytes) {
		double fileSize = 0.0D;
		switch (enums) {
		case MB:
			double kilobytes = (bytes / 1024);
			double megabytes = (kilobytes / 1024);
			fileSize = megabytes;
			break;
		}
		return fileSize;
	}

	public static byte[] readBytesFromFile(String filePath) throws Exception {
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;
		try {
			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];

			// read file into bytes[]
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);

		} catch (IOException e) {
			throw new Exception("Unable to convert file to byte array!",e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					throw new Exception("Unable to close IO Stream!",e);
				}
			}
		}
		return bytesArray;
	}
}
