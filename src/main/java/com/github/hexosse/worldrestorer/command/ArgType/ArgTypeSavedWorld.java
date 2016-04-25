package com.github.hexosse.worldrestorer.command.ArgType;

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
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgType;
import com.google.common.collect.ImmutableList;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class ArgTypeSavedWorld implements ArgType<String>
{
	private ArgTypeSavedWorld() {};
	private static ArgTypeSavedWorld t = new ArgTypeSavedWorld();
	public static ArgTypeSavedWorld get() { return t; }

	@Override
	public boolean check(String world)
	{
		// List of saved worlds
		final ArrayList<String> savedWorlds = WorldRestorerApi.listWorlds(WorldRestorer.instance);

		return savedWorlds == null ? false : savedWorlds.contains(world);
	}

	@Override
	public String get(String world)
	{
		try
		{
			return check(world) ? world : "";
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public List<String> tabComplete(CommandInfo commandInfo)
	{
		// Last word
		String lastWord = commandInfo.numArgs() == 0 ? "" : commandInfo.getArgs().get(commandInfo.numArgs()-1);

		// List of saved worlds
		final ArrayList<String> savedWorlds = WorldRestorerApi.listWorlds(WorldRestorer.instance);
		if(savedWorlds==null)
			return ImmutableList.of();

		ArrayList<String> matchedWorlds = new ArrayList<String>();
		for(String savedWorld : savedWorlds)
		{
			if(StringUtil.startsWithIgnoreCase(savedWorld, lastWord))
				matchedWorlds.add(savedWorld);
		}

		return matchedWorlds;
	}
}
