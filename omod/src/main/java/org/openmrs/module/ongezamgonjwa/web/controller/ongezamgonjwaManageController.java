/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.ongezamgonjwa.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

/**
 * The main controller.
 */
@Controller
public class  ongezamgonjwaManageController {
	
	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/ongezamgonjwa/addpatient.form" ,method = RequestMethod.POST)
	public void addPatient(@RequestParam(value = "fname", required = false) String fname,
							   @RequestParam(value = "mname", required = false) String mname,
							   @RequestParam(value = "lname",required = false)String lname){
		Patient patient= new Patient();
		PersonName personName =new PersonName();
		PersonAddress personAddress=new PersonAddress();

		personName.setGivenName(fname);
		personName.setMiddleName(mname);
		personName.setFamilyName(lname);

		personAddress.getAddress1();

		Set<PersonName> personNameSet=new HashSet<PersonName>();
		personNameSet.add(personName);
		patient.setNames(personNameSet);
		Context.getPatientService().savePatient(patient);

	}
    @RequestMapping(value = "/module/ongezamgonjwa/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		//List<Patient> allPatients = Context.getPatientService().getAllPatients();
		//model.addAttribute("patients", allPatients);
	}
}