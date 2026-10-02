package com.serilum.sleepsooner;

import com.natamus.collective.config.GenerateJSONFiles;
import com.serilum.sleepsooner.config.ConfigHandler;
import com.serilum.sleepsooner.util.Reference;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		GenerateJSONFiles.requestJSONFile(Reference.MOD_ID, "linger_messages.json");
	}
}