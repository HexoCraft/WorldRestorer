package com.github.hexocraft.worldrestorer.command;

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

import com.github.hexocraft.worldrestorer.WorldRestorer;
import com.github.hexocraft.worldrestorer.WorldRestorerApi;
import com.github.hexocraft.worldrestorer.command.ArgType.ArgTypeSavedWorld;
import com.github.hexocraft.worldrestorer.configuration.Permissions;
import com.github.hexocraft.worldrestorer.configuration.WorldConfig;
import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandArgument;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.type.ArgTypeString;
import com.github.hexocraftapi.message.predifined.message.EmptyMessage;
import com.github.hexocraftapi.message.predifined.message.SimplePrefixedMessage;
import com.github.hexocraftapi.message.predifined.message.WarningPrefixedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import static com.github.hexocraft.worldrestorer.command.WrCommands.prefix;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandLoad extends Command<WorldRestorer>
{
	private WorldConfig worldConfig = null;

    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandLoad(WorldRestorer plugin)
    {
		super("load", plugin);
		this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cLoadArgWorld));
		this.addArgument(new CommandArgument<String>("load as", ArgTypeString.get(), false, false, plugin.messages.cLoadArgWorldAs));
        this.setDescription(plugin.messages.cLoad);
        this.setPermission(Permissions.LOAD.toString());
    }

    /**
     * Executes the given command, returning its success
     *
     * @param commandInfo Info about the command
     *
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandInfo commandInfo)
    {
		// Get data from command line
		String savedWorld = commandInfo.getNamedArg("saved world");
		String loadAs = commandInfo.getNamedArg("load as");
		if(loadAs==null) loadAs = savedWorld;

		// Check if a saved world exist
		if(!WorldRestorerApi.exist(getPlugin(), savedWorld))
		{
			sendErrorMessage(commandInfo.getSender(), loadAs);
			return false;
		}

		// Get the WorldConfig
		worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

		// Delay the load of the world if necessary
		if(worldConfig.unloadDelay!=0)
			delayLoadWorld(commandInfo.getSender(), savedWorld, loadAs);
		else
			loadWorld(commandInfo.getSender(), savedWorld, loadAs);

		return true;
	}

	private void delayLoadWorld(final CommandSender sender, final String savedWorld, final String loadAs)
	{
		// Send message to players
		if(worldConfig.unloadDelayMessage)
			WorldRestorerApi.sendMessageInWorld(loadAs, getPlugin().messages.aDelay.replace("{WORLD}",loadAs).replace("{DELAY}",Integer.toString(worldConfig.unloadDelay)));

		// Delay the load
		new BukkitRunnable() {
			@Override
			public void run() {
				loadWorld(sender, savedWorld, loadAs);
			}

		}.runTaskLater(plugin, 20 * worldConfig.unloadDelay);
	}

	private boolean loadWorld(CommandSender sender, String savedWorld, String loadAs)
	{
		// Clear players list
		WorldRestorerApi.clearWorldPlayers();

		// Commands to execute before unloading the world
		for(String command : worldConfig.unloadCommands)
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

		// Actions before loading the world
		if(worldConfig.unloadRemovePlayers)
			WorldRestorerApi.removePlayersFromWorld(loadAs);
		else if(worldConfig.unloadKickPlayers)
			WorldRestorerApi.kickPlayersFromWorld(loadAs, plugin.messages.sKickPlayer);
		else if(worldConfig.unloadSpawnPlayers && worldConfig.unloadSpawnLocation.getWorld()!=null)
			WorldRestorerApi.spawnPlayersInWorld(loadAs, worldConfig.unloadSpawnLocation);
		else
			WorldRestorerApi.removePlayersFromWorld(loadAs);

		// Load the world
		if(!WorldRestorerApi.loadWorld(getPlugin(), savedWorld, loadAs))
		{
			// Give a another chance to load the world
			if(!WorldRestorerApi.loadWorld(getPlugin(), savedWorld, loadAs))
			{
				// Give a another chance to load the world
				sendErrorMessage(sender, loadAs);
				return false;
			}
		}

		// Actions after loading the world
		if(worldConfig.loadForceSpawn && worldConfig.loadSpawnLocation.getWorld()!=null)
			WorldRestorerApi.spawnPlayersInWorld(loadAs, worldConfig.loadSpawnLocation);
		else if(worldConfig.loadRespawn)
			WorldRestorerApi.respawnPlayersInWorld(loadAs);

		// Commands to execute after loading the world
		for(String command : worldConfig.loadCommands)
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

		// Message
		EmptyMessage.toSender(sender);
		SimplePrefixedMessage titleMessage = new SimplePrefixedMessage(prefix, WorldRestorer.messages.sLoad.replace("{WORLD}",loadAs), ChatColor.GREEN);
		titleMessage.send(sender);

		return true;
	}

	private void sendErrorMessage(CommandSender sender, String worldName)
	{
		// Message
		WarningPrefixedMessage.toSender(sender, prefix, plugin.messages.eLoad.replace("{WORLD}",worldName));
	}
}
