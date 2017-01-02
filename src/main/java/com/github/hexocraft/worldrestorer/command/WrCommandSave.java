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
import com.github.hexocraftapi.command.CommandArgument;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.type.ArgTypeString;
import com.github.hexocraftapi.command.type.ArgTypeWorld;
import com.github.hexocraftapi.message.predifined.message.EmptyMessage;
import com.github.hexocraftapi.message.predifined.message.SimplePrefixedMessage;
import com.github.hexocraftapi.message.predifined.message.WarningPrefixedMessage;
import org.bukkit.ChatColor;
import org.bukkit.World;

import static com.github.hexocraft.worldrestorer.command.WrCommands.prefix;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandSave extends Command<WorldRestorer>
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
			WarningPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.eSave.replace("{WORLD}",saveas));
			return false;
		}

        // Message
	    EmptyMessage.toSender(commandInfo.getPlayer());
	    SimplePrefixedMessage titleMessage = new SimplePrefixedMessage(prefix, plugin.messages.sSave.replace("{WORLD}",saveas), ChatColor.GREEN);
	    titleMessage.send(commandInfo.getSenders());

        return true;
    }
}
