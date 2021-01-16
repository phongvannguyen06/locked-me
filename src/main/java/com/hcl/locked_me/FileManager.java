package com.hcl.locked_me;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileManager {

	List<File> files = new ArrayList<File>();

	public void add(File file) {
		files.add(file);
	}

	public void listFileByName() {
		System.out.println("Name --- Time --- Size");
		System.out.println("----------------------");
		files.stream()
			.sorted(new sortByName())
			.forEach(f -> System.out.println(f.getName() + " --- " + f.getSize()));
	}

	public void listFileByNameReverse() {
		System.out.println("Name --- Time --- Size");
		System.out.println("----------------------");
		files.stream()
			.sorted(new sortByName().reversed())
			.forEach(f -> System.out.println(f.getName() + " --- " + f.getSize()));
	}
	
	public void listFileByTime() {
		System.out.println("Name --- Time --- Size");
		System.out.println("----------------------");
		files.stream()
			.sorted(new sortByTime())
			.forEach(f -> System.out.println(f.getName() + " --- " + f.getFileTime() + " --- " + f.getSize()));
	}
	
	public void listFileByTimeReverse() {
		System.out.println("Name --- Time --- Size");
		System.out.println("----------------------");
		files.stream()
			.sorted(new sortByTime())
			.forEach(f -> System.out.println(f.getName() + " --- " + f.getFileTime() + " --- " + f.getSize()));
	}

	public void listFileBySize() {
		System.out.println("Name --- Time --- Size");
		System.out.println("----------------------");
		files.stream()
			.sorted(new sortBySize())
			.forEach(f -> System.out.println(f.getName() + " --- " + f.getSize()));
	}

	public void listFileBySizeReverse() {
		System.out.println("Name --- Time --- Size");
		System.out.println("----------------------");
		files.stream()
			.sorted(new sortBySize().reversed())
			.forEach(f -> System.out.println(f.getName() + " --- " + f.getSize()));
	}

	private class sortByName implements Comparator<File> {
		@Override
		public int compare(File f1, File f2) {
			return f1.getName().compareToIgnoreCase(f2.getName());
		}
	}

	private class sortByTime implements Comparator<File> {
		@Override
		public int compare(File f1, File f2) {
			return f1.getFileTime().compareTo(f2.getFileTime());
		}
		
	}
	
	private class sortBySize implements Comparator<File> {
		@Override
		public int compare(File f1, File f2) {
			return f1.getSize() - f2.getSize();
		}
	}
	

}
