package com.hcl.locked_me;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class FileClass {

	private Path path;
	private String name;
	private FileTime creationTime;
	private int size;

	public FileClass(Path path, String name, FileTime creationTime, int size) {
		this.path = path;
		this.creationTime = creationTime;
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
		return creationTime;
	}

	public int getSize() {
		return size;
	}
}
