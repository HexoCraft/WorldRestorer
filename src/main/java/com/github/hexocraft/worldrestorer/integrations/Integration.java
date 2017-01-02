package com.github.hexocraft.worldrestorer.integrations;

/*
 * Copyright 2016 hexosse
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import com.github.hexosse.pluginframework.pluginapi.Plugin;
import com.github.hexosse.pluginframework.pluginapi.PluginObject;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public abstract class Integration<PluginClass extends Plugin, PluginHook extends JavaPlugin> extends PluginObject<PluginClass>
{
	// Plugin to hook
	protected PluginHook pluginHook = null;
	protected String pluginName = null;

	public Integration(PluginClass plugin, String pluginName )
	{
		super(plugin);
		this.pluginName = pluginName;
	}

	public PluginHook get()
	{
		if(this.pluginHook != null) return this.pluginHook;

		PluginManager pm = getPlugin().getServer().getPluginManager();
		PluginHook pluginHook = (PluginHook)pm.getPlugin(this.pluginName);
		if(pluginHook != null && pm.isPluginEnabled(pluginHook))
			this.pluginHook = pluginHook;

		return this.pluginHook;
	}

	public String getName()
	{
		return pluginName;
	}

	public boolean enabled()
	{
		return get() != null;
	}
}
