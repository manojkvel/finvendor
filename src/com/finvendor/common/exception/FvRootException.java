package com.finvendor.common.exception;
 * Super Type of all Application checked excpetion (abstract class), To be sub
public abstract class FvRootException extends Exception implements Serializable {
	public FvRootException(final String message) {
	public FvRootException(final Throwable cause) {
	public FvRootException(final String message, final Throwable cause) {
	final public Exception getOriginalCause() {