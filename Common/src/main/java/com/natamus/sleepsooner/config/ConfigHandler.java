package com.natamus.sleepsooner.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.sleepsooner.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean enableSleepSooner = true;
	@Entry public static boolean enablePreSleepMessage = true;
	@Entry(min = 0, max = 24000) public static int whenSleepIsPossibleInTicks = 12000;

	public static void initConfig() {
		configMetaData.put("enableSleepSooner", Arrays.asList(
			"Allows you to disable/enable the Sleep Sooner mod."
		));
		configMetaData.put("enablePreSleepMessage", Arrays.asList(
			"Allows you to disable/enable the message you receive before sleeping. You still need to click the bed twice if the current time is below 12540."
		));
		configMetaData.put("whenSleepIsPossibleInTicks", Arrays.asList(
			"The default time in ticks when you can sleep is ~12540. The default Sleep Sooner mod value is 12000."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}