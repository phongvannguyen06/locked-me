package com.hcl.locked_me;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		startApp();
	}

	public static void startApp() throws IOException {
		System.out.println("*******************************************************");
		System.out.println("	Welcome to LockedMe");
		System.out.println("	Developer: Phong Van Nguyen");
		System.out.println("*******************************************************");

		System.out.println("Enter a path to directory or press [Enter] to choose the current directory: ");
		String pathInput;
		while (!FileManager.isPathValid(pathInput = scanner.nextLine().replace("\\", File.separator)));

		if (pathInput.isEmpty()) {
			pathInput = Paths.get("").toAbsolutePath().toString();
		}

		FileManager fm = new FileManager(pathInput);

		fm.showCurrentPath();
		fm.showMainMenu();

		String commandExpression = "";
		while (!(commandExpression = scanner.nextLine()).equals("exit")) {
			fm.chooseAnOperation(commandExpression);
		}
		System.out.println("The program has been closed");
		System.out.println("Thank you for using it!");
	}

}
