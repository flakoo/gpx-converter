package org.alpacology.gpx.converter;

import org.springframework.stereotype.Service;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

@Service
public class EpilogueWriter {
	public void writeEpilogue(XMLStreamWriter streamWriter) throws XMLStreamException {
//		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		/*streamWriter.writeEndElement(
				"",
				"gpx",
				"http://www.topografix.com/GPX/1/1"
		);*/
		streamWriter.writeEndDocument();
	}
}
