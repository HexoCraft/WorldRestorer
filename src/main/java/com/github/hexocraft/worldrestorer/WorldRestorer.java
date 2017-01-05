package com.github.hexocraft.worldrestorer;

/*
 * Copyright 2017 hexosse
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

import com.github.hexocraft.worldrestorer.command.WrCommands;
import com.github.hexocraft.worldrestorer.configuration.Config;
import com.github.hexocraft.worldrestorer.configuration.Messages;
import com.github.hexocraft.worldrestorer.integrations.Multiverse;
import com.github.hexocraft.worldrestorer.listeners.UpdaterListener;
import com.github.hexocraft.worldrestorer.listeners.WorldListener;
import com.github.hexocraftapi.integration.Hook;
import com.github.hexocraftapi.message.Line;
import com.github.hexocraftapi.message.predifined.message.PluginMessage;
import com.github.hexocraftapi.message.predifined.message.PluginTitleMessage;
import com.github.hexocraftapi.plugin.Plugin;
import com.github.hexocraftapi.updater.BukkitUpdater;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WorldRestorer extends Plugin
{
	public static WorldRestorer instance = null;
	public static Config config = null;
	public static Messages messages = null;

	/* Plugins */
	public static Multiverse multiverse = null;

    // Activation du plugin
    @Override
    public void onEnable()
    {
        /* Instance du plugin */
		instance = this;

        /* Chargement de la config */
	    config = new Config(this, "config.yml", true);
	    messages = new Messages(this, config.messages, true);

        /* Enregistrement du gestionnaire de commandes */
	    registerCommands(new WrCommands(this));

        /* Enregistrement des listeners */
		Bukkit.getPluginManager().registerEvents(new WorldListener(this), this);
		Bukkit.getPluginManager().registerEvents(new UpdaterListener(this), this);

		/* Multiverse */
		multiverse = (Multiverse) new Hook(Multiverse.class, "Multiverse-Core", "com.onarandombox.MultiverseCore.MultiverseCore").get();

		/* Enable message */
	    PluginTitleMessage titleMessage = new PluginTitleMessage(this, "WorldRestorer is enable ...", ChatColor.YELLOW);
	    if(multiverse != null) titleMessage.add("Integration with " + ChatColor.YELLOW + multiverse.get().getName());
	    titleMessage.send(Bukkit.getConsoleSender());

        /* Updater */
	    if(config.useUpdater)
		    runUpdater(getServer().getConsoleSender(), 20 * 10);

        /* Metrics */
	    if(config.useMetrics)
		    runMetrics(20 * 2);
    }

    // Désactivation du plugin
    @Override
    public void onDisable()
    {
        super.onDisable();

	    PluginMessage.toConsole(this, "Disabled", ChatColor.RED, new Line("WorldRestorer is now disabled", ChatColor.DARK_RED));
    }

	public void runUpdater(final CommandSender sender, int delay)
	{
		super.runUpdater(new BukkitUpdater(this, "256382"), sender, delay);
	}

	private void runMetrics(int delay)
	{
		super.RunMetrics(delay);
	}
}
