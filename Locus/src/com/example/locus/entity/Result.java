package com.example.locus.entity;

public class Result {
	public static Result Success = new Result(true, 0);
	
	private boolean isSuccessful;
	private int errorCode;
	
	public Result(boolean isSuccessful, int errorCode) {
		this.isSuccessful = isSuccessful;
		this.errorCode = errorCode;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "Result [isSuccessful=" + isSuccessful + ", errorCode="
				+ errorCode + "]";
	}
}
