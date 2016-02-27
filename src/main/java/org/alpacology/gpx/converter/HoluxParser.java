package org.alpacology.gpx.converter;

import org.springframework.stereotype.Service;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class HoluxParser {
	public static final String FILE = "src/main/resources/static/test_holux.gpx";

	public void parse() {
		XMLInputFactory factory = XMLInputFactory.newInstance();
//		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
		XMLEventReader eventReader = null;

		try {
			eventReader = factory.createXMLEventReader(new FileReader(FILE));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (eventReader == null) {
			return;
		}

		while (eventReader.hasNext()) {
			XMLEvent event = null;
			try {
				event = eventReader.nextEvent();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}

			if(event.getEventType() == XMLStreamConstants.START_ELEMENT){
				StartElement startElement = event.asStartElement();
				System.out.println(startElement.getName().getLocalPart());
			}
		}
	}
}
