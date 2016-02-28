package org.alpacology.gpx.converter.transformer;

import javax.xml.bind.JAXBElement;

public interface XmlFragmentTransformer<F, T> {
	JAXBElement<T> transform(JAXBElement<F> from);
}
