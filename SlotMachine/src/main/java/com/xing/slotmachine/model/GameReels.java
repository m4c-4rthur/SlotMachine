package com.xing.slotmachine.model;

public class GameReels {
	
	private Integer reel1;
	private Integer reel2;
	private Integer reel3;
	public Integer getReel1() {
		return reel1;
	}
	public void setReel1(Integer reel1) {
		this.reel1 = reel1;
	}
	public Integer getReel2() {
		return reel2;
	}
	public void setReel2(Integer reel2) {
		this.reel2 = reel2;
	}
	public Integer getReel3() {
		return reel3;
	}
	public void setReel3(Integer reel3) {
		this.reel3 = reel3;
	}
	@Override
	public String toString() {
		return "GameReels [reel1=" + reel1 + ", reel2=" + reel2 + ", reel3=" + reel3 + "]";
	}
	
	

}
