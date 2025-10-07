package com.natamus.sleepsooner;

import com.natamus.collective.config.GenerateJSONFiles;
import com.natamus.sleepsooner.config.ConfigHandler;
import com.natamus.sleepsooner.util.Reference;
import org.apache.logging.log4j.core.tools.Generate;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		GenerateJSONFiles.requestJSONFile(Reference.MOD_ID, "linger_messages.json");
	}
}