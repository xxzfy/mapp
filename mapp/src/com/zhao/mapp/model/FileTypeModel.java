package com.zhao.mapp.model;

public class FileTypeModel {
	private int type;
	private String filename;
	private String filepath;
	
	public FileTypeModel() {
		// TODO Auto-generated constructor stub
	}

	public FileTypeModel(int type, String filename, String filepath) {
		super();
		this.type = type;
		this.filename = filename;
		this.filepath = filepath;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
