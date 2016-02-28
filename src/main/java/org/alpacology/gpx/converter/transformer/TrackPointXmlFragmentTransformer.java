package org.alpacology.gpx.converter.transformer;

import org.alpacology.gpx.converter.model.garmin.GarminExtensions;
import org.alpacology.gpx.converter.model.garmin.GarminTrackPointExtension;
import org.alpacology.gpx.converter.model.garmin.GarminTrackPoint;
import org.alpacology.gpx.converter.model.holux.HoluxExtensions;
import org.alpacology.gpx.converter.model.holux.HoluxTrackPoint;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Service
public class TrackPointXmlFragmentTransformer implements XmlFragmentTransformer<HoluxTrackPoint, GarminTrackPoint> {

	@Override
	public JAXBElement<GarminTrackPoint> transform(JAXBElement<HoluxTrackPoint> from) {
		return new JAXBElement<GarminTrackPoint>(
				new QName("trkpt"),
				GarminTrackPoint.class,
				mapHoluxToGarmin(from.getValue()));
	}

	private GarminTrackPoint mapHoluxToGarmin(HoluxTrackPoint holuxTrackPoint) {
		GarminTrackPoint garminTrackPoint = new GarminTrackPoint();
		garminTrackPoint.setElevation(holuxTrackPoint.getElevation());
		garminTrackPoint.setLat(holuxTrackPoint.getLat());
		garminTrackPoint.setLng(holuxTrackPoint.getLng());
		garminTrackPoint.setTime(holuxTrackPoint.getTime());

		garminTrackPoint.setExtensions(
				mapHoluxExtensionsToGarminExtensions(holuxTrackPoint.getExtensions())
		);

		return garminTrackPoint;
	}

	private GarminExtensions mapHoluxExtensionsToGarminExtensions(HoluxExtensions holuxExtensions) {

		GarminTrackPointExtension garminTrackPointExtension = new GarminTrackPointExtension();
		garminTrackPointExtension.setCadence(Math.round(holuxExtensions.getCadence()));
		garminTrackPointExtension.setHeartrate(holuxExtensions.getHeartrate());

		GarminExtensions garminExtensions = new GarminExtensions();
		garminExtensions.setGarminTrackPointExtension(garminTrackPointExtension);

		return garminExtensions;
	}
}
