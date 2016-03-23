package org.alpacology.gpx.converter.processor;

import org.alpacology.gpx.converter.StreamNavigator;
import org.alpacology.gpx.converter.preprocessor.PreprocessorOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.util.NoSuchElementException;

@Service
public class TrackProcessor implements Processor {

	@Autowired
	private SegmentProcessor segmentProcessor;

	@Autowired
	private StreamNavigator streamNavigator;

	@Override
	public void process(XMLStreamReader inputStream, XMLStreamWriter outputStream, PreprocessorOutput preprocessorOutput) throws XMLStreamException, JAXBException {
		try {
			while (true) {
				processNextTrack(inputStream, outputStream, preprocessorOutput);
			}
		} catch(NoSuchElementException e) {
			// End reached.
		}

	}

	private void processNextTrack(XMLStreamReader inputStream, XMLStreamWriter outputStream, PreprocessorOutput preprocessorOutput) throws XMLStreamException, JAXBException {
		streamNavigator.goToNextTag("trk", inputStream);

		outputStream.writeStartElement("http://www.topografix.com/GPX/1/1", "trk");
		writeName(inputStream, outputStream);
		segmentProcessor.process(inputStream, outputStream, preprocessorOutput);
		outputStream.writeEndElement();

		inputStream.next();
	}

	private void writeName(XMLStreamReader inputStream, XMLStreamWriter outputStream) throws XMLStreamException {
		streamNavigator.goToNextTagIfNotClosingOf("name", "trk", inputStream);
		inputStream.next(); // TODO kind of not self-explanatory (rewind to text) + does not protect from empty text

		outputStream.writeStartElement("http://www.topografix.com/GPX/1/1", "name");
		outputStream.writeCData(inputStream.getText());

		outputStream.writeEndElement();
		inputStream.next(); // TODO kind of not self-explanatory: rewind to the end of closing tag
	}

}
