package me.thesilverecho.zeropoint.impl;

import me.thesilverecho.zeropoint.api.config.Config;
import me.thesilverecho.zeropoint.api.config.ConfigSetting;
import me.thesilverecho.zeropoint.api.util.DiscordPresence;
import me.thesilverecho.zeropoint.api.util.ZeroPointApiLogger;
import me.thesilverecho.zeropoint.impl.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ZeroPointClient implements ClientModInitializer
{
	public static final Config DEFAULT_CONFIG = new Config("Zero-point");
	public static final String MOD_ID = "zero-point";
	@ConfigSetting
	private final int version = 1;


	@Override
	public void onInitializeClient()
	{
		DEFAULT_CONFIG.register(this);
		ZeroPointApiLogger.setUp(true, true, true);
		ModuleManager.registerAllModules();
		DEFAULT_CONFIG.save();
		DiscordPresence.startRPC();
//		ModelLoadingRegistry.INSTANCE.registerResourceProvider(ObjFileLoader.INSTANCE);
//		ModelLoadingRegistry.INSTANCE.registerVariantProvider(ItemObjectFileLoader.INSTANCE);

//		ShaderManager.initShaders();
//		FontManager.initFonts();
	}


}
