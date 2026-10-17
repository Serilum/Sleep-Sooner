package com.serilum.sleepsooner.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.collective.config.DuskConfigMod;
import com.natamus.collective.functions.ConfigFunctions;
import com.serilum.sleepsooner.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean enablePreSleepMessage = true;
	@Entry(min = 0, max = 24000) public static int whenSleepIsPossibleInTicks = 12000;

	public static void initConfig() {
		configMetaData.put("enablePreSleepMessage", Arrays.asList(
			"Allows you to disable/enable the message you receive before sleeping. You still need to click the bed twice if the current time is below 12540."
		));
		configMetaData.put("whenSleepIsPossibleInTicks", Arrays.asList(
			"The default time in ticks when you can sleep is ~12540. The default Sleep Sooner mod value is 12000."
		));

		boolean wasDisabled = "false".equals(ConfigFunctions.getConfigValue(Reference.MOD_ID, "enableSleepSooner"));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);

		if (wasDisabled) {
			DuskConfigMod.configMods.get(Reference.MOD_ID).enableEntry.setValue(false);
			DuskConfig.write(Reference.MOD_ID);
		}
	}
}