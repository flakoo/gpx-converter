package org.alpacology.gpx.converter.transformer;

import org.alpacology.gpx.converter.model.holux.HoluxTrackPoint;
import org.alpacology.gpx.converter.preprocessor.PreprocessorOutput;

import javax.xml.bind.JAXBElement;

public interface XmlFragmentTransformer<F, T> {
	JAXBElement<T> transform(JAXBElement<F> from, PreprocessorOutput preprocessorOutput);
}
