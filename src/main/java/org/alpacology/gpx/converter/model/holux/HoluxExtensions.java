package org.alpacology.gpx.converter.model.holux;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class HoluxExtensions {
	private String speed;

	@XmlElement(name = "temp")
	private String temperature;

	@XmlElement(name = "bpm")
	private Integer heartrate;

	@XmlElement(name = "rpm")
	private Float cadence;

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public Integer getHeartrate() {
		return heartrate;
	}

	public void setHeartrate(Integer heartrate) {
		this.heartrate = heartrate;
	}

	public Float getCadence() {
		return cadence;
	}

	public void setCadence(Float cadence) {
		this.cadence = cadence;
	}
}
