package com.fitinsure.fitinsureservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitinsure.fitinsurevo.DiscountVO;

@RestController
public class DiscountGeneratorService {
	
	@GetMapping("/generateDiscount")
	public int getMyDiscount(DiscountVO discount) {
		
		if(discount == null) {
			return 0;
		}
		
		discount.setIntDiscountPercent(0);
		discount.setIntDiscountPoints(0);
		
		if(discount.getIsCustomer() != null && discount.getIsCustomer() == "yes") {
			if(discount.getWantToEnroll() != null && discount.getWantToEnroll() == "yes") {
				if(discount.getIs19OrYounger() != null && discount.getIs19OrYounger() == "yes") {
					if(discount.getIsCholLessThan170() != null && discount.getIsCholLessThan170() == "yes") {
						discount.setIntDiscountPoints(discount.getIntDiscountPoints() + 500);
					}
					if(discount.getIsSysBPLessThan120() !=null && discount.getIsSysBPLessThan120() == "yes") {
						if(discount.getIsDiaBPLessThan80() !=null && discount.getIsDiaBPLessThan80() == "yes") {
							discount.setIntDiscountPoints(discount.getIntDiscountPoints() + 500);
						}
					}
				}
				if(discount.getIs20OrOlder() != null && discount.getIs20OrOlder() == "yes") {
					if(discount.getIsCholBet125And200() != null && discount.getIsCholBet125And200() == "yes") {
						discount.setIntDiscountPoints(discount.getIntDiscountPoints() + 500);
					}
					if(discount.getIsSysBPLessThan120() !=null && discount.getIsSysBPLessThan120() == "yes") {
						if(discount.getIsDiaBPLessThan80() !=null && discount.getIsDiaBPLessThan80() == "yes") {
							discount.setIntDiscountPoints(discount.getIntDiscountPoints() + 500);
						}
					}
				}
				if(discount.getIsSmoker() != null && discount.getIsSmoker() == "no") {
					discount.setIntDiscountPoints(discount.getIntDiscountPoints() + 1000);
				}
				if(discount.getIsBPLessThan140() != null && discount.getIsBPLessThan140() == "yes") {
					discount.setIntDiscountPoints(discount.getIntDiscountPoints() + 300);
				}
				if(discount.getIsDailyFitnessGoalMet() != null && discount.getIsDailyFitnessGoalMet() == "yes") {
					discount.setIntDiscountPoints(discount.getIntDiscountPoints() + discount.getNumDaysFitnessGoalMet()*5);
				}
				if(discount.getHasFluShot() != null && discount.getHasFluShot() == "yes") {
					discount.setIntDiscountPoints(discount.getIntDiscountPoints() + 300);
				}
				if(discount.getHasCOVIDShot() != null && discount.getHasCOVIDShot() == "yes") {
					discount.setIntDiscountPoints(discount.getIntDiscountPoints() + 500);
				}
				
				if(discount.getIntDiscountPoints() < 2000) {
					discount.setIntDiscountPercent(5);
				}else{
					if(discount.getIntDiscountPoints() < 3000) {
						discount.setIntDiscountPercent(10);
					}else {
						if(discount.getIntDiscountPoints() > 3000) {
							discount.setIntDiscountPercent(15);
						}
					}
				}
			}
		}
		
		
		
		return discount.getIntDiscountPercent();

	}

}
