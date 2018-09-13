/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.entities.Language;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class FileUtils {


	public static boolean saveToFile(MultipartFile mpf, String baseDir, String file) {
		File f = new File(baseDir, file);
		try {
			mpf.transferTo(f);
		} catch (IllegalStateException | IOException e1) {
			System.out.println(e1.getMessage());
			return false;
		}
		return true;
	}

	private static FilenameFilter modelSolutionFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return name.startsWith("Model");
		}
	};
	private static FilenameFilter inputGenFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return name.startsWith("Datagen");
		}
	};

	public static void deleteDirectory(File file) throws IOException {
		org.apache.commons.io.FileUtils.deleteDirectory(file);
	}

	public static void redirectStreams(InputStream is, OutputStream os) {
		try {
			IOUtils.copy(is, os);
			os.flush();
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
	}

	public static void crearArchivoComprimido(OutputStream zipStream, List<SubmissionJudge> solutions) {

		
		// Create a buffer for reading the files

		try {
			// Create the ZIP file
			java.util.zip.ZipOutputStream out = new java.util.zip.ZipOutputStream(zipStream);
			// Compress the files
			for (SubmissionJudge solution : solutions) {
				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(solution.getFilename()));
				// Transfer bytes from the file to the ZIP file
				out.write(solution.getCode().getBytes());
				out.closeEntry();
			}
			out.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void decompressFile(String zipfile, String dir, boolean erase) {
		try {
			ZipFile zipFile = new ZipFile(zipfile);
			zipFile.extractAll(dir);
			if (erase) {
				org.apache.commons.io.FileUtils.deleteQuietly(new File(zipfile));
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

}
