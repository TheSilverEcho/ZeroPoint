package me.thesilverecho.zeropoint.api.module;

import me.thesilverecho.zeropoint.api.event.EventManager;
import me.thesilverecho.zeropoint.api.util.Keybind;

public class BaseModule implements IModule
{

	private final String name, description;
	private final Keybind keybind;
	private final boolean shouldDraw;
	private boolean enabled;


	public BaseModule()
	{
		final ClientModule annotation = getClass().getAnnotation(ClientModule.class);
		this.enabled = annotation.active();
		this.name = annotation.name();
		this.shouldDraw = annotation.shouldDraw();
		this.description = annotation.description();
		this.keybind = new Keybind(annotation.keyBinding(), Keybind.Duration.TOGGLED, clickType ->
		{
			if (clickType == Keybind.ClickType.PRESSED)
				toggle();
		});
	}


	private void toggle()
	{
		this.enabled = !enabled;
		if (enabled)
		{
			onEnable();
			EventManager.register(this.getClass());
		} else
		{
			EventManager.deregister(this.getClass());
			onDisable();
		}
	}


	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public Keybind getKeybind()
	{
		return keybind;
	}

	public boolean shouldDraw()
	{
		return shouldDraw;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
