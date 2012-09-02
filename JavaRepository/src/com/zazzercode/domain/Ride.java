/**
 * 
 */
package com.zazzercode.domain;

import java.util.List;

/**
 * @author prayag
 * 
 */
public class Ride {
	private int id;
	private String rideName;
	private List<Suspension> suspensions;

	public int getId() {
		return id;
	}

	public String getRideName() {
		return rideName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRideName(String rideName) {
		this.rideName = rideName;
	}

	public List<Suspension> getSuspensions() {
		return suspensions;
	}

	public void setSuspensions(List<Suspension> suspensions) {
		this.suspensions = suspensions;
	}

}
