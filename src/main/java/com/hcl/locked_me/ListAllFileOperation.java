package com.hcl.locked_me;

import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * list all the files in the current directory with options
 * @author Phong Van Nguyen
 *
 */
public class ListAllFileOperation {

	private List<FileClass> files;

	public ListAllFileOperation(List<FileClass> files) {
		this.files = files;
	}

	private static void printFiles(FileClass file) {
		if (Files.isDirectory(file.getPath())) {
			System.out.println(file.getName() + " [D]  |  " + formatDateTime(file.getFileTime()) + "  |  "
					+ file.getSize() + " KB");
		} else {
			System.out.println(
					file.getName() + "  |  " + formatDateTime(file.getFileTime()) + "  |  " + file.getSize() + " KB");
		}
	}

	private static String formatDateTime(FileTime fileTime) {
		LocalDateTime localDateTime = fileTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return localDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a"));
	}

	public void listFileByName() {
		files.stream().sorted(new sortByName()).forEach(ListAllFileOperation::printFiles);
	}

	public void listFileByNameReverse() {
		files.stream().sorted(new sortByName().reversed()).forEach(ListAllFileOperation::printFiles);
	}

	public void listFileByTime() {
		files.stream().sorted(new sortByTime()).forEach(ListAllFileOperation::printFiles);
	}

	public void listFileByTimeReverse() {
		files.stream().sorted(new sortByTime().reversed()).forEach(ListAllFileOperation::printFiles);
	}

	public void listFileBySize() {
		files.stream().sorted(new sortBySize()).forEach(ListAllFileOperation::printFiles);
	}

	public void listFileBySizeReverse() {
		files.stream().sorted(new sortBySize().reversed()).forEach(ListAllFileOperation::printFiles);
	}

	private class sortByName implements Comparator<FileClass> {
		@Override
		public int compare(FileClass f1, FileClass f2) {
			return f1.getName().compareToIgnoreCase(f2.getName());
		}
	}

	private class sortByTime implements Comparator<FileClass> {
		@Override
		public int compare(FileClass f1, FileClass f2) {
			return f1.getFileTime().compareTo(f2.getFileTime());
		}

	}

	private class sortBySize implements Comparator<FileClass> {
		@Override
		public int compare(FileClass f1, FileClass f2) {
			return f1.getSize() - f2.getSize();
		}
	}

	public static void printGFNManual() {
		System.out.println("******* Manual Page For gfn *******");
		System.out.println("[D] next to a name means it's a directory");
		
		System.out.println("\ngfn");
		System.out.println("***list all files\' names (default, sorted by names in an ascending order)");

		System.out.println("\ngfn -rv");
		System.out.println("***list all files\' names (sorted by names in a reversed order)");

		System.out.println("\ngfn -datecreated");
		System.out.println("***list all files\' names (sorted by date modified in an ascending order)");

		System.out.println("\ngfn -datecreated -rv");
		System.out.println("***list all files\' names (sorted by dates modified in a reversed order)");

		System.out.println("\ngfn -size");
		System.out.println("***list all files\' names (sorted by size in an ascending order)");

		System.out.println("\ngfn -size -rv");
		System.out.println("***list all files\' names (sorted by size in a reversed order)");
	}
}
