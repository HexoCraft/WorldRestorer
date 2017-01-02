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
import com.github.hexocraft.worldrestorer.command.ArgType.ArgTypeSavedWorld;
import com.github.hexocraft.worldrestorer.configuration.Permissions;
import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandArgument;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.message.predifined.message.EmptyMessage;
import com.github.hexocraftapi.message.predifined.message.SimplePrefixedMessage;
import com.github.hexocraftapi.message.predifined.message.WarningPrefixedMessage;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import static com.github.hexocraft.worldrestorer.command.WrCommands.prefix;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandDelete extends Command<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandDelete(WorldRestorer plugin)
    {
        super("delete", plugin);
        this.setAliases(Lists.newArrayList("del"));
        this.addArgument(new CommandArgument<String>("saved world", ArgTypeSavedWorld.get(), true, true, plugin.messages.cDeleteArgWorld));
        this.setDescription(plugin.messages.cDelete);
        this.setPermission(Permissions.DELETE.toString());
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
        String worldName = commandInfo.getNamedArg("saved world");

        // Delete the world
        if(!WorldRestorerApi.deleteWorldSave(getPlugin(), worldName))
        {
            WarningPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.eDelete.replace("{WORLD}",worldName));
            return false;
        }

        // Message
        EmptyMessage.toSender(commandInfo.getPlayer());
        SimplePrefixedMessage titleMessage = new SimplePrefixedMessage(prefix, WorldRestorer.messages.sDelete.replace("{WORLD}",worldName), ChatColor.GREEN);
        titleMessage.send(commandInfo.getSenders());

        return true;
    }
}
