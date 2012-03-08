package com.blackmill.util;

public class Build {
	static boolean IN_PRODUCTION = false;

	
	
	public static void setProductionMode(boolean state) {
		IN_PRODUCTION = state;
	}
}
