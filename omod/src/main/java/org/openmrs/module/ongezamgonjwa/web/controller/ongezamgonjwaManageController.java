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
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The main controller.
 */
@Controller
public class  ongezamgonjwaManageController {

    protected final Log log = LogFactory.getLog(getClass());
    @RequestMapping(value = "/module/ongezamgonjwa/manage", method = RequestMethod.GET)
    public void manage(ModelMap model) {
        //List<Patient> allPatients = Context.getPatientService().getAllPatients();
        //model.addAttribute("patients", allPatients);
    }

    @RequestMapping(value = "/module/ongezamgonjwa/addpatient.form" ,method = RequestMethod.POST)
    public String addPatient(ModelMap model, WebRequest webRequest, HttpSession httpSession,
                             @RequestParam(value = "fname", required = false) String fname,
                             @RequestParam(value = "mname", required = false) String mname,
                             @RequestParam(value = "lname",required = false)String lname,
                             @RequestParam(value = "dob", required = true) String dateofbirth,
                             @RequestParam(value = "sex",required = false)String sex,
                             @RequestParam(value = "NID",required = false)String nid) throws ParseException {
            String expectedPattern = "dd-mm-yyyy";
            SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
            Date date = formatter.parse(dateofbirth);

            Patient patient = new Patient();
            PersonName personName = new PersonName();
            PatientIdentifier patientIdentifier = new PatientIdentifier();

            PatientIdentifierType patientIdentifierType = Context.getPatientService().getPatientIdentifierTypeByUuid("8d79403a-c2cc-11de-8d13-0010c6dffd0f");
            patientIdentifier.setPatientIdentifierId(Integer.parseInt(nid));

            patientIdentifier.setIdentifier(nid);
            patientIdentifier.setDateCreated(new Date());
            patientIdentifier.setLocation(Context.getLocationService().getDefaultLocation());
            patientIdentifier.setIdentifierType(patientIdentifierType);
            patientIdentifier.setPreferred(true);

            personName.setGivenName(fname);
            personName.setMiddleName(mname);
            personName.setFamilyName(lname);

            patient.addName(personName);
            patient.setGender(sex);
            patient.setBirthdate(date);


            //PatientIdentifierValidator.validateIdentifier(patientIdentifier);
            patient.addIdentifier(patientIdentifier);

            //saving the patient
            Context.getPatientService().savePatient(patient);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Registered Successfully");
            return "redirect:manage.form";
    }
}