/**
 * 
 */
package com.zazzercode.csv.io;

import java.util.List;

/**
 * @author prayag
 * 
 */
public interface CSVWriter<T> {
	static final String CSV_SEPARATOR = ",";

	public void write(List<T> list);
}
