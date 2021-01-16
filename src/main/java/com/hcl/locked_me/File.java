package com.hcl.locked_me;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class File {

	private Path path;
	private String name;
	private FileTime fileTime;
	private int size;

	public File(Path path, String name, FileTime fileTime, int size) {
		this.path = path;
		this.fileTime = fileTime;
		this.name = name;
		this.size = size;
	}
	
	public File(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public Path getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public FileTime getFileTime() {
		return fileTime;
	}

	public int getSize() {
		return size;
	}
}
