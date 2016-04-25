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
import com.github.hexosse.configuration.Permissions;
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandArgument;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgTypeString;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgTypeWorld;
import com.github.hexosse.pluginframework.pluginapi.message.Message;
import com.github.hexosse.pluginframework.pluginapi.message.MessageSeverity;
import com.github.hexosse.pluginframework.pluginapi.message.MessageTarget;
import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandSave extends PluginCommand<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandSave(WorldRestorer plugin)
    {
        super("save", plugin);
		this.addArgument(new CommandArgument<World>("world", ArgTypeWorld.get(), true, true, plugin.messages.cSaveArgWorld));
		this.addArgument(new CommandArgument<String>("save as", ArgTypeString.get(), false, false, plugin.messages.cSaveArgWorldAs));
        this.setDescription(plugin.messages.cSave);
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
		String worldName = commandInfo.getNamedArg("world");
		String saveas = commandInfo.getNamedArg("save as");

		if(saveas==null) saveas = worldName;

		if(!WorldRestorerApi.saveWorld(getPlugin(), worldName, saveas))
		{
			// Message
			Message message = new Message(MessageSeverity.ERROR);
			MessageTarget target = new MessageTarget(Bukkit.getConsoleSender()).add(commandInfo.getSender());
			message.setPrefix(plugin.messages.chatPrefix);
			message.add(new Message(plugin.messages.eSave.replace("{WORLD}",saveas)));
			messageManager.send(target, message);

			return false;
		}

        // Message
        Message message = new Message();
        MessageTarget target = new MessageTarget(Bukkit.getConsoleSender()).add(commandInfo.getSender());
        message.setPrefix(plugin.messages.chatPrefix);
        message.add(new Message(plugin.messages.sSave.replace("{WORLD}",saveas)));
        messageManager.send(target, message);

        return true;
    }
}
