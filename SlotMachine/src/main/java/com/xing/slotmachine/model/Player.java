package com.xing.slotmachine.model;


import org.springframework.stereotype.Component;

@Component
public class Player {


	private Integer credit;

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	

}
