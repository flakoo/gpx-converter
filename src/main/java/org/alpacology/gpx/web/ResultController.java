package org.alpacology.gpx.web;

import org.alpacology.gpx.converter.HoluxParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResultController {

	@Autowired
	private HoluxParser holuxParser;

	@RequestMapping("/result")
	public String showResult() {
		holuxParser.parse();
		return "result";
	}
}
