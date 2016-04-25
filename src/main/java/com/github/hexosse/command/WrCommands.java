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
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.google.common.collect.Lists;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommands extends PluginCommand<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommands(WorldRestorer plugin)
    {
        super("WorldRestorer", plugin);
        this.setAliases(Lists.newArrayList("wr"));

        this.addSubCommand(new WrCommandHelp(plugin));
        this.addSubCommand(new WrCommandSave(plugin));
        this.addSubCommand(new WrCommandLoad(plugin));
        this.addSubCommand(new WrCommandReset(plugin));
        this.addSubCommand(new WrCommandDelete(plugin));
        this.addSubCommand(new WrCommandList(plugin));
        this.addSubCommand(new WrCommandKickPlayers(plugin));
        this.addSubCommand(new WrCommandRemovePlayers(plugin));
        this.addSubCommand(new WrCommandRespawnPlayers(plugin));
        this.addSubCommand(new WrCommandConfig(plugin));
        this.addSubCommand(new WrCommandReload(plugin));
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
        plugin.getServer().dispatchCommand(commandInfo.getSender(), "WorldRestorer help");

        return true;
    }
}
