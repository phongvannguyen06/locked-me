package com.hcl.locked_me;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		
		startApp();
		
	}

	public static void startApp() throws IOException {
		System.out.println("*******************************************************");
		System.out.println("	Welcome to LockedMe");
		System.out.println("	Developer: Phong Van Nguyen");
		System.out.println("*******************************************************");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a path to directory or press [Enter] to choose the current directory: ");
		String path;
		// makes sure the String path is valid
		while (!FileManager.isPathValid(path = scanner.nextLine().replace("\\", File.separator)));

		// if no path is given, then use the current directory
		if (path.isEmpty()) {
			path = Paths.get("").toAbsolutePath().toString();
		}

		FileManager fileManager = new FileManager(path);

		fileManager.showCurrentPath();
		fileManager.showMainMenu();

		// keep running the application, until the user enter "exit"
		String commandExpression = "";
		while (!(commandExpression = scanner.nextLine()).equals("exit")) {
			fileManager.chooseAnOperationType(commandExpression);
		}
		
		scanner.close();
		System.out.println("The program has been closed");
		System.out.println("Thank you for using it!");
	}

}
