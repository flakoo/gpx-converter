package org.alpacology.gpx.converter.processor;

import org.alpacology.gpx.converter.StreamNavigator;
import org.alpacology.gpx.converter.model.garmin.GarminTrackPoint;
import org.alpacology.gpx.converter.model.holux.HoluxTrackPoint;
import org.alpacology.gpx.converter.transformer.TrackPointXmlFragmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.*;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.util.NoSuchElementException;

@Service
public class TrackPointProcessor implements Processor {

	@Autowired
	private TrackPointXmlFragmentTransformer trackPointXmlFragmentTransformer;

	@Autowired
	private StreamNavigator streamNavigator;

	@Override
	public void process(XMLStreamReader inputStream, XMLStreamWriter outputStream) throws XMLStreamException, JAXBException {
		try {
			while (true) {
				processNextPoint(inputStream, outputStream);
			}
		} catch(NoSuchElementException e) {
			// End reached.
		}
	}

	private void processNextPoint(XMLStreamReader inputStream, XMLStreamWriter outputStream) throws JAXBException, XMLStreamException {
		streamNavigator.goToNextTagIfNotClosingOf("trkpt", "trkseg", inputStream);

		JAXBContext jaxbInputContext = JAXBContext.newInstance(HoluxTrackPoint.class);

		Unmarshaller unmarshaller = jaxbInputContext.createUnmarshaller();
		JAXBElement<HoluxTrackPoint> sourceJaxbElement = unmarshaller.unmarshal(inputStream, HoluxTrackPoint.class);

		JAXBContext jaxbOutputContext = JAXBContext.newInstance(GarminTrackPoint.class);
		Marshaller marshaller = jaxbOutputContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		marshaller.marshal(
				trackPointXmlFragmentTransformer.transform(sourceJaxbElement),
				outputStream
		);
	}
}
