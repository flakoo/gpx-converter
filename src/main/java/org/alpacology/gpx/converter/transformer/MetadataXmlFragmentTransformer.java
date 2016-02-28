package org.alpacology.gpx.converter.transformer;

import org.alpacology.gpx.converter.model.common.Metadata;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Service
public class MetadataXmlFragmentTransformer implements XmlFragmentTransformer<Metadata, Metadata> {

	@Override
	public JAXBElement<Metadata> transform(JAXBElement<Metadata> from) {
		return new JAXBElement<Metadata>(
				new QName("metadata"),
				Metadata.class,
				from.getValue()
		);
	}
}
