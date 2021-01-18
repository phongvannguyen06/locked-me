package com.hcl.locked_me;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * manage the FileClass objects
 * 
 * @author Phong Van Nguyen
 *
 */
public class FileManager {

	private List<FileClass> files;
	private String path;
	private ListAllFileOperation listOperation;
	private BusinessOperation businessOperation;

	/**
	 * A constructor that sets the path to the chosen directory and hold the files'
	 * properties.
	 * 
	 * @param path
	 * @throws IOException
	 */
	public FileManager(String path) throws IOException {
		this.path = path;
		populateTheList();
	}

	/**
	 * A method to make sure the user-entered path is valid
	 * 
	 * @param path
	 * @return boolean
	 */
	public static boolean isPathValid(String path) {
		try {
			Path pathtoDirectory = Paths.get(path);
			if (!Files.exists(pathtoDirectory)) {
				System.out.println("Path doesn't exist. Please try again!\n");
				return false;
			}
		} catch (InvalidPathException ex) {
			System.out.println("Invalid path input. Please try again!\n");
			return false;
		}
		return true;
	}

	/**
	 * It gets all the files from the user-entered path and populates the variable
	 * files with the properties specified in the FileClass.java
	 * 
	 * @throws IOException
	 */
	private void populateTheList() throws IOException {
		files = new ArrayList<FileClass>();
		Stream<Path> fileStreamPath = Files.list(Paths.get(this.path));

		Iterator<Path> iterator = fileStreamPath.iterator();
		while (iterator.hasNext()) {
			Path path = iterator.next();
			BasicFileAttributes bfa = Files.readAttributes(path, BasicFileAttributes.class);

			String name = path.getFileName().toString();
			int size = (int) (bfa.size() / 1000); // in KB

			files.add(new FileClass(path, name, bfa.creationTime(), size));
		}

		fileStreamPath.close();
	}

	/**
	 * shows the current path
	 */
	public void showCurrentPath() {
		System.out.println("Current Path:");
		System.out.printf("%s\n", path);
	}

	/**
	 * Choose the correct operation with the user-entered input
	 * 
	 * @param expression e.g. gfn -datecreated
	 * @throws IOException
	 */
	public void chooseAnOperationType(String expression) throws IOException {
		String operationType = expression.split(" ")[0];
		switch (operationType) {
		case "gfn":
			chooseAListingOperation(expression);
			break;
		case "vmo":
			int wordsNum = expression.split(" ").length;
			if (wordsNum > 1) {
				System.out.println("Invalid command. Please try again\n");
			}
			showBusinessOperationsMenu();
			break;
		case "add":
		case "delete":
		case "search":
		case "main":
			chooseABusinessOperation(expression);
			break;
		case "help":
			chooseAManual(expression);
			break;
		default:
			System.out.println("Invalid command. enter \"main\" to go back to the main menu\n");
		}
	}

	private void chooseAListingOperation(String listMethodName) throws IOException {
		populateTheList();

		listOperation = new ListAllFileOperation(this.files);

		System.out.println("\nName --- Date-Created --- Size");
		System.out.println("-------------------------------");

		switch (listMethodName) {
		case "gfn":
			listOperation.listFileByName();
			break;
		case "gfn -rv":
			listOperation.listFileByNameReverse();
			break;
		case "gfn -datecreated":
			listOperation.listFileByTime();
			break;
		case "gfn -datecreated -rv":
			listOperation.listFileByTimeReverse();
			break;
		case "gfn -size":
			listOperation.listFileBySize();
			break;
		case "gfn -size -rv":
			listOperation.listFileBySizeReverse();
			break;
		default:
			System.out.println("Invalid gfn command. Please type \"help gfn\" to view the valid commands");
		}
	}

	private void chooseABusinessOperation(String businessOperationMethod) throws IOException {
		String[] expression = businessOperationMethod.split(" ");
		String method = expression[0];
		String fileName = "";
		if (expression.length > 1) {
			fileName = expression[1];
		}

		businessOperation = new BusinessOperation(files, this.path);

		switch (method) {
		case "add":
			businessOperation.addFile(fileName);
			populateTheList();
			break;
		case "delete":
			businessOperation.deleteFile(fileName);
			populateTheList();
			break;
		case "search":
			businessOperation.searchFile(fileName);
			break;
		case "main":
			showMainMenu();
			break;
		default:
			System.out.println("Invalid file operation. Please type \"help vmo\" to view the valid commands");
		}
	}

	public void chooseAManual(String expression) {
		switch (expression) {
		case "help gfn":
			ListAllFileOperation.printGFNManual();
			break;
		case "help vmo":
			BusinessOperation.printBusinessOperationManual();
			break;
		default:
			System.out.printf("invalid help operation: The format should be -> help [gfn OR vmo]\n", expression);
		}
	}

	public void showMainMenu() {
		System.out.println("\nChoose an option below. Type \"help [gfn OR vmo]\" to view the manual!");
		System.out.println("1. enter \"gfn\" get all files' names.");
		System.out.println("2. enter \"vmo\" to view more options.");
		System.out.println(
				"3. enter \"help [gfn OR vmo]\" to view the manual for that command. (RECOMMENDED for new users)");
		System.out.println("4. enter \"exit\" to close the application.");
	}

	private void showBusinessOperationsMenu() {
		System.out.println("\nPossible business operations");
		System.out.println("1. enter \"add\" to create a new file");
		System.out.println("2. enter \"delete\" to remove a file");
		System.out.println("3. enter \"search\" to search for a file");
		System.out.println("4. enter \"help vmo\" to learn how to use these operations");
		System.out.println("5. enter \"main\" to go back to the main menu\n");
	}
}
