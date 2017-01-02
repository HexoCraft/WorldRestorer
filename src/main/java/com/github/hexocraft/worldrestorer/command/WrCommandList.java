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
import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.message.Sentence;
import com.github.hexocraftapi.message.predifined.line.Title;
import com.github.hexocraftapi.message.predifined.message.EmptyMessage;
import com.github.hexocraftapi.message.predifined.message.SimpleMessage;
import com.github.hexocraftapi.message.predifined.message.TitleMessage;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import java.util.ArrayList;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandList extends Command<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandList(WorldRestorer plugin)
    {
        super("list", plugin);
		this.setAliases(Lists.newArrayList("l"));
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
        final ArrayList<String> savedWorlds = WorldRestorerApi.listWorlds(getPlugin());

	    // Empty
	    EmptyMessage.toSender(commandInfo.getPlayer());

	    // Title line
	    Title title = new Title('-', ChatColor.AQUA, new Sentence(savedWorlds==null ? plugin.messages.eList : plugin.messages.sList, ChatColor.YELLOW));
	    TitleMessage.toPlayer(commandInfo.getPlayer(), title);

	    //
	    if(savedWorlds==null) return true;

	    //
	    for(String worldName : savedWorlds)
		    SimpleMessage.toPlayer(commandInfo.getPlayer(), " - " + worldName);

        return true;
    }
}
