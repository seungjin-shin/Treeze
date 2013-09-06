package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.Version;
import com.hansung.treeze.service.VersionService;

@Controller
public class VersionController {
	private static final Logger logger = LoggerFactory
			.getLogger(VersionController.class);
	@Autowired
	private VersionService versionService;

	// @Autowired private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/updateVersion", method = RequestMethod.POST)
	public String updateVersion(Version model, ModelMap map) {
		Version version = versionService.findLastVersion(model.getUserType());

		if (version == null)
			version = versionService.saveVersion(model);
		else{
			version.setVersion(model.getVersion());
			version = versionService.saveVersion(version);
		}

		map.put("version", version);

		return "jsonView";
	}

	@RequestMapping(value = "/getLastVersion", method = RequestMethod.GET)
	public String getLastVersion(@RequestParam("userType") String userType,
			ModelMap map) {

		Version version = versionService.findLastVersion(userType);
		map.put("version", version);

		return "jsonView";
	}

}
