package org.alpacology.gpx.converter;

import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

@Service
public class PrologueWriter {
	public void writePrologue(XMLStreamWriter streamWriter) throws XMLStreamException {
		startDocument(streamWriter);
		addGpxElement(streamWriter);
	}

	private void addGpxElement(XMLStreamWriter streamWriter) throws XMLStreamException {
		streamWriter.writeStartElement(
				"",
				"gpx",
				"http://www.topografix.com/GPX/1/1"
		);

		addGpxAttributes(streamWriter);
		addGpxNamespaces(streamWriter);
	}

	private void addGpxNamespaces(XMLStreamWriter streamWriter) throws XMLStreamException {
		streamWriter.writeNamespace("", "http://www.topografix.com/GPX/1/1");
		streamWriter.writeNamespace("gpxtpx", "http://www.garmin.com/xmlschemas/TrackPointExtension/v1");
		streamWriter.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
	}

	private void addGpxAttributes(XMLStreamWriter streamWriter) throws XMLStreamException {
		streamWriter.writeAttribute("version", "1.1");
		streamWriter.writeAttribute("creator", "Holux - Garmin converter");
		streamWriter.writeAttribute(
				"xsi",
				"http://www.w3.org/2001/XMLSchema-instance",
				"schemaLocation",
				"http://www.topografix.com/GPX/1/1 " +
						"http://www.topografix.com/GPX/1/1/gpx.xsd " +
						"http://www.garmin.com/xmlschemas/TrackPointExtension/v1 " +
						"http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd");
	}

	private void startDocument(XMLStreamWriter streamWriter) throws XMLStreamException {
		streamWriter.writeStartDocument();
	}
}
