package org.alpacology.gpx.converter.model.garmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GarminExtensions {

	@XmlElement(name = "gpxtpx:TrackPointExtension")
	private GarminTrackPointExtension garminTrackPointExtension;

	public GarminTrackPointExtension getGarminTrackPointExtension() {
		return garminTrackPointExtension;
	}

	public void setGarminTrackPointExtension(GarminTrackPointExtension garminTrackPointExtension) {
		this.garminTrackPointExtension = garminTrackPointExtension;
	}
}
