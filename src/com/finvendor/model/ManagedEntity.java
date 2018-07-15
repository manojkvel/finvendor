package com.finvendor.model;

import java.io.Serializable;

//@MappedSuperclass
public abstract class ManagedEntity implements Serializable {

	private static final long serialVersionUID = 1116006216286956801L;
	// @Id
	// @GeneratedValue
	//
	// @Column(name = "id")
	// protected String id;
	//
	// public ManagedEntity() {
	// super();
	// }
	//
	// public String getId() {
	// return id;
	// }
	//
	// public void setId(String id) {
	// this.id = id;
	// }

}
