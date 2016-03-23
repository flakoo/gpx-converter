package org.alpacology.gpx.converter.preprocessor;

import org.alpacology.gpx.converter.StreamNavigator;
import org.alpacology.gpx.converter.XMLStreamFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.NoSuchElementException;

@Service
public class CadencePreprocessor {

	private static final String CADENCE_TAG = "rpm";

	@Autowired
	private StreamNavigator streamNavigator;

	public void checkIfCadenceWasReported(PreprocessorOutput output) throws XMLStreamException {
		output.setHasCadence(false);

		XMLStreamReader streamReader = XMLStreamFactory.getXmlStreamReader();
		if (streamReader == null) {
			return;
		}

		try {
			while (true) {
				if (checkNextTag(streamReader, output)) {
					return;
				}
			}

		} catch (NoSuchElementException e) {
			// End of document reached
		} finally {
			streamReader.close();
		}
	}

	private boolean checkNextTag(XMLStreamReader streamReader, PreprocessorOutput output) throws XMLStreamException {
		streamNavigator.goToNextTag(CADENCE_TAG, streamReader);
		if (!streamReader.getElementText().equals("0.00")) {
			output.setHasCadence(true);
			return true;
		}
		return false;
	}
}
