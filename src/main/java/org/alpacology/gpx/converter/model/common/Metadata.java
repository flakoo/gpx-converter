package org.alpacology.gpx.converter.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class Metadata {
	private Link link;

	private XMLGregorianCalendar time;

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public XMLGregorianCalendar getTime() {
		return time;
	}

	public void setTime(XMLGregorianCalendar time) {
		this.time = time;
	}
}
