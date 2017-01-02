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
import com.github.hexocraft.worldrestorer.configuration.Permissions;
import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.message.predifined.message.EmptyMessage;
import com.github.hexocraftapi.message.predifined.message.SimplePrefixedMessage;
import org.bukkit.ChatColor;

import static com.github.hexocraft.worldrestorer.command.WrCommands.prefix;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandConfig extends Command<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandConfig(WorldRestorer plugin)
    {
		super("config", plugin);
        this.setDescription(plugin.messages.cConfig);
        this.setPermission(Permissions.SAVE.toString());

		this.addSubCommand(new WrCommandConfigHelp(plugin));
		this.addSubCommand(new WrCommandConfigLoad(plugin));
	    this.addSubCommand(new WrCommandConfigUnload(plugin));
	    this.addSubCommand(new WrCommandConfigReload(plugin));
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
		plugin.getServer().dispatchCommand(commandInfo.getSender(), "WorldRestorer config help");

		return true;
	}

	public static void sendConfigSavedMessage(CommandInfo commandInfo, String worldName)
	{
		// Message
		EmptyMessage.toSender(commandInfo.getPlayer());
		SimplePrefixedMessage titleMessage = new SimplePrefixedMessage(prefix, WorldRestorer.messages.sSave.replace("{WORLD}",worldName), ChatColor.GREEN);
		titleMessage.send(commandInfo.getSenders());
	}
}
