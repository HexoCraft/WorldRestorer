package com.github.hexocraft.worldrestorer.configuration;

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

import com.github.hexocraftapi.configuration.Configuration;
import com.github.hexocraftapi.configuration.annotation.ConfigFooter;
import com.github.hexocraftapi.configuration.annotation.ConfigHeader;
import com.github.hexocraftapi.configuration.annotation.ConfigPath;
import com.github.hexocraftapi.configuration.annotation.ConfigValue;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

/**
 * This file is part of WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */

@ConfigHeader(comment = {
"# ===--- WorldRestorer -------------------------------------------------------------------------------------------=== #",
"#                                                                                                                      ",
"#     Save, load, reset any worlds.                                                                                    ",
"#     Change spawn, add commands and more options per world.                                                           ",
"#     Fully integrated with Multiverse and fully configurable.                                                         ",
"#                                                                                                                      ",
"# ===------------------------------------------------------------------------------------------ © 2017 Hexosse ---=== #"
})
@ConfigFooter(comment = {
" ",
"# ===--- Enjoy -------------------------------------------------------------------------------- © 2017 Hexosse ---=== #"
})

public class WorldConfig extends Configuration
{
    /* Plugin */
    @ConfigPath(path = "world", comment = "Data about the saved world")
    @ConfigValue(path = "world.name") public               String  name;
    @ConfigValue(path = "world.seed") public               long    seed;
    @ConfigValue(path = "world.environment") public        String  environment;
    @ConfigValue(path = "world.generator") public          String  generator;
    @ConfigValue(path = "world.type") public               String  type;
    @ConfigValue(path = "world.generateStructures") public boolean generateStructures;
    @ConfigValue(path = "world.generatorSettings") public  String  generatorSettings;

	@ConfigPath(
	comment = {"Actions to perform before unloading the world"
			, " - delay (in seconds) : delay before unloading the world"
			, " - remove_players : if true players will be respawed to main world"
			, " - kick_players : if true players will be kicked from the server"
			, " - spawn_players : if true players will be respawed at location specified in spawn_location"
			, " - spawn_location : The location to respawn players if spawn_players is set to true"
			, " - commands : List of server commands to execute before unloading the world."
			/*, "              You can use the {WORLD} variable which will be replace by the name"
			, "              of the world that will be loaded."*/}
	, path = "unload")
	@ConfigValue(path = "unload.delay")				public int unloadDelay = 0;
	@ConfigValue(path = "unload.delay_message")		public boolean unloadDelayMessage = false;
	@ConfigValue(path = "unload.remove_players")	public boolean unloadRemovePlayers = true;
	@ConfigValue(path = "unload.kick_players")		public boolean unloadKickPlayers = false;
	@ConfigValue(path = "unload.spawn_players")		public boolean unloadSpawnPlayers = false;
	@ConfigValue(path = "unload.spawn_location")	public Location unloadSpawnLocation;
	@ConfigValue(path = "unload.commands")			public ArrayList<String> unloadCommands = new ArrayList<String>();

	@ConfigPath(
	comment = {"Actions to perform after loading the world"
			, " - respawn : respawn the players in the world after the reload"
			, " - force_spawn : if true players will be respawed at location specified in spawn_location"
			, " - spawn_location : The location to respawn players if force_spawn is set to true"
			, " - commands : List of server commands to execute after unloading the world."
			/*, "              You can use the {WORLD} variable which will be replace by the name"
			, "              of the world that will be loaded."*/}
	, path = "load")
	@ConfigValue(path = "load.respawn")				public boolean loadRespawn = false;
	@ConfigValue(path = "load.force_spawn")			public boolean loadForceSpawn = false;
	@ConfigValue(path = "load.spawn_location")		public Location loadSpawnLocation;
	@ConfigValue(path = "load.commands")			public ArrayList<String> loadCommands = new ArrayList<String>();


	public WorldConfig(JavaPlugin plugin, String fileName, boolean load)
	{
		super(plugin, fileName);

		if(load) load();
	}

	public WorldConfig(JavaPlugin plugin, File dataFolder, String filename, boolean load)
	{
		super(plugin, new File(dataFolder, filename));

		if(load) load();
	}

	public WorldCreator getWorldCreator()
	{
		return getWorldCreator(this.name);
	}

	public WorldCreator getWorldCreator(String worldName)
	{
		final WorldCreator worldCreator = WorldCreator.name(worldName);
		worldCreator.seed(this.seed);
		worldCreator.environment(World.Environment.valueOf(this.environment));
		worldCreator.generator(this.generator.isEmpty()?null:this.generator);
		worldCreator.type(WorldType.valueOf(this.type));
		worldCreator.generateStructures(this.generateStructures);
		worldCreator.generatorSettings(generatorSettings);
		return worldCreator;
	}
}
