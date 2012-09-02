/**
 * 
 */
package com.zazzercode.csv.io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.zazzercode.domain.Ride;
import com.zazzercode.domain.Suspension;

/**
 * @author prayag
 * @author Mark Reinhold @link BufferedWriter
 */
public class RideWriter implements CSVWriter<Ride> {
	private static String TAG = "RideWriter";

	@Override
	public void write(List<Ride> rides) {
		try {
			String date = (new Date()).toString();
			String filename = "Ride" + date + ".csv";
			System.out.println(TAG);
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(filename),
							"UTF-8"));
			for (Ride ride : rides) {

				StringBuffer oneLineStringBuffer = new StringBuffer();
				/**/
				oneLineStringBuffer.append("RIDE ");
				oneLineStringBuffer.append("\n");
				/**/
				oneLineStringBuffer.append(ride.getId() <= 0 ? "" : ride
						.getId());
				oneLineStringBuffer.append(CSV_SEPARATOR);

				/**/
				oneLineStringBuffer
						.append(ride.getRideName().trim().length() == 0 ? ""
								: ride.getRideName());
				oneLineStringBuffer.append(CSV_SEPARATOR);
				/**/

				oneLineStringBuffer.append("\n");
				oneLineStringBuffer.append("SETTINGS");
				oneLineStringBuffer.append("\n");
				for (Suspension suspension : ride.getSuspensions()) {
					oneLineStringBuffer.append(suspension.getId() <= 0 ? ""
							: suspension.getId());
					oneLineStringBuffer.append(CSV_SEPARATOR);

					/**/
					// oneLineStringBuffer.append(ride.getRideName().trim().length()
					// == 0 ? ""
					// : ride.getRideName());
					// oneLineStringBuffer.append(CSV_SEPARATOR);
					/**/
					oneLineStringBuffer.append("\n");
					// bufferedWriter.newLine();
				}
				bufferedWriter.write(oneLineStringBuffer.toString());
				System.out.println(TAG);
			}

			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException ioException) {
			System.out.println(ioException.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
