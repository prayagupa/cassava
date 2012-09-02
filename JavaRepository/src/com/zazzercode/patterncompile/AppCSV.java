/**
 * 
 */
package com.zazzercode.patterncompile;

import java.util.ArrayList;
import java.util.List;

import com.zazzercode.csv.io.RideWriter;
import com.zazzercode.domain.Ride;
import com.zazzercode.domain.Suspension;

/**
 * @author prayag
 * 
 */
public class AppCSV {
	private static String TAG = "AppCSV";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Ride> rides = new ArrayList<Ride>();
		List<Suspension> suspensions = new ArrayList<Suspension>();

		for (int j = 1; j <= 2; j++) {
			Suspension suspension = new Suspension();
			suspension.setId(j);
			suspensions.add(suspension);
		}
		for (int i = 1; i <= 2; i++) {
			Ride ride = new Ride();
			ride.setId(i);
			ride.setRideName("YAMAHA");
			ride.setSuspensions(suspensions);
			rides.add(ride);
		}
		System.out.println(TAG + " - BEFORE");
		RideWriter rideExporter = new RideWriter();
		rideExporter.write(rides);
		System.out.println(TAG + " - AFTER");

	}
}
