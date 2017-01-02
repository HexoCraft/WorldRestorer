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
import com.github.hexocraft.worldrestorer.configuration.Permissions;
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.message.Message;
import com.github.hexosse.pluginframework.pluginapi.message.MessageTarget;
import org.bukkit.Bukkit;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandConfig extends PluginCommand<WorldRestorer>
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
		this.addSubCommand(new WrCommandConfigUnload(plugin));
		this.addSubCommand(new WrCommandConfigLoad(plugin));
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
		Message message = new Message();
		MessageTarget target = new MessageTarget(Bukkit.getConsoleSender()).add(commandInfo.getSender());
		message.setPrefix(WorldRestorer.messages.chatPrefix);
		message.add(new Message(WorldRestorer.messages.sConfig.replace("{WORLD}",worldName)));
		WorldRestorer.instance.messageManager.send(target, message);

	}





}
