package com.warehouse.inventorymanagement.model;

import java.util.List;

public class Response {


private String message;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public List getData() {
	return data;
}
public void setData(List data) {
	this.data = data;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
private List data;
private int code;
}
