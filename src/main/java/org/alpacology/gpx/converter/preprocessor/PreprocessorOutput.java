package org.alpacology.gpx.converter.preprocessor;

import org.alpacology.gpx.converter.model.garmin.GarminTrackPoint;

public class PreprocessorOutput extends GarminTrackPoint {
	private boolean hasHeartRate = false;
	private boolean hasCadence = false;

	public boolean isHasHeartRate() {
		return hasHeartRate;
	}

	public void setHasHeartRate(boolean hasHeartRate) {
		this.hasHeartRate = hasHeartRate;
	}

	public boolean isHasCadence() {
		return hasCadence;
	}

	public void setHasCadence(boolean hasCadence) {
		this.hasCadence = hasCadence;
	}
}
