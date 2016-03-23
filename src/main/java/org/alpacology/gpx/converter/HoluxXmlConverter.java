package org.alpacology.gpx.converter;

import org.alpacology.gpx.converter.preprocessor.GpxPreprocessor;
import org.alpacology.gpx.converter.preprocessor.PreprocessorOutput;
import org.alpacology.gpx.converter.processor.GpxProcessor;
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

	@Autowired
	private PrologueWriter prologueWriter;

	@Autowired
	private EpilogueWriter epilogueWriter;

	@Autowired
	private GpxProcessor gpxProcessor;

	@Autowired
	private GpxPreprocessor gpxPreprocessor;

	public void convert() {
		PreprocessorOutput preprocessorOutput = null;
		try {
			preprocessorOutput = preprocess();
			process(preprocessorOutput);
		} catch (XMLStreamException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private void process(PreprocessorOutput preprocessorOutput) throws XMLStreamException {
		XMLStreamReader streamReader = XMLStreamFactory.getXmlStreamReader();
		if (streamReader == null) {
			return;
		}

		XMLStreamWriter streamWriter = XMLStreamFactory.getXmlStreamWriter();
		if (streamWriter == null) {
			return;
		}

		try {
			prologueWriter.writePrologue(streamWriter);
			gpxProcessor.process(streamReader, streamWriter, preprocessorOutput);
			epilogueWriter.writeEpilogue(streamWriter);

			freeResources(streamWriter, streamReader);
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private PreprocessorOutput preprocess() throws XMLStreamException {
		PreprocessorOutput preprocessorOutput = gpxPreprocessor.preprocess();
		return preprocessorOutput;
	}

	private void freeResources(XMLStreamWriter eventWriter, XMLStreamReader eventReader) throws XMLStreamException {
		eventReader.close();
		eventWriter.flush();
		eventWriter.close();
	}
}
