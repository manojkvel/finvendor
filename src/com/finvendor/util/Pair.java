package com.finvendor.util;
/**
 * Holder of pair of object
 * 
 * @author ayush
 * @since 15-Jan-2018
 */
public class Pair<E1, E2> {
	private E1 element1;
	private E2 element2;

	public Pair(E1 element1, E2 element2) {
		super();
		this.element1 = element1;
		this.element2 = element2;
	}

	public E1 getElement1() {
		return element1;
	}

	public void setElement1(E1 element1) {
		this.element1 = element1;
	}

	public E2 getElement2() {
		return element2;
	}

	public void setElement2(E2 element2) {
		this.element2 = element2;
	}
}
