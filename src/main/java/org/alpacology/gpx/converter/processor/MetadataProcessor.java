package org.alpacology.gpx.converter.processor;

import org.alpacology.gpx.converter.StreamNavigator;
import org.alpacology.gpx.converter.model.common.Metadata;
import org.alpacology.gpx.converter.transformer.MetadataXmlFragmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.*;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

@Service
public class MetadataProcessor implements Processor {

	@Autowired
	private MetadataXmlFragmentTransformer metadataXmlFragmentTransformer;

	@Autowired
	private StreamNavigator streamNavigator;

	public void process(XMLStreamReader inputStream, XMLStreamWriter outputStream) throws XMLStreamException, JAXBException {
		streamNavigator.goToNextTag("metadata", inputStream);
		writeMetadata(inputStream, outputStream);
	}

	private void writeMetadata(XMLStreamReader inputStream, XMLStreamWriter outputStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Metadata.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement<Metadata> sourceJaxbElement = unmarshaller.unmarshal(inputStream, Metadata.class);

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		marshaller.marshal(
				metadataXmlFragmentTransformer.transform(sourceJaxbElement),
				outputStream
		);
	}
}
