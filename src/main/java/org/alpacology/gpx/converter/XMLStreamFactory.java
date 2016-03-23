package org.alpacology.gpx.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.*;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.IOException;

public class XMLStreamFactory {
	private static Logger LOGGER = LoggerFactory.getLogger(XMLStreamFactory.class);

	public static final String OUTPUT_FILE = "E:/out.xml";
	public static final String INPUT_FILE = "src/main/resources/static/test_holux.gpx";

	public static XMLStreamReader getXmlStreamReader() {
		try {
			return XMLInputFactory.newInstance().createXMLStreamReader(new StreamSource(INPUT_FILE));
		} catch (XMLStreamException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public static XMLStreamWriter getXmlStreamWriter() {
		try {
			// TODO  StreamResult?
			return XMLOutputFactory.newInstance().createXMLStreamWriter(new FileWriter(OUTPUT_FILE));
		} catch (XMLStreamException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
}
