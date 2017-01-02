package com.github.hexocraft.worldrestorer.command;

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

import com.github.hexocraft.worldrestorer.WorldRestorer;
import com.github.hexocraft.worldrestorer.WorldRestorerApi;
import com.github.hexocraft.worldrestorer.command.ArgType.ArgTypeSavedWorld;
import com.github.hexocraft.worldrestorer.configuration.Permissions;
import com.github.hexocraft.worldrestorer.configuration.WorldConfig;
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandArgument;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandConfigReload extends PluginCommand<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandConfigReload(WorldRestorer plugin)
    {
		super("reload", plugin);
		this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cConfigReloadArgWorld));
		this.setDescription(plugin.messages.cConfigReload);
        this.setPermission(Permissions.SAVE.toString());
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

		// Get the WorldConfig
		WorldConfig worldConfig = WorldRestorerApi.getWorldConfig(getPlugin(), savedWorld);
		worldConfig.save();

		// Send message
		WrCommandConfig.sendConfigSavedMessage(commandInfo, savedWorld);

		return true;
	}

}
