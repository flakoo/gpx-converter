package org.alpacology.gpx.converter.model.garmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GarminTrackPointExtension {
	@XmlElement(name = "gpxtpx:hr")
	private Integer heartrate;

	@XmlElement( name = "gpxtpx:cad")
	private Integer cadence;

	public Integer getHeartrate() {
		return heartrate;
	}

	public void setHeartrate(Integer heartrate) {
		this.heartrate = heartrate;
	}

	public Integer getCadence() {
		return cadence;
	}

	public void setCadence(Integer cadence) {
		this.cadence = cadence;
	}
}
