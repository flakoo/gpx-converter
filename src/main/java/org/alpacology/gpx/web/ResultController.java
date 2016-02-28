package org.alpacology.gpx.web;

import org.alpacology.gpx.converter.HoluxXmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResultController {

	@Autowired
	private HoluxXmlConverter holuxXmlConverter;

	@RequestMapping("/result")
	public String showResult() {
		holuxXmlConverter.process();
		return "result";
	}
}
