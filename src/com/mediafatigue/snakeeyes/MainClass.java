package com.mediafatigue.snakeeyes;

public class MainClass {

	private static Skimmer sk;
	
	public static void main(String[] args) {
		GUIFrame frame = new GUIFrame("SnakeEyes Controls");
		
		while(sk == null) {
			
		}
		
		
	}
	
	public static void setSkimmer(Skimmer s) {
		sk = s;
	}
}