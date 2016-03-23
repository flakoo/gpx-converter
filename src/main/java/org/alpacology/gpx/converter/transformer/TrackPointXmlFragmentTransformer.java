package org.alpacology.gpx.converter.transformer;

import org.alpacology.gpx.converter.model.garmin.GarminExtensions;
import org.alpacology.gpx.converter.model.garmin.GarminTrackPointExtension;
import org.alpacology.gpx.converter.model.garmin.GarminTrackPoint;
import org.alpacology.gpx.converter.model.holux.HoluxExtensions;
import org.alpacology.gpx.converter.model.holux.HoluxTrackPoint;
import org.alpacology.gpx.converter.preprocessor.PreprocessorOutput;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Service
public class TrackPointXmlFragmentTransformer implements XmlFragmentTransformer<HoluxTrackPoint, GarminTrackPoint> {

	@Override
	public JAXBElement<GarminTrackPoint> transform(JAXBElement<HoluxTrackPoint> from, PreprocessorOutput preprocessorOutput) {
		return new JAXBElement<GarminTrackPoint>(
				new QName("trkpt"),
				GarminTrackPoint.class,
				mapHoluxToGarmin(from.getValue(), preprocessorOutput));
	}

	private GarminTrackPoint mapHoluxToGarmin(HoluxTrackPoint holuxTrackPoint, PreprocessorOutput preprocessorOutput) {
		GarminTrackPoint garminTrackPoint = new GarminTrackPoint();
		garminTrackPoint.setElevation(holuxTrackPoint.getElevation());
		garminTrackPoint.setLat(holuxTrackPoint.getLat());
		garminTrackPoint.setLng(holuxTrackPoint.getLng());
		garminTrackPoint.setTime(holuxTrackPoint.getTime());

		garminTrackPoint.setExtensions(
				mapHoluxExtensionsToGarminExtensions(holuxTrackPoint.getExtensions(), preprocessorOutput)
		);

		return garminTrackPoint;
	}

	private GarminExtensions mapHoluxExtensionsToGarminExtensions(HoluxExtensions holuxExtensions, PreprocessorOutput preprocessorOutput) {
		if (!preprocessorOutput.isHasCadence() && !preprocessorOutput.isHasHeartRate()) {
			return null;
		}

		GarminTrackPointExtension garminTrackPointExtension = new GarminTrackPointExtension();

		setCadenceIfPresent(holuxExtensions, preprocessorOutput, garminTrackPointExtension);
		setHeartRateIfPresent(holuxExtensions, preprocessorOutput, garminTrackPointExtension);

		GarminExtensions garminExtensions = new GarminExtensions();
		garminExtensions.setGarminTrackPointExtension(garminTrackPointExtension);

		return garminExtensions;
	}

	private void setCadenceIfPresent(HoluxExtensions holuxExtensions, PreprocessorOutput preprocessorOutput, GarminTrackPointExtension garminTrackPointExtension) {
		if (preprocessorOutput.isHasCadence()) {
			garminTrackPointExtension.setCadence(Math.round(holuxExtensions.getCadence()));
		}
	}

	private void setHeartRateIfPresent(HoluxExtensions holuxExtensions, PreprocessorOutput preprocessorOutput, GarminTrackPointExtension garminTrackPointExtension) {
		if (preprocessorOutput.isHasHeartRate()) {
			garminTrackPointExtension.setHeartrate(holuxExtensions.getHeartrate());
		}
	}
}
