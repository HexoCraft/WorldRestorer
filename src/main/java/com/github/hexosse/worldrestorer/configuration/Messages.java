package com.github.hexosse.worldrestorer.configuration;

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
import com.github.hexosse.pluginframework.pluginapi.config.ConfigFile;

import java.io.File;

/**
 * This file is part of WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */

@ConfigFile.ConfigHeader(comment = {
        "############################################################",
        "# |   WorldRestorer by hexosse                           | #",
        "############################################################"
})
@ConfigFile.ConfigFooter(comment = {
        " ",
        " ",
        "############################################################"
})

public class Messages extends ConfigFile<WorldRestorer>
{
    /* Chat */
    @ConfigComment(path = "chat")
    @ConfigOptions(path = "chat.prefix")						public String chatPrefix = "§3[§bWorldRestorer§3]§r";

	/* Commands */
	@ConfigComment(path = "commands", 		comment = "List of messages used in commands")
	@ConfigOptions(path = "commands.help.cmd")					public String cHelp = "Display plugin help";
	@ConfigOptions(path = "commands.save.cmd")					public String cSave = "Save a world";
	@ConfigOptions(path = "commands.save.arg_world")			public String cSaveArgWorld = "World to save";
	@ConfigOptions(path = "commands.save.arg_world_as")			public String cSaveArgWorldAs = "Saved world name";
	@ConfigOptions(path = "commands.load.cmd")					public String cLoad = "Load a world";
	@ConfigOptions(path = "commands.load.arg_world")			public String cLoadArgWorld = "Saved world to load";
	@ConfigOptions(path = "commands.load.arg_world_as")			public String cLoadArgWorldAs = "Name of the loaded world";
	@ConfigOptions(path = "commands.reset.cmd")					public String cReset = "Reset a world";
	@ConfigOptions(path = "commands.reset.arg_world")			public String cResetArgWorld = "World to reset";
	@ConfigOptions(path = "commands.delete.cmd")				public String cDelete = "Delete a saved world";
	@ConfigOptions(path = "commands.delete.arg_world")			public String cDeleteArgWorld = "Saved world to delete";
	@ConfigOptions(path = "commands.list.cmd")					public String cList = "List saved worlds";
	@ConfigOptions(path = "commands.kick.cmd")					public String cKick = "Kick all players";
	@ConfigOptions(path = "commands.kick.arg_world")			public String cKickArgWorld = "World where players will be kicked";
	@ConfigOptions(path = "commands.remove.cmd")				public String cRemove = "Remove all players";
	@ConfigOptions(path = "commands.remove.arg_world")			public String cRemoveArgWorld = "World where players will be removed";
	@ConfigOptions(path = "commands.respawn.cmd")				public String cRespawn = "Respawn all players";
	@ConfigOptions(path = "commands.respawn.arg_world")			public String cRespawnArgWorld = "World where players will be respawned";
	@ConfigOptions(path = "commands.config.cmd")								public String cConfig = "Configure the saved world";
	@ConfigOptions(path = "commands.config.help.cmd")							public String cConfigHelp = "Display config help";
	@ConfigOptions(path = "commands.config.unload.cmd")							public String cConfigUnload = "Config unload options";
	@ConfigOptions(path = "commands.config.unload.help.cmd")					public String cConfigUnloadHelp = "Config unload help";
	@ConfigOptions(path = "commands.config.unload.delay.cmd")					public String cConfigUnloadDelay = "Delay before unloading";
	@ConfigOptions(path = "commands.config.unload.delay.arg_world")				public String cConfigUnloadDelayArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.unload.delay.arg_delay")				public String cConfigUnloadDelayArgDelay = "tehe delay";
	@ConfigOptions(path = "commands.config.unload.message.cmd")					public String cConfigUnloadMessage = "Messsage when unload is delayed";
	@ConfigOptions(path = "commands.config.unload.message.arg_world")			public String cConfigUnloadMessageArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.unload.message.arg_message")			public String cConfigUnloadMessageArgMessage = "Messgae to send";
	@ConfigOptions(path = "commands.config.unload.remove.cmd")					public String cConfigUnloadRemove = "Remove players";
	@ConfigOptions(path = "commands.config.unload.remove.arg_world")			public String cConfigUnloadRemoveArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.unload.remove.arg_remove")			public String cConfigUnloadRemoveArgRemove = "True or False";
	@ConfigOptions(path = "commands.config.unload.kick.cmd")					public String cConfigUnloadkick = "Kick players";
	@ConfigOptions(path = "commands.config.unload.kick.arg_world")				public String cConfigUnloadkickArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.unload.kick.arg_Kick")				public String cConfigUnloadkickArgKick = "True or False";
	@ConfigOptions(path = "commands.config.unload.spawn.cmd")					public String cConfigUnloadSpawn = "Spawn players";
	@ConfigOptions(path = "commands.config.unload.spawn.arg_world")				public String cConfigUnloadSpawnArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.unload.spawn.arg_spawn")				public String cConfigUnloadSpawnArgSpawn = "Kick players";
	@ConfigOptions(path = "commands.config.unload.location.cmd")				public String cConfigUnloadLocation = "Spawn location";
	@ConfigOptions(path = "commands.config.unload.location.arg_world")			public String cConfigUnloadLocationArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.unload.add_command.cmd")				public String cConfigUnloadAddCommand = "Add a command";
	@ConfigOptions(path = "commands.config.unload.add_command.arg_world")		public String cConfigUnloadAddCommandArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.unload.add_command.arg_cmd")			public String cConfigUnloadAddCommandArgCmd= "Command";
	@ConfigOptions(path = "commands.config.unload.remove_command.cmd")			public String cConfigUnloadRemoveCommand = "Remove a command";
	@ConfigOptions(path = "commands.config.unload.remove_command.arg_world")	public String cConfigUnloadRemoveCommandArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.unload.remove_command.arg_cmd")		public String cConfigUnloadRemoveCommandArgCmd = "Command";
	@ConfigOptions(path = "commands.config.unload.remove_all_command.cmd")		public String cConfigUnloadRemoveAllCommand = "Remove all commands";
	@ConfigOptions(path = "commands.config.unload.remove_all_command.arg_world")public String cConfigUnloadRemoveAllCommandArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.load.cmd")							public String cConfigLoad = "Config load options";
	@ConfigOptions(path = "commands.config.load.help.cmd")						public String cConfigLoadHelp = "Config load help";
	@ConfigOptions(path = "commands.config.load.respawn.cmd")					public String cConfigLoadRespawn = "Respawn command";
	@ConfigOptions(path = "commands.config.load.respawn.arg_world")				public String cConfigLoadRespawnArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.load.respawn.arg_respawn")			public String cConfigLoadRespawnArgRespawn = "True or False";
	@ConfigOptions(path = "commands.config.load.force_spawn.cmd")				public String cConfigLoadForceSpawn = "Force the spawn location";
	@ConfigOptions(path = "commands.config.load.force_spawn.arg_world")			public String cConfigLoadForceSpawnArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.load.force_spawn.arg_spawn")			public String cConfigLoadForceSpawnArgSpawn = "True or False";
	@ConfigOptions(path = "commands.config.load.spawn_location.cmd")			public String cConfigLoadSpawnLocation = "Spawn location";
	@ConfigOptions(path = "commands.config.load.spawn_location.arg_world")		public String cConfigLoadSpawnLocationArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.load.add_command.cmd")				public String cConfigLoadAddCommand = "Add a command";
	@ConfigOptions(path = "commands.config.load.add_command.arg_world")			public String cConfigLoadAddCommandArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.load.add_command.arg_cmd")			public String cConfigLoadAddCommandArgCmd = "Command";
	@ConfigOptions(path = "commands.config.load.remove_command.cmd")			public String cConfigLoadRemoveCommand = "Remove a command";
	@ConfigOptions(path = "commands.config.load.remove_command.arg_world")		public String cConfigLoadRemoveCommandArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.load.remove_command.arg_cmd")		public String cConfigLoadRemoveCommandArgCmd = "Command";
	@ConfigOptions(path = "commands.config.load.remove_all_command.cmd")		public String cConfigLoadRemoveAllCommand = "Remove all commands";
	@ConfigOptions(path = "commands.config.load.remove_all_command.arg_world")	public String cConfigLoadRemoveAllCommandArgWorld = "The world to configure";
	@ConfigOptions(path = "commands.config.reload.cmd")			public String cConfigReload = "Reload a config";
	@ConfigOptions(path = "commands.config.reload.arg_world")	public String cConfigReloadArgWorld = "The world config to reload";
	@ConfigOptions(path = "commands.relaod.cmd")				public String cReload = "Reload Worldrestorer";

	/* Success */
	@ConfigComment(path = "success", 		comment = "List of messages used after a sucess command")
	@ConfigOptions(path = "success.save")						public String sSave = "The world {WORLD} has been saved";
	@ConfigOptions(path = "success.load")						public String sLoad = "The world {WORLD} has been loaded";
	@ConfigOptions(path = "success.delete")						public String sDelete = "The world {WORLD} has been deleted";
	@ConfigOptions(path = "success.list")						public String sList = "List of saved worlds";
	@ConfigOptions(path = "success.kick")						public String sKick = "Players have been kick from world {WORLD} !";
	@ConfigOptions(path = "success.kick_player")				public String sKickPlayer = "You've been kicked !";
	@ConfigOptions(path = "success.remove")						public String sRemove = "Players have been removed from world {WORLD} !";
	@ConfigOptions(path = "success.respawn")					public String sRespawn = "Players have been respawned in world {WORLD} !";
	@ConfigOptions(path = "success.config")						public String sConfig = "The config for world {WORLD} as been updated";
	@ConfigOptions(path = "success.relaod")						public String sReload = "WorldRestorer has been sReload";

	/* Errors */
	@ConfigComment(path = "errors", 		comment = "List of messages used after a error command")
	@ConfigOptions(path = "errors.save")						public String eSave = "Error while saving the world {WORLD}";
	@ConfigOptions(path = "errors.load")						public String eLoad = "Error while loading the world {WORLD}";
	@ConfigOptions(path = "errors.delete")						public String eDelete = "Error while deleting the world {WORLD}";
	@ConfigOptions(path = "errors.list")						public String eList = "List of saved worlds is empty";

	/* Annonce */
    @ConfigComment(path = "annonces", 		comment = "List of messages used for annoncement")
	@ConfigOptions(path = "annonces.delay")						public String aDelay = "The world {WORLD} will be sReload in {DELAY} seconds";




    public Messages(WorldRestorer plugin, File dataFolder, String filename)
    {
        super(plugin, new File(dataFolder, filename));
    }

    public void reloadConfig() {
        load();
    }
}
