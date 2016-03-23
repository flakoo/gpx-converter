package org.alpacology.gpx.converter;

import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.NoSuchElementException;

@Service
public class StreamNavigator {
	public void goToNextTag(String tag, XMLStreamReader streamReader) throws XMLStreamException {
		int elementType;
		do {
			elementType = streamReader.next();
		} while (isNotTheRightTagAndNotTheEnd(tag, streamReader, elementType));

		checkIfNotEndOfDocument(elementType);

		if (elementType != XMLStreamConstants.START_ELEMENT) {
			throw new NoSuchElementException("No '" + tag + "' tag found");
		}
	}

	private void checkIfNotEndOfDocument(int elementType) {
		if (elementType == XMLStreamConstants.END_DOCUMENT) {
			throw new NoSuchElementException("End of document reached");
		}
	}

	public void goToNextTagIfNotClosingOf(String tag, String closingTag, XMLStreamReader inputStream) throws XMLStreamException {
		int elementType;
		do {
			elementType = inputStream.next();

			if (isClosingTag(closingTag, inputStream, elementType)) {
				throw new NoSuchElementException("No element '" + tag + "' before closing of tag '" + closingTag + "'");
			}
		} while (isNotTheRightTagAndNotTheEnd(tag, inputStream, elementType));

		checkIfNotEndOfDocument(elementType);
	}

	private boolean isNotTheRightTagAndNotTheEnd(String tag, XMLStreamReader inputStream, int elementType) {
		return !(elementType == XMLStreamConstants.START_ELEMENT &&
					inputStream.getName().getLocalPart().equals(tag)) &&
				elementType != XMLStreamConstants.END_DOCUMENT;
	}

	private boolean isClosingTag(String closingTag, XMLStreamReader inputStream, int elementType) {
		return elementType == XMLStreamConstants.END_ELEMENT &&
			inputStream.getName().getLocalPart().equals(closingTag);
	}
}
