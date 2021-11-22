package com.fitinsure.fitinsureservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitinsure.fitinsurevo.InsuranceQuoteVO;

@RestController
public class InsuranceQuoteService {
	
	/*
	 * Weighting system: Lower scores get better rates.
	 * 
	 * Example tiers 0 - 5 6 - 10 11 - 15 16 - 20 21+ - uninsurable
	 * 
	 * 
	 * Non-customer{ Wants insurance{ No{ } Yes{ Are you in good health? { Yes : 0
	 * 
	 * No { : 1
	 * 
	 * Do you have cancer? { Yes: 1 No: 0 } Do you have high blood pressure? { Yes:
	 * 1 No: 0 } Do you have high cholesterol? { Yes: 1 No: 0 } Do you regularly
	 * take medication? { Yes: { What condition does it treat? { Blood pressure: 1
	 * Cholesterol: 1 Seizures: 1 Pain: 1 } } No : 0 } } } Are you a smoker { No : 0
	 * Yes: 3 } Do you exercise regularly { Yes : 0 No : 2 }
	 * 
	 * } }
	 * 
	 * }
	 */
	
	/**
	 * @Variables
	
	isCustomer
	wantAQuoteOnHealthInsurance
	isASmoker
	onRegularExercise
	isAllOverHealthGood
	if isAllOverHealthGood == FALSE  
	  isCancer  
	  isHighBP  
	  isHighCholesterol  
	  onRegularMedication    
	    if onRegularMedication=TRUE        
	     isMedForHighBP        
	     isMedForHighCholesterol        
	     isMedForSeizures        
	     isMedForPain  
	     
	      
	  */ 
	
	@GetMapping("/generateQuote")
	public int getMyQuote(InsuranceQuoteVO insuranacequote) {
		
		if(insuranacequote == null) {
			return 0;
		}
		
		insuranacequote.setWeightage(0);
		
		if (insuranacequote.getIsCustomer() != null && insuranacequote.getIsCustomer() == "no") {
			if(insuranacequote.getWantAQuoteOnHealthInsurance() != null && insuranacequote.getWantAQuoteOnHealthInsurance() == "yes") {
				if(insuranacequote.getIsAllOverHealthGood() != null && insuranacequote.getIsAllOverHealthGood() == "yes") {
					insuranacequote.setWeightage(0);
				}else {
					insuranacequote.setWeightage(insuranacequote.getWeightage() +1);
					if(insuranacequote.getIsCancer() != null && insuranacequote.getIsCancer() == "yes") {
						insuranacequote.setWeightage(insuranacequote.getWeightage() +1);
					}
					if(insuranacequote.getIsHighBP() != null && insuranacequote.getIsHighBP() == "yes") {
						insuranacequote.setWeightage(insuranacequote.getWeightage() +1);
					}
					if(insuranacequote.getIsHighCholesterol() != null && insuranacequote.getIsHighCholesterol() == "yes") {
						insuranacequote.setWeightage(insuranacequote.getWeightage() +1);
					}
					if(insuranacequote.getIsHighCholesterol() != null && insuranacequote.getIsHighCholesterol() == "yes") {
						insuranacequote.setWeightage(insuranacequote.getWeightage() +1);
					}
					if(insuranacequote.getOnRegularMedication() != null && insuranacequote.getOnRegularMedication() == "yes") {
						if(insuranacequote.getIsMedForHighBP() != null && insuranacequote.getIsMedForHighBP() == "yes") {
							insuranacequote.setWeightage(insuranacequote.getWeightage() +1);
						}
						if(insuranacequote.getIsMedForHighCholesterol() != null && insuranacequote.getIsMedForHighCholesterol() == "yes") {
							insuranacequote.setWeightage(insuranacequote.getWeightage() +1);
						}
						if(insuranacequote.getIsMedForSeizures() != null && insuranacequote.getIsMedForSeizures() == "yes") {
							insuranacequote.setWeightage(insuranacequote.getWeightage() +1);
						}
						if(insuranacequote.getIsMedForPain() != null && insuranacequote.getIsMedForPain() == "yes") {
							insuranacequote.setWeightage(insuranacequote.getWeightage() +1);
						}
					}
				}
				if(insuranacequote.getIsASmoker() != null && insuranacequote.getIsASmoker() == "yes") {
					insuranacequote.setWeightage(insuranacequote.getWeightage() +3);
				}
				if(insuranacequote.getOnRegularExercise() != null && insuranacequote.getOnRegularExercise() == "no") {
					insuranacequote.setWeightage(insuranacequote.getWeightage() +2);
				}
			}
		}
		
		return insuranacequote.getWeightage();
	}

}
