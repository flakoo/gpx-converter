package org.alpacology.gpx.converter.processor;

import org.alpacology.gpx.converter.preprocessor.PreprocessorOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

@Service
public class GpxProcessor implements Processor {

	@Autowired
	private MetadataProcessor metadataProcessor;

	@Autowired
	private TrackProcessor trackProcessor;

	public void process(XMLStreamReader inputStream, XMLStreamWriter outputStream, PreprocessorOutput preprocessorOutput) throws XMLStreamException, JAXBException {
		metadataProcessor.process(inputStream, outputStream, preprocessorOutput);
		trackProcessor.process(inputStream, outputStream, preprocessorOutput);
	}
}
