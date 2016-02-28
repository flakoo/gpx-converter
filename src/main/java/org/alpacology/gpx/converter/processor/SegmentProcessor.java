package org.alpacology.gpx.converter.processor;

import org.alpacology.gpx.converter.StreamNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.util.NoSuchElementException;

@Service
public class SegmentProcessor implements Processor {

	@Autowired
	private TrackPointProcessor trackPointProcessor;

	@Autowired
	private StreamNavigator streamNavigator;

	@Override
	public void process(XMLStreamReader inputStream, XMLStreamWriter outputStream) throws XMLStreamException, JAXBException {
		try {
			while (true) {
				processNextSegment(inputStream, outputStream);
			}
		} catch(NoSuchElementException e) {
			// End reached.
		}
	}

	private void processNextSegment(XMLStreamReader inputStream, XMLStreamWriter outputStream) throws XMLStreamException, JAXBException {
		streamNavigator.goToNextTagIfNotClosingOf("trkseg", "trk", inputStream);

		outputStream.writeStartElement("http://www.topografix.com/GPX/1/1", "trkseg");
		trackPointProcessor.process(inputStream, outputStream);
		outputStream.writeEndElement();

		inputStream.next();
	}
}
