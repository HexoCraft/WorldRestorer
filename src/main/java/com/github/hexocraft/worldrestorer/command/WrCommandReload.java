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
import com.github.hexocraft.worldrestorer.configuration.Messages;
import com.github.hexocraft.worldrestorer.configuration.Permissions;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.command.predifined.CommandReload;
import com.github.hexosse.pluginframework.pluginapi.message.Message;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This file is part of WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */
public class WrCommandReload extends CommandReload<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandReload(WorldRestorer plugin)
    {
        super(plugin, Permissions.ADMIN.toString());
        this.setDescription(plugin.messages.cReload);
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
        final Player player = commandInfo.getPlayer();

        super.onCommand(commandInfo);

        new BukkitRunnable()
        {
            @Override
            public void run()
            {

                plugin.config.reloadConfig();

                plugin.messages = new Messages(plugin, plugin.getDataFolder(), plugin.config.messages);
                plugin.messages.reloadConfig();

				// Log
                pluginLogger.info(plugin.messages.sReload);

				// Message
				Message message = new Message();
				message.setPrefix(plugin.messages.chatPrefix);
				message.add(plugin.messages.sReload);
				messageManager.send(player, message);
            }

        }.runTask(plugin);

        return true;
    }
}
