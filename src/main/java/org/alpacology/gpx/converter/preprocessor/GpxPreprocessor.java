package org.alpacology.gpx.converter.preprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

@Service
public class GpxPreprocessor {

	@Autowired
	private HeartRatePreprocessor heartRatePreprocessor;

	@Autowired
	private CadencePreprocessor cadencePreprocessor;

	public PreprocessorOutput preprocess() throws XMLStreamException {
		PreprocessorOutput preprocessorOutput = new PreprocessorOutput();

		heartRatePreprocessor.checkIfHeartRateWasReported(preprocessorOutput);
		cadencePreprocessor.checkIfCadenceWasReported(preprocessorOutput);

		return preprocessorOutput;
	}
}
