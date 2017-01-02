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
import org.bukkit.plugin.java.JavaPlugin;

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

public class Messages extends Configuration
{
    /* Chat */
    @ConfigPath(path = "chat")
    @ConfigValue(path = "chat.prefix")						public String chatPrefix = "§3[§bWorldRestorer§3]§r";

	/* Commands */
	@ConfigPath(path = "commands", 		comment = "List of messages used in commands")
	@ConfigValue(path = "commands.help.cmd")					            public String cHelp = "Display plugin help";
	@ConfigValue(path = "commands.save.cmd")					            public String cSave = "Save a world";
	@ConfigValue(path = "commands.save.arg_world")			                public String cSaveArgWorld = "World to save";
	@ConfigValue(path = "commands.save.arg_world_as")			            public String cSaveArgWorldAs = "Saved world name";
	@ConfigValue(path = "commands.load.cmd")					            public String cLoad = "Load a world";
	@ConfigValue(path = "commands.load.arg_world")			                public String cLoadArgWorld = "Saved world to load";
	@ConfigValue(path = "commands.load.arg_world_as")			            public String cLoadArgWorldAs = "Name of the loaded world";
	@ConfigValue(path = "commands.reset.cmd")					            public String cReset = "Reset a world";
	@ConfigValue(path = "commands.reset.arg_world")			                public String cResetArgWorld = "World to reset";
	@ConfigValue(path = "commands.delete.cmd")				                public String cDelete = "Delete a saved world";
	@ConfigValue(path = "commands.delete.arg_world")			            public String cDeleteArgWorld = "Saved world to delete";
	@ConfigValue(path = "commands.list.cmd")					            public String cList = "List saved worlds";
	@ConfigValue(path = "commands.kick.cmd")					            public String cKick = "Kick all players";
	@ConfigValue(path = "commands.kick.arg_world")			                public String cKickArgWorld = "World where players will be kicked";
	@ConfigValue(path = "commands.remove.cmd")				                public String cRemove = "Remove all players";
	@ConfigValue(path = "commands.remove.arg_world")			            public String cRemoveArgWorld = "World where players will be removed";
	@ConfigValue(path = "commands.respawn.cmd")				                public String cRespawn = "Respawn all players";
	@ConfigValue(path = "commands.respawn.arg_world")			            public String cRespawnArgWorld = "World where players will be respawned";
	@ConfigValue(path = "commands.config.cmd")								public String cConfig = "Configure the saved world";
	@ConfigValue(path = "commands.config.help.cmd")							public String cConfigHelp = "Display config help";
	@ConfigValue(path = "commands.config.unload.cmd")						public String cConfigUnload = "Config unload options";
	@ConfigValue(path = "commands.config.unload.help.cmd")					public String cConfigUnloadHelp = "Config unload help";
	@ConfigValue(path = "commands.config.unload.delay.cmd")					public String cConfigUnloadDelay = "Delay before unloading";
	@ConfigValue(path = "commands.config.unload.delay.arg_world")			public String cConfigUnloadDelayArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.unload.delay.arg_delay")			public String cConfigUnloadDelayArgDelay = "tehe delay";
	@ConfigValue(path = "commands.config.unload.message.cmd")				public String cConfigUnloadMessage = "Messsage when unload is delayed";
	@ConfigValue(path = "commands.config.unload.message.arg_world")			public String cConfigUnloadMessageArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.unload.message.arg_message")		public String cConfigUnloadMessageArgMessage = "Messgae to send";
	@ConfigValue(path = "commands.config.unload.remove.cmd")				public String cConfigUnloadRemove = "Remove players";
	@ConfigValue(path = "commands.config.unload.remove.arg_world")			public String cConfigUnloadRemoveArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.unload.remove.arg_remove")			public String cConfigUnloadRemoveArgRemove = "True or False";
	@ConfigValue(path = "commands.config.unload.kick.cmd")					public String cConfigUnloadkick = "Kick players";
	@ConfigValue(path = "commands.config.unload.kick.arg_world")			public String cConfigUnloadkickArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.unload.kick.arg_Kick")				public String cConfigUnloadkickArgKick = "True or False";
	@ConfigValue(path = "commands.config.unload.spawn.cmd")					public String cConfigUnloadSpawn = "Spawn players";
	@ConfigValue(path = "commands.config.unload.spawn.arg_world")			public String cConfigUnloadSpawnArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.unload.spawn.arg_spawn")			public String cConfigUnloadSpawnArgSpawn = "Kick players";
	@ConfigValue(path = "commands.config.unload.location.cmd")				public String cConfigUnloadLocation = "Spawn location";
	@ConfigValue(path = "commands.config.unload.location.arg_world")		public String cConfigUnloadLocationArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.unload.add_command.cmd")			public String cConfigUnloadAddCommand = "Add a command";
	@ConfigValue(path = "commands.config.unload.add_command.arg_world")		public String cConfigUnloadAddCommandArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.unload.add_command.arg_cmd")		public String cConfigUnloadAddCommandArgCmd= "Command";
	@ConfigValue(path = "commands.config.unload.remove_command.cmd")		public String cConfigUnloadRemoveCommand = "Remove a command";
	@ConfigValue(path = "commands.config.unload.remove_command.arg_world")	public String cConfigUnloadRemoveCommandArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.unload.remove_command.arg_cmd")	public String cConfigUnloadRemoveCommandArgCmd = "Command";
	@ConfigValue(path = "commands.config.unload.remove_all_command.cmd")	public String cConfigUnloadRemoveAllCommand = "Remove all commands";
	@ConfigValue(path = "commands.config.unload.remove_all_command.arg_world")public String cConfigUnloadRemoveAllCommandArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.load.cmd")							public String cConfigLoad = "Config load options";
	@ConfigValue(path = "commands.config.load.help.cmd")					public String cConfigLoadHelp = "Config load help";
	@ConfigValue(path = "commands.config.load.respawn.cmd")					public String cConfigLoadRespawn = "Respawn command";
	@ConfigValue(path = "commands.config.load.respawn.arg_world")			public String cConfigLoadRespawnArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.load.respawn.arg_respawn")			public String cConfigLoadRespawnArgRespawn = "True or False";
	@ConfigValue(path = "commands.config.load.force_spawn.cmd")				public String cConfigLoadForceSpawn = "Force the spawn location";
	@ConfigValue(path = "commands.config.load.force_spawn.arg_world")		public String cConfigLoadForceSpawnArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.load.force_spawn.arg_spawn")		public String cConfigLoadForceSpawnArgSpawn = "True or False";
	@ConfigValue(path = "commands.config.load.spawn_location.cmd")			public String cConfigLoadSpawnLocation = "Spawn location";
	@ConfigValue(path = "commands.config.load.spawn_location.arg_world")	public String cConfigLoadSpawnLocationArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.load.add_command.cmd")				public String cConfigLoadAddCommand = "Add a command";
	@ConfigValue(path = "commands.config.load.add_command.arg_world")		public String cConfigLoadAddCommandArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.load.add_command.arg_cmd")			public String cConfigLoadAddCommandArgCmd = "Command";
	@ConfigValue(path = "commands.config.load.remove_command.cmd")			public String cConfigLoadRemoveCommand = "Remove a command";
	@ConfigValue(path = "commands.config.load.remove_command.arg_world")	public String cConfigLoadRemoveCommandArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.load.remove_command.arg_cmd")		public String cConfigLoadRemoveCommandArgCmd = "Command";
	@ConfigValue(path = "commands.config.load.remove_all_command.cmd")		public String cConfigLoadRemoveAllCommand = "Remove all commands";
	@ConfigValue(path = "commands.config.load.remove_all_command.arg_world")public String cConfigLoadRemoveAllCommandArgWorld = "The world to configure";
	@ConfigValue(path = "commands.config.reload.cmd")			            public String cConfigReload = "Reload a config";
	@ConfigValue(path = "commands.config.reload.arg_world")                 public String cConfigReloadArgWorld = "The world config to reload";
	@ConfigValue(path = "commands.relaod.cmd")				                public String cReload = "Reload Worldrestorer";

	/* Success */
	@ConfigPath(path = "success", 		comment = "List of messages used after a sucess command")
	@ConfigValue(path = "success.save")						public String sSave = "The world {WORLD} has been saved";
	@ConfigValue(path = "success.load")						public String sLoad = "The world {WORLD} has been loaded";
	@ConfigValue(path = "success.delete")					public String sDelete = "The world {WORLD} has been deleted";
	@ConfigValue(path = "success.list")						public String sList = "List of saved worlds";
	@ConfigValue(path = "success.kick")						public String sKick = "Players have been kick from world {WORLD} !";
	@ConfigValue(path = "success.kick_player")				public String sKickPlayer = "You've been kicked !";
	@ConfigValue(path = "success.remove")					public String sRemove = "Players have been removed from world {WORLD} !";
	@ConfigValue(path = "success.respawn")					public String sRespawn = "Players have been respawned in world {WORLD} !";
	@ConfigValue(path = "success.config")					public String sConfig = "The config for world {WORLD} as been updated";
	@ConfigValue(path = "success.reload")					public String sReload = "WorldRestorer has been sReload";

	/* Errors */
	@ConfigPath(path = "errors", 		comment = "List of messages used after a error command")
	@ConfigValue(path = "errors.save")						public String eSave = "Error while saving the world {WORLD}";
	@ConfigValue(path = "errors.load")						public String eLoad = "Error while loading the world {WORLD}";
	@ConfigValue(path = "errors.delete")					public String eDelete = "Error while deleting the world {WORLD}";
	@ConfigValue(path = "errors.list")						public String eList = "List of saved worlds is empty";

	/* Annonce */
    @ConfigPath(path = "annonces", 		comment = "List of messages used for annoncement")
	@ConfigValue(path = "annonces.delay")					public String aDelay = "The world {WORLD} will be sReload in {DELAY} seconds";


    public Messages(JavaPlugin plugin, String fileName, boolean load)
    {
	    super(plugin, fileName);

	    if(load) load();
    }
}
