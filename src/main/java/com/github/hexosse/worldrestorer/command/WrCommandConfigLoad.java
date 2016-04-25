package com.github.hexosse.worldrestorer.command;

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

import com.github.hexosse.worldrestorer.WorldRestorer;
import com.github.hexosse.worldrestorer.WorldRestorerApi;
import com.github.hexosse.worldrestorer.command.ArgType.ArgTypeSavedWorld;
import com.github.hexosse.worldrestorer.configuration.Permissions;
import com.github.hexosse.worldrestorer.configuration.WorldConfig;
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandArgument;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.command.predifined.CommandHelp;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgTypeBoolean;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgTypeStringList;

import java.util.List;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandConfigLoad extends PluginCommand<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandConfigLoad(WorldRestorer plugin)
    {
		super("load", plugin);
        this.setDescription(plugin.messages.cConfigLoad);
        this.setPermission(Permissions.SAVE.toString());

		this.addSubCommand(new WrCommandConfigLoadHelp(plugin));
		this.addSubCommand(new WrCommandConfigLoadRespawn(plugin));
		this.addSubCommand(new WrCommandConfigLoadForceSpawn(plugin));
		this.addSubCommand(new WrCommandConfigLoadSpawnLocation(plugin));
		this.addSubCommand(new WrCommandConfigLoadAddCommand(plugin));
		this.addSubCommand(new WrCommandConfigLoadRemoveCommand(plugin));
		this.addSubCommand(new WrCommandConfigLoadRemoveAllCommands(plugin));
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
		plugin.getServer().dispatchCommand(commandInfo.getSender(), "WorldRestorer config load help");

		return true;
	}


	public class WrCommandConfigLoadHelp extends CommandHelp<WorldRestorer>
	{
		/**
		 * @param plugin The plugin that this object belong to.
		 */
		public WrCommandConfigLoadHelp(WorldRestorer plugin)
		{
			super(plugin);
			//this.removeArgument("page");
			this.setDescription(plugin.messages.cConfigLoadHelp);
			this.setPermission(Permissions.SAVE.toString());
		}
	}


	public class WrCommandConfigLoadRespawn extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigLoadRespawn(WorldRestorer plugin)
		{
			super("respawn", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigLoadRespawnArgWorld));
			this.addArgument(new CommandArgument<Boolean>("respawn", ArgTypeBoolean.get(), true, true, plugin.messages.cConfigLoadRespawnArgRespawn));
			this.setDescription(plugin.messages.cConfigLoadRespawn);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");
			Boolean kickPlayers = ArgTypeBoolean.get().get(commandInfo.getNamedArg("respawn"));

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			if(kickPlayers)
			{
				worldConfig.loadRespawn = true;
				worldConfig.loadForceSpawn = false;
			}
			else
			{
				worldConfig.loadRespawn = false;
			}
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigLoadForceSpawn extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigLoadForceSpawn(WorldRestorer plugin)
		{
			super("forcespawn", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigLoadForceSpawnArgWorld));
			this.addArgument(new CommandArgument<Boolean>("force spawn", ArgTypeBoolean.get(), true, true, plugin.messages.cConfigLoadForceSpawnArgSpawn));
			this.setDescription(plugin.messages.cConfigLoadForceSpawn);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");
			Boolean spawnPlayers = ArgTypeBoolean.get().get(commandInfo.getNamedArg("force spawn"));

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			if(spawnPlayers)
			{
				worldConfig.loadRespawn = false;
				worldConfig.loadForceSpawn = true;
			}
			else
			{
				worldConfig.loadForceSpawn = false;
			}
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigLoadSpawnLocation extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigLoadSpawnLocation(WorldRestorer plugin)
		{
			super("spawnlocation", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigLoadSpawnLocationArgWorld));
			this.setDescription(plugin.messages.cConfigLoadSpawnLocation);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");

			// Get the player location
			if(commandInfo.getPlayer()==null) return false;

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			worldConfig.loadSpawnLocation = commandInfo.getPlayer().getLocation();
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigLoadAddCommand extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigLoadAddCommand(WorldRestorer plugin)
		{
			super("addcommand", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigLoadAddCommandArgWorld));
			this.addArgument(new CommandArgument<List<String>>("add command", ArgTypeStringList.get(), true, true, plugin.messages.cConfigLoadAddCommandArgCmd));
			this.setDescription(plugin.messages.cConfigLoadAddCommand);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");
			String addCommand = commandInfo.getNamedArg("add command");

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			worldConfig.loadCommands.add(addCommand);
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigLoadRemoveCommand extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigLoadRemoveCommand(WorldRestorer plugin)
		{
			super("removecommand", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigLoadRemoveCommandArgWorld));
			this.addArgument(new CommandArgument<List<String>>("remove command", ArgTypeStringList.get(), true, true, plugin.messages.cConfigLoadRemoveCommandArgCmd));
			this.setDescription(plugin.messages.cConfigLoadRemoveCommand);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");
			String removeCommand = commandInfo.getNamedArg("remove command");

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			worldConfig.loadCommands.remove(removeCommand);
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigLoadRemoveAllCommands extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigLoadRemoveAllCommands(WorldRestorer plugin)
		{
			super("removeallcommand", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigLoadRemoveAllCommandArgWorld));
			this.setDescription(plugin.messages.cConfigLoadRemoveAllCommand);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			worldConfig.loadCommands.clear();
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}
}
