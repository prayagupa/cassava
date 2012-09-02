/**
 * 
 */
package com.zazzercode.patterncompile;

/**
 * StringTokenizer not used because is mentioned as a legacy class in api docs
 * 
 * @author prayag
 * 
 */
public class PatternCompileService {

	public static String getFileName() {
		String src = "Resources/Dumps/remit.xls";
		String regex = "/";
		// Pattern pattern = Pattern.compile(regex);
		// Matcher matcher = pattern.matcher(src);
		// if (matcher.matches()) {
		// return matcher.group();
		// }
		String[] tokens = src.split(regex);
		for (String s : tokens) {
			if (s.contains(".xls")) {
				return s;
			}
		}
		return null;
	}
}
