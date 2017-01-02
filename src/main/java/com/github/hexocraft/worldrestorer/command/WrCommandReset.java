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
import com.github.hexocraft.worldrestorer.configuration.Permissions;
import com.github.hexocraft.worldrestorer.configuration.WorldConfig;
import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandArgument;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.type.ArgTypeWorld;
import org.bukkit.World;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandReset extends Command<WorldRestorer>
{
	private WorldConfig worldConfig = null;

	/**
	 * @param plugin The plugin that this object belong to.
	 */
	public WrCommandReset(WorldRestorer plugin)
	{
		super("reset", plugin);
		this.addArgument(new CommandArgument<World>("world", ArgTypeWorld.get(), true, true, plugin.messages.cResetArgWorld));
		this.setDescription(plugin.messages.cReset);
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
		String world = commandInfo.getNamedArg("world");

		// Check if a saved world exist
		if(!WorldRestorerApi.exist(getPlugin(), world))
			WorldRestorerApi.saveWorld(getPlugin(), world, world);

		// Load the world
		plugin.getServer().dispatchCommand(commandInfo.getSender(), "WorldRestorer load " + world + " " + world);

		return true;
	}
}
