package com.github.hexocraft.worldrestorer;

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

import com.github.hexocraft.worldrestorer.configuration.WorldConfig;
import com.github.hexocraftapi.message.predifined.message.SimplePrefixedMessage;
import com.github.hexocraftapi.message.predifined.message.WarningPrefixedMessage;
import com.github.hexocraftapi.nms.utils.NmsWorldUtil;
import com.google.common.collect.Lists;
import com.onarandombox.MultiverseCore.api.SafeTTeleporter;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.hexocraft.worldrestorer.command.WrCommands.prefix;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WorldRestorerApi
{
	private static final String worldSaves = "WorldSaves";
	private static final String worldSettings = "WorldSettings.yml";

	private static List<Player> worldPlayers = null;

	// List of existing saved worlds
	public static ArrayList<String> listWorlds(JavaPlugin plugin)
	{
		File worldSavesFolder = new File(plugin.getDataFolder() + "/" + worldSaves + "/");
		String[] directories = worldSavesFolder.list(new FilenameFilter()
		{
			@Override
			public boolean accept(File file, String name)
			{
				if(file.isDirectory())
				{
					String[] settings = file.list(new FilenameFilter()
					{
						@Override
						public boolean accept(File file, String name)
						{
							return name.equals(worldSettings);
						}
					});
					if(settings != null)
						return true;
					return false;
				}
				else
					return false;
			}
		});

		if(directories==null)
			return Lists.newArrayList();
		else
			return Lists.newArrayList(directories);
	}

	// Clear the world players list
	public static void clearWorldPlayers() { if(worldPlayers!=null) worldPlayers.clear(); }

	// Check if world save exist
	public static boolean exist(JavaPlugin plugin, String savedWorld)
	{
		return listWorlds(plugin).contains(savedWorld);
	}

	// Save a world
	public static boolean saveWorld(JavaPlugin plugin, World world, String saveAs)
	{
		if(world == null)
		{
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to save the world '" + world.getName() + "'. This world doesn't exist");
			return false;
		}

		// First we need to save the world to disk
		saveWorld(world);

		// Create the worldSaves folder
		new File(plugin.getDataFolder() + "/" + worldSaves + "/").mkdirs();

		// World folder
		final File worldFolder = new File(plugin.getServer().getWorldContainer(), world.getName());

		// Save as folder
		final File saveAsFolder = new File(plugin.getDataFolder() + "/" + worldSaves + "/" + saveAs);

		// Get the WorldConfig file for the saved world
		WorldConfig worldConfig = getWorldConfig(plugin, saveAs);

		// Check if the save already exists
		if(saveAsFolder.exists())
			deleteWorldFolder(saveAsFolder);
		if(saveAsFolder.exists()) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete the existing world '" + saveAs + "'");
			return false;
		}

		// Copy the world
		if(!copyWorldFolder(worldFolder, saveAsFolder) || !saveAsFolder.exists()) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to copy world '" + saveAs + "'");
			return false;
		}

		// Create the WorldConfig file for the saved world
		if(worldConfig==null) worldConfig = getWorldConfig(plugin, saveAs);

		// Create a copy of the WorldCreator used for the world to copy
		final WorldCreator worldCreator = getWorldCreator(world);

		// Update the world config file for the saved world
		worldConfig.name = worldCreator.name();
		worldConfig.seed = worldCreator.seed();
		worldConfig.environment = worldCreator.environment().toString();
		worldConfig.generator = worldCreator.generator() != null ? worldCreator.generator().toString() : "";
		worldConfig.type = worldCreator.type().toString();
		worldConfig.generateStructures = worldCreator.generateStructures();
		worldConfig.generatorSettings = worldCreator.generatorSettings();
		worldConfig.save();

		return true;
	}

	public static boolean saveWorld(JavaPlugin plugin, String world)
	{
		return saveWorld(plugin, Bukkit.getServer().getWorld(world), world);
	}

	public static boolean saveWorld(JavaPlugin plugin, String world, String saveAs)
	{
		return saveWorld(plugin, Bukkit.getServer().getWorld(world), saveAs);
	}

	public static boolean deleteWorldSave(JavaPlugin plugin, String worldName)
	{
		// Folder to delete
		File worldFolder = new File(plugin.getDataFolder() + "/" + worldSaves + "/" + worldName);

		// Check
		if(!worldFolder.exists()) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete the saved world '" + worldName + "'. This world doesn't exist");
			return false;
		}

		// Delete the folder
		if(worldFolder.exists() && !deleteWorldFolder(worldFolder)) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete the folder '" + worldFolder.toString() + "'.");
			return false;
		}

		// Check
		if(worldFolder.exists()) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete the saved world '" + worldName + "'.");
			return false;
		}

		SimplePrefixedMessage.toConsole(WorldRestorer.instance, prefix, "The saved world " + worldName + " was deleted.");

		return true;
	}

	public static boolean loadWorld(JavaPlugin plugin, String savedWorld, String loadAs)
	{
		// The main world 'world' can't be resetted
		if(loadAs.equals("world")) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete the world 'world'. The main world 'world' can't be resetted");
			return false;
		}

		// Check that the saved folder exist
		final File savedWorldFolder = new File(plugin.getDataFolder() + "/" + worldSaves + "/" + savedWorld);
		if(savedWorldFolder.exists()==false) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to load the world '" + savedWorld + "'. This world 'world' doesn't exist");
			return false;
		}

		// Load as folder
		final File loadAsFolder = new File(WorldRestorer.instance.getServer().getWorldContainer(), loadAs);

		// Get the config file for that world
		final WorldConfig worldConfig = getWorldConfig(plugin, savedWorld);
		if(worldConfig==null) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to load the config file for the world '" + savedWorld + "'.");
			return false;
		}

		// by default, remove all the players in the world
		removePlayersFromWorld(loadAs);

		// Unload the world if it exist
		if(WorldRestorer.instance.getServer().getWorld(loadAs)!=null)
		{
			// First with Bukkit, be sure to save the world to unload all chunks
			if(WorldRestorer.instance.getServer().unloadWorld(loadAs, true) == false) {
				WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Bukkit refused to unload the world '" + loadAs + "'");
				return false;
			}

			// Next with multiverse if it is present
			if(WorldRestorer.multiverse != null && WorldRestorer.multiverse.getCore().getMVWorldManager().unloadWorld(loadAs, true) == false) {
				WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to unload the world '" + loadAs + "' using Multiverse.");
				return false;
			}

			SimplePrefixedMessage.toConsole(WorldRestorer.instance, prefix, "The world '" + loadAs + "' was unloaded.");
		}

		// Delete the folder containing the world
		if(loadAsFolder.exists() && !deleteWorldFolder(loadAsFolder)) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete the folder '" + loadAsFolder.toString() + "'.");
			return false;
		}
		// Check that all files are deleted
		if(loadAsFolder.exists()) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete the world '" + loadAs + "'");
			return false;
		}
		else
			SimplePrefixedMessage.toConsole(WorldRestorer.instance, prefix, "The world '" + loadAs + "' was deleted.");

		// Copy the saved world
		copyWorldFolder(savedWorldFolder, loadAsFolder);
		// Check that files are copied
		if(!loadAsFolder.exists()) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to copy the world '" + savedWorld + "' to '" + loadAs + "'.");
			return false;
		}
		else
			SimplePrefixedMessage.toConsole(WorldRestorer.instance, prefix, "The world '" + savedWorld + "' has been copied to '" + loadAs + "'.");

		//load the world
		WorldCreator worldCreator = worldConfig.getWorldCreator(loadAs);
		if(WorldRestorer.multiverse != null)
		{
			if(WorldRestorer.multiverse.getCore().getMVWorldManager().addWorld(loadAs, worldCreator.environment(), Long.toString(worldCreator.seed()), worldCreator.type(), worldCreator.generateStructures(), worldCreator.generatorSettings()) == false) {
				WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to add the world '" + loadAs + "' using Multiverse.");
				return false;
			}
			if(WorldRestorer.multiverse.getCore().getMVWorldManager().loadWorld(loadAs) == false) {
				WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to load the world '" + loadAs + "' using Multiverse.");
				return false;
			}

			SimplePrefixedMessage.toConsole(WorldRestorer.instance, prefix, "The world '" + loadAs + "' has bee loaded using Multiverse.");
		}
		else
		{
			WorldRestorer.instance.getServer().createWorld(worldCreator);
			if( WorldRestorer.instance.getServer().getWorld(loadAs) == null) {
				WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to add the world '" + loadAs + "' using Bukkit.");
				return false;
			}

			SimplePrefixedMessage.toConsole(WorldRestorer.instance, prefix, "The world '" + loadAs + "' has bee loaded using Bukkit.");
		}

		// Events
		WorldRestorer.instance.getServer().getPluginManager().callEvent(new WorldInitEvent(WorldRestorer.instance.getServer().getWorld(loadAs)));
		WorldRestorer.instance.getServer().getPluginManager().callEvent(new WorldLoadEvent(WorldRestorer.instance.getServer().getWorld(loadAs)));

		return true;
	}

	public static boolean loadWorld(JavaPlugin plugin, String world)
	{
		return loadWorld(plugin, world, world);
	}



	private static WorldCreator getWorldCreator(World world)
	{
		final WorldCreator worldCreator = new WorldCreator(world.getName());
		worldCreator.copy(world);
		return worldCreator;
	}

	private static boolean saveWorld(World world)
	{
		// Try to save all chunks
		final Chunk[] chunks = world.getLoadedChunks();
		for (final Chunk chunk : chunks)
			NmsWorldUtil.saveChunk(world, chunk);

		// Time reference
		long reference = new Date().getTime();

		// Save the world
		world.save();

		// Wait for world to save
		try
		{
			long delay = 100, count = 0;
			while(count<100)
			{
				long lastModify = new File(world.getWorldFolder(), "level.dat").lastModified();

				// Wait for the file to close
				if(lastModify - reference > 0)
				{
					Thread.sleep(800);
					return true;
				}

				// Wait again
				Thread.sleep(delay);

				// D'ont wait to much
				count++;
			}
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		System.runFinalization();
		System.gc();

		return false;
	}

	private static boolean copyWorldFolder(File sourceFolder, File destinationFolder)
	{
		try
		{
			FileUtils.copyDirectory(sourceFolder, destinationFolder);
		}
		catch(IOException e)
		{
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to copy folder " + sourceFolder.getName() + " to " + destinationFolder.getName());
			return false;
		}

		// Remove the uid.dat file if exist
		File uidFile = new File(destinationFolder, "uid.dat");
		if (uidFile.exists() && !FileUtils.deleteQuietly(uidFile)) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete uid.dat file from world " + destinationFolder.getName());
			return false;
		}
		// Remove the session.lock file if exist
		File sessionFile = new File(destinationFolder, "session.lock");
		if (sessionFile.exists() && !FileUtils.deleteQuietly(sessionFile)) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete session.lock file from world " + destinationFolder.getName());
			return false;
		}
		// Remove the worldSettings file if exist
		File worldSettingsFile = new File(destinationFolder, worldSettings);
		if (worldSettingsFile.exists() && !FileUtils.deleteQuietly(worldSettingsFile)) {
			WarningPrefixedMessage.toConsole(WorldRestorer.instance, prefix, "Failed to delete " + worldSettings + " file from world " + destinationFolder.getName() + ".");
			return false;
		}

		return true;
	}

	private static boolean deleteWorldFolder(File dir)
	{
		try
		{
			FileUtils.deleteDirectory(dir);
			return true;
		}
		catch(IOException ignored)
		{
			return false;
		}
	}

	private static boolean isCompletelyWritten(File file) {
		RandomAccessFile stream = null;
		try {
			stream = new RandomAccessFile(file, "rw");
		} catch (Exception e) {
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				}
				return true;
			}
		}
		return false;
	}



	public static WorldConfig getWorldConfig(JavaPlugin plugin, String savedWorldName)
	{
		// The world folder
		final File savedWorldFolder = new File(plugin.getDataFolder() + "/" + worldSaves + "/" + savedWorldName);
		if(savedWorldFolder.exists()==false)
			return null;

		//
		final WorldConfig worldConfig = new WorldConfig(WorldRestorer.instance, savedWorldFolder, worldSettings, true);
		if(worldConfig.load()==false)
			return null;

		return worldConfig;
	}

	private static void kickPlayersFromWorld(World world, String kickMessage)
	{
		if(world == null) return;

		List<Player> ps = world.getPlayers();
		for(Player p : ps) {
			p.saveData();
			p.kickPlayer(kickMessage);
		}
	}

	public static void kickPlayersFromWorld(String worldName, String kickMessage) {
		World world = Bukkit.getWorld(worldName);
		kickPlayersFromWorld(world, kickMessage);
	}

	public static void removePlayersFromWorld(World world)
	{
		if(world == null) return;
		if(world.getPlayers().size()==0) return;

		// Keep trace of all players in the world
		worldPlayers = world.getPlayers();

		// Remove the players
		if(WorldRestorer.multiverse != null)
		{
			WorldRestorer.multiverse.getCore().getMVWorldManager().removePlayersFromWorld(world.getName());
		}
		else
		{
			World safeWorld = WorldRestorer.instance.getServer().getWorlds().get(0);
			for (Player p : worldPlayers) {
				p.teleport(safeWorld.getSpawnLocation());
			}
		}
	}

	public static void removePlayersFromWorld(String worldName)
	{
		World world = Bukkit.getWorld(worldName);
		removePlayersFromWorld(world);
	}

	public static void respawnPlayersInWorld(World world)
	{
		if(world == null) return;
		if(worldPlayers == null) return;
		if(worldPlayers.size() == 0) return;

		// Respawn the players
		if(WorldRestorer.multiverse != null)
		{
			SafeTTeleporter teleporter = WorldRestorer.multiverse.getCore().getSafeTTeleporter();
			for (Player p : worldPlayers) {
				if(p.isOnline())
					teleporter.safelyTeleport(null, p, world.getSpawnLocation(), true);
			}
		}
		else
		{
			for (Player p : worldPlayers) {
				if(p.isOnline())
					p.teleport(world.getSpawnLocation());
			}
		}

		// Empty the list
		worldPlayers.clear();
	}

	public static void respawnPlayersInWorld(String worldName)
	{
		World world = Bukkit.getWorld(worldName);
		respawnPlayersInWorld(world);
	}

	public static void spawnPlayersInWorld(World world, Location location)
	{
		if(world == null) return;
		if(worldPlayers == null) return;
		if(worldPlayers.size() == 0) return;

		Location teleportLocation = location.clone();
		teleportLocation.setWorld(world);

		// Teleport players
		for(Player p : worldPlayers)  {
			p.teleport(location);
		}
	}

	public static void spawnPlayersInWorld(String worldName, Location location)
	{
		World world = Bukkit.getWorld(worldName);
		spawnPlayersInWorld(world, location);
	}

	public static void sendMessageInWorld(World world, String message)
	{
		if(world == null) return;

		for (Player p : world.getPlayers()) {
			p.sendMessage(message);
		}
	}

	public static void sendMessageInWorld(String worldName, String message)
	{
		World world = Bukkit.getWorld(worldName);
		sendMessageInWorld(world, message);
	}
}
