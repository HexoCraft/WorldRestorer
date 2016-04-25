package com.github.hexosse.command;

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

import com.github.hexosse.WorldRestorer;
import com.github.hexosse.WorldRestorerApi;
import com.github.hexosse.command.ArgType.ArgTypeSavedWorld;
import com.github.hexosse.configuration.Permissions;
import com.github.hexosse.configuration.WorldConfig;
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandArgument;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.command.predifined.CommandHelp;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgTypeBoolean;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgTypeInteger;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgTypeStringList;

import java.util.List;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandConfigUnload extends PluginCommand<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandConfigUnload(WorldRestorer plugin)
    {
		super("unload", plugin);
        this.setDescription(plugin.messages.cConfigUnload);
        this.setPermission(Permissions.SAVE.toString());

		this.addSubCommand(new WrCommandConfigUnloadHelp(plugin));
		this.addSubCommand(new WrCommandConfigUnloadDelay(plugin));
		this.addSubCommand(new WrCommandConfigUnloadDelayMessage(plugin));
		this.addSubCommand(new WrCommandConfigUnloadRemovePlayers(plugin));
		this.addSubCommand(new WrCommandConfigUnloadKickPlayers(plugin));
		this.addSubCommand(new WrCommandConfigUnloadSpawnPlayers(plugin));
		this.addSubCommand(new WrCommandConfigUnloadSpawnLocation(plugin));
		this.addSubCommand(new WrCommandConfigUnloadAddCommand(plugin));
		this.addSubCommand(new WrCommandConfigUnloadRemoveCommand(plugin));
		this.addSubCommand(new WrCommandConfigUnloadRemoveAllCommands(plugin));
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
		plugin.getServer().dispatchCommand(commandInfo.getSender(), "WorldRestorer config unload help");

		return true;
	}


	public class WrCommandConfigUnloadHelp extends CommandHelp<WorldRestorer>
	{
		/**
		 * @param plugin The plugin that this object belong to.
		 */
		public WrCommandConfigUnloadHelp(WorldRestorer plugin)
		{
			super(plugin);
			//this.removeArgument("page");
			this.setDescription(plugin.messages.cConfigUnloadHelp);
			this.setPermission(Permissions.SAVE.toString());
		}
	}

	public class WrCommandConfigUnloadDelay extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigUnloadDelay(WorldRestorer plugin)
		{
			super("delay", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigUnloadDelayArgWorld));
			this.addArgument(new CommandArgument<Integer>("delay", ArgTypeInteger.get(), true, true, plugin.messages.cConfigUnloadDelayArgDelay));
			this.setDescription(plugin.messages.cConfigUnloadDelay);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");
			Integer delay = Integer.parseInt(commandInfo.getNamedArg("delay"));

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			worldConfig.unloadDelay = delay;
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigUnloadDelayMessage extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigUnloadDelayMessage(WorldRestorer plugin)
		{
			super("delaymessage", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigUnloadMessageArgWorld));
			this.addArgument(new CommandArgument<Boolean>("delay message", ArgTypeBoolean.get(), true, true, plugin.messages.cConfigUnloadMessageArgMessage));
			this.setDescription(plugin.messages.cConfigUnloadMessage);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");
			Boolean delayMessage = ArgTypeBoolean.get().get(commandInfo.getNamedArg("delay message"));

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			worldConfig.unloadDelayMessage = delayMessage;
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigUnloadRemovePlayers extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigUnloadRemovePlayers(WorldRestorer plugin)
		{
			super("removeplayers", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigUnloadRemoveArgWorld));
			this.addArgument(new CommandArgument<Boolean>("remove players", ArgTypeBoolean.get(), true, true, plugin.messages.cConfigUnloadRemoveArgRemove));
			this.setDescription(plugin.messages.cConfigUnloadRemove);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");
			Boolean removePlayers = ArgTypeBoolean.get().get(commandInfo.getNamedArg("remove players"));

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			if(removePlayers)
			{
				worldConfig.unloadRemovePlayers = true;
				worldConfig.unloadKickPlayers = false;
				worldConfig.unloadSpawnPlayers = false;
			}
			else
			{
				worldConfig.unloadRemovePlayers = false;
			}
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigUnloadKickPlayers extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigUnloadKickPlayers(WorldRestorer plugin)
		{
			super("kickplayers", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigUnloadkickArgWorld));
			this.addArgument(new CommandArgument<Boolean>("kick players", ArgTypeBoolean.get(), true, true, plugin.messages.cConfigUnloadkickArgKick));
			this.setDescription(plugin.messages.cConfigUnloadkick);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");
			Boolean kickPlayers = ArgTypeBoolean.get().get(commandInfo.getNamedArg("kick players"));

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			if(kickPlayers)
			{
				worldConfig.unloadRemovePlayers = false;
				worldConfig.unloadKickPlayers = true;
				worldConfig.unloadSpawnPlayers = false;
			}
			else
			{
				worldConfig.unloadKickPlayers = false;
			}
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigUnloadSpawnPlayers extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigUnloadSpawnPlayers(WorldRestorer plugin)
		{
			super("spawnplayers", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigUnloadSpawnArgWorld));
			this.addArgument(new CommandArgument<Boolean>("spawn players", ArgTypeBoolean.get(), true, true, plugin.messages.cConfigUnloadSpawnArgSpawn));
			this.setDescription(plugin.messages.cConfigUnloadSpawn);
			this.setPermission(Permissions.SAVE.toString());
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			// Get data from command line
			String savedWorld = commandInfo.getNamedArg("saved world");
			Boolean spawnPlayers = ArgTypeBoolean.get().get(commandInfo.getNamedArg("spawn players"));

			// Get the WorldConfig
			WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);

			//
			if(spawnPlayers)
			{
				worldConfig.unloadRemovePlayers = false;
				worldConfig.unloadKickPlayers = false;
				worldConfig.unloadSpawnPlayers = true;
			}
			else
			{
				worldConfig.unloadSpawnPlayers = false;
			}
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigUnloadSpawnLocation extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigUnloadSpawnLocation(WorldRestorer plugin)
		{
			super("spawnlocation", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigUnloadLocationArgWorld));
			this.setDescription(plugin.messages.cConfigUnloadLocation);
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
			worldConfig.unloadSpawnLocation = commandInfo.getPlayer().getLocation();
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigUnloadAddCommand extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigUnloadAddCommand(WorldRestorer plugin)
		{
			super("addcommand", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigUnloadAddCommandArgWorld));
			this.addArgument(new CommandArgument<List<String>>("add command", ArgTypeStringList.get(), true, true, plugin.messages.cConfigUnloadAddCommandArgCmd));
			this.setDescription(plugin.messages.cConfigUnloadAddCommand);
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
			worldConfig.unloadCommands.add(addCommand);
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigUnloadRemoveCommand extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigUnloadRemoveCommand(WorldRestorer plugin)
		{
			super("removecommand", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigUnloadRemoveCommandArgWorld));
			this.addArgument(new CommandArgument<List<String>>("remove command", ArgTypeStringList.get(), true, true, plugin.messages.cConfigUnloadRemoveCommandArgCmd));
			this.setDescription(plugin.messages.cConfigUnloadRemoveCommand);
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
			worldConfig.unloadCommands.remove(removeCommand);
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}


	public class WrCommandConfigUnloadRemoveAllCommands extends PluginCommand<WorldRestorer>
	{
		public WrCommandConfigUnloadRemoveAllCommands(WorldRestorer plugin)
		{
			super("removeallcommand", plugin);
			this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigUnloadRemoveAllCommandArgWorld));
			this.setDescription(plugin.messages.cConfigUnloadRemoveAllCommand);
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
			worldConfig.unloadCommands.clear();
			worldConfig.save();

			// Send message
			WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

			return true;
		}
	}
}
