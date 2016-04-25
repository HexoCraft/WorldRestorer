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
import com.github.hexosse.worldrestorer.configuration.Permissions;
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.message.Message;
import com.github.hexosse.pluginframework.pluginapi.message.MessageTarget;
import org.bukkit.Bukkit;

import java.util.ArrayList;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandList extends PluginCommand<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandList(WorldRestorer plugin)
    {
        super("list", plugin);
        this.setDescription(plugin.messages.cList);
        this.setPermission(Permissions.LIST.toString());
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
        // List of saved worlds
        final ArrayList<String> savedWorlds = WorldRestorerApi.listWorlds(getPlugin()
		);

		// Message
		Message message = new Message();
		MessageTarget target = new MessageTarget(Bukkit.getConsoleSender()).add(commandInfo.getSender());
		//message.setPrefix(plugin.messages.chatPrefix);

		if(savedWorlds==null)
			message.add(new Message(plugin.messages.eList));
		else
		{
			message.add(new Message(plugin.messages.sList));
			for(String worldName : savedWorlds)
				message.add(new Message(" - " + worldName));
		}
		messageManager.send(target, message);

        return true;
    }
}
