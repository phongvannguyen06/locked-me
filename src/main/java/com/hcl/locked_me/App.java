package com.hcl.locked_me;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {

	static String path = "C:\\Users\\phong\\Downloads\\eclipse-jee-2020-12-R-win32-x86_64\\eclipse";

	static void printGFNManual() {
		System.out.println("******* Manual Page For gfn *******");
		System.out.println("\ngfn");
		System.out.println("***list all files\' names (default, sorted by names in an ascending order)");

		System.out.println("\ngfn -rv");
		System.out.println("***list all files\' names (sorted by names in a reversed order)");

		System.out.println("\ngfn -datemodified");
		System.out.println("***list all files\' names (sorted by date modified in an ascending order)");

		System.out.println("\ngfn -datemodified -rv");
		System.out.println("***list all files\' names (sorted by dates modified in a reversed order)");

		System.out.println("\ngfn -size");
		System.out.println("***list all files\' names (sorted by size in an ascending order)");

		System.out.println("\ngfn -size -rv");
		System.out.println("***list all files\' names (sorted by size in a reversed order)");
	}

	public static void main(String[] args) throws IOException {
		FileManager fm = new FileManager();

		Stream<Path> walk = Files.list(Paths.get("C:\\Program Files\\MongoDB\\Server\\4.2"));

		Iterator<Path> iterator = walk.iterator();
		while (iterator.hasNext()) {
			Path path = iterator.next();
			BasicFileAttributes bfa = Files.readAttributes(path, BasicFileAttributes.class);
			
			String name = path.getFileName().toString();
			int size = (int)(bfa.size() / 1000);
			
			fm.add(new File(path, name, bfa.lastModifiedTime(), size));
		}

		fm.listFileByTimeReverse();
	}

	static void fake() {
		System.out.println("welcome page");
		System.out.println("developer: Phong van nguyen");

		System.out.println();
		System.out.println("1. enter \"gfn\" get all files' names.");
		System.out.println("2. enter \"vmo\" to view more options.");
		System.out.println("3. enter \"help\" to view the manual. (RECOMMENDED for new users)");
		System.out.println("4. enter \"exit\" to close the application.");

		System.out.println("\nFiles sorted by names in ascending order");
		System.out.println("file1.txt");
		System.out.println("file2.txt");
		System.out.println("file3.txt");
		System.out.println("file4.txt");
		System.out.println("file5.txt");
		System.out.println("file6.txt");
		System.out.println("file7.txt");

		System.out.println("\nPossible file options");
		System.out.println("1. enter \"add\" to create a new file");
		System.out.println("2. enter \"rm\" to remove a file");
		System.out.println("3. enter \"search\" to search for a file");
		System.out.println("4. enter \"vmo -help\" to learn how to use these operations");
		System.out.println("5. enter \"return\" to go back to the main menu");
	}

	static void addFeature() throws IOException {
		Stream<Path> files = Files.list(Paths.get(path));
		List<String> fileNames = files.map(x -> x.getFileName().toString()).collect(Collectors.toList());
		Collections.sort(fileNames, String.CASE_INSENSITIVE_ORDER);
		fileNames.forEach(x -> System.out.println(x));

	}
}
