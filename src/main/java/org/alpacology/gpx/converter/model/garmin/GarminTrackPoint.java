package org.alpacology.gpx.converter.model.garmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class GarminTrackPoint {

	@XmlAttribute
	private Double lat;

	@XmlAttribute(name = "lon")
	private Double lng;

	@XmlElement(name = "ele")
	private Double elevation;

	private XMLGregorianCalendar time;

	private GarminExtensions extensions;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public XMLGregorianCalendar getTime() {
		return time;
	}

	public void setTime(XMLGregorianCalendar time) {
		this.time = time;
	}

	public GarminExtensions getExtensions() {
		return extensions;
	}

	public void setExtensions(GarminExtensions extensions) {
		this.extensions = extensions;
	}
}
