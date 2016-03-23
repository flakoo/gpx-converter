package org.alpacology.gpx.converter.preprocessor;

import org.alpacology.gpx.converter.StreamNavigator;
import org.alpacology.gpx.converter.XMLStreamFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.NoSuchElementException;

@Service
public class HeartRatePreprocessor {
	private static final String HEART_RATE_TAG = "bpm";

	@Autowired
	private StreamNavigator streamNavigator;

	public void checkIfHeartRateWasReported(PreprocessorOutput output) throws XMLStreamException {
		output.setHasHeartRate(false);

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
		}
	}

	private boolean checkNextTag(XMLStreamReader streamReader, PreprocessorOutput output) throws XMLStreamException {
		streamNavigator.goToNextTag(HEART_RATE_TAG, streamReader);
		if (!streamReader.getElementText().equals("0")) {
			output.setHasHeartRate(true);
			return true;
		}
		return false;
	}
}
