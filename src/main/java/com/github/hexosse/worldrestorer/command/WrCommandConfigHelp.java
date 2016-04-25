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
import com.github.hexosse.worldrestorer.configuration.Permissions;
import com.github.hexosse.pluginframework.pluginapi.command.predifined.CommandHelp;

/**
 * This file is part WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandConfigHelp extends CommandHelp<WorldRestorer>
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WrCommandConfigHelp(WorldRestorer plugin)
    {
		super(plugin);
		this.setDescription(plugin.messages.cConfigHelp);
		this.setPermission(Permissions.SAVE.toString());
	}
}
