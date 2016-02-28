package org.alpacology.gpx.converter;

import org.alpacology.gpx.converter.processor.StreamProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.stream.*;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class HoluxXmlConverter {
	Logger LOGGER = LoggerFactory.getLogger(HoluxXmlConverter.class);

	public static final String OUTPUT_FILE = "E:/out.xml";
	public static final String INPUT_FILE = "src/main/resources/static/test_holux.gpx";

	@Autowired
	private PrologueWriter prologueWriter;

	@Autowired
	private EpilogueWriter epilogueWriter;

	@Autowired
	private StreamProcessor streamProcessor;


	public void process() {
		XMLStreamReader streamReader = getXmlStreamReader();
		if (streamReader == null) {
			return;
		}

		XMLStreamWriter streamWriter = getXmlStreamWriter();
		if (streamWriter == null) {
			return;
		}

		try {
			prologueWriter.writePrologue(streamWriter);
			streamProcessor.process(streamReader, streamWriter);
			epilogueWriter.writeEpilogue(streamWriter);

			freeResources(streamWriter, streamReader);
		} catch (XMLStreamException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private void freeResources(XMLStreamWriter eventWriter, XMLStreamReader eventReader) throws XMLStreamException {
		eventReader.close();
		eventWriter.flush();
		eventWriter.close();
	}

	private XMLStreamWriter getXmlStreamWriter() {
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

	private XMLStreamReader getXmlStreamReader() {
		try {
			return XMLInputFactory.newInstance().createXMLStreamReader(new StreamSource(INPUT_FILE));
		} catch (XMLStreamException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
}
