package com.hcl.locked_me;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

/**
 * add, delete, or search for a file
 * 
 * @author Phong Van Nguyen
 *
 */
public class BusinessOperation {

	private List<FileClass> files;
	private String path;

	public BusinessOperation(List<FileClass> files, String path) {
		this.files = files;
		this.path = path;
	}

	public void addFile(String name) {
		try {
			Files.createFile(Paths.get(path, name));
			System.out.printf("\n[%s] has been added to the directory.\n", name);
		} catch (InvalidPathException e) {
			System.out.println("Invalid File Name.  Type \"help vmo\" to view the manual\n");
		} catch (UnsupportedOperationException e) {
			System.out.println("Unsupported operation. Type \"help vmo\" to view the manual\n");
		} catch (FileAlreadyExistsException e) {
			System.out.printf("\n[%s] already exists! Type \"help vmo\" to view the manual\n", name);
		} catch (IOException e) {
			System.out.println("Invalid input. Type \"help vmo\" to view the manual");
		}
	}

	public void deleteFile(String name) {
		try {
			Files.delete(Paths.get(path, name));
			System.out.printf("\n[%s] has been deleted!\n", name);
		} catch (InvalidPathException e) {
			System.out.println("Invalid File Name. Type \"help vmo\" to view the manual");
		} catch (NoSuchFileException e) {
			System.out.printf("\n[%s] doesn't exist. Type \"help vmo\" to view the manual\n", name);
		} catch (DirectoryNotEmptyException e) {
			System.out.printf("\nUnable to delete. [%s] is not an empty directory!\n", name);
		} catch (IOException e) {
			System.out.println("Invalid input. Type \"help vmo\" to view the manual\n");
		}
	}

	public void searchFile(String name) {
		for (FileClass file : files) {
			if (name.equalsIgnoreCase(file.getName())) {
				System.out.printf("\nFile found successfully [%s]!\n", name);
				return;
			}
		}
		System.out.printf("\nFile doesn't exist [%s].\n", name);
	}

	public static void printBusinessOperationManual() {
		System.out.println("******* Manual Page For vmo *******");
		System.out.println("\nvmo");
		System.out.println("***list all the available business operations.");

		System.out.println("\nadd filename.filetype");
		System.out.println("***add a file to the existing directory list.");

		System.out.println("\ndelete filename.filetype");
		System.out.println("***list all files\' names (sorted by date modified in an ascending order)");

		System.out.println("\nsearch filename.filetype");
		System.out.println("***search a user specified file from the main directory");

		System.out.println("\nmain");
		System.out.println("***view the main menu\n");
	}

}
