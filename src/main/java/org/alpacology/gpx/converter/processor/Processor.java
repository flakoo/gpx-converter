package org.alpacology.gpx.converter.processor;

import org.alpacology.gpx.converter.preprocessor.PreprocessorOutput;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public interface Processor {
	void process(XMLStreamReader inputStream, XMLStreamWriter outputStream, PreprocessorOutput preprocessorOutput) throws XMLStreamException, JAXBException;
}
