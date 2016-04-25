package com.github.hexosse.worldrestorer;

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

import com.github.hexosse.worldrestorer.command.WrCommands;
import com.github.hexosse.worldrestorer.configuration.Config;
import com.github.hexosse.worldrestorer.configuration.Messages;
import com.github.hexosse.githubupdater.GitHubUpdater;
import com.github.hexosse.worldrestorer.integrations.Multiverse;
import com.github.hexosse.pluginframework.pluginapi.Plugin;
import com.github.hexosse.pluginframework.pluginapi.message.Message;
import com.github.hexosse.pluginframework.pluginapi.metric.MetricsLite;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.io.IOException;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WorldRestorer extends Plugin
{
	public static WorldRestorer instance = null;
	public static Multiverse multiverse = null;

	public static Config config = null;
	public static Messages messages = null;

	private String repository = "hexosse/WorldRestorer";


    // Activation du plugin
    @Override
    public void onEnable()
    {
        /* Instance du plugin */
		instance = this;

        /* Chargement de la config */
        config = new Config(this, getDataFolder(), "config.yml");           config.load();
        messages = new Messages(this, getDataFolder(), config.messages);     messages.load();

        /* Enregistrement des listeners */
        //Bukkit.getPluginManager().registerEvents(new WorldListener(this), this);

        /* Enregistrement du gestionnaire de commandes */
        registerCommands(new WrCommands(this));

        /* Updater */
        if(config.useUpdater)
            RunUpdater(config.downloadUpdate);

        /* Metrics */
        if(config.useMetrics)
            RunMetrics();

		/* Multiverse */
		multiverse = new Multiverse(this);

		/* Console message */
		Message message = new Message();
        message.setPrefix("§3[§b" + WorldRestorer.instance.getDescription().getName() + " " + WorldRestorer.instance.getDescription().getVersion() + "§3]§r");
        message.add(new Message(ChatColor.YELLOW, "Enable"));
		if(multiverse.enabled()) message.add("Integration with " + ChatColor.YELLOW + multiverse.getName());
		messageManager.send(Bukkit.getConsoleSender(), message);
    }

    // Désactivation du plugin
    @Override
    public void onDisable()
    {
        super.onDisable();

		/* Console message */
		Message message = new Message();
		message.setPrefix("§3[§b" + WorldRestorer.instance.getDescription().getName() + " " + WorldRestorer.instance.getDescription().getVersion() + "§3]§r");
		message.add(new Message(ChatColor.YELLOW, "Disabled"));
		messageManager.send(Bukkit.getConsoleSender(), message);
    }

 	public void RunUpdater(final boolean download)
    {
        GitHubUpdater updater = new GitHubUpdater(this, this.repository, this.getFile(), download?GitHubUpdater.UpdateType.DEFAULT:GitHubUpdater.UpdateType.NO_DOWNLOAD, true);
    }

    /**
     * Run metrics
     */
    private void RunMetrics()
    {
        try
        {
            MetricsLite metrics = new MetricsLite(this);
            if(metrics.start())
                pluginLogger.info("Succesfully started Metrics, see http://mcstats.org for more information.");
            else
                pluginLogger.info("Could not start Metrics, see http://mcstats.org for more information.");
        } catch (IOException e){
            // Failed to submit the stats :-(
        }
    }
}
