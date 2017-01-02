package com.github.hexocraft.worldrestorer.listeners;

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
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;

/**
 * This file is part of WorldRestorer
 *
 * @author <b>hexosse</b> (<a href="https://github.com/hexosse">hexosse on GitHub</a>).
 */
public class WorldListener implements Listener
{
    /**
     * @param plugin The plugin that this object belong to.
     */
    public WorldListener(WorldRestorer plugin)
    {
        super();
    }

    /**
     * @param event PlayerInteractEvent
     */
    @EventHandler(priority= EventPriority.NORMAL)
    public void onWorldInit(WorldInitEvent event)
    {
        World w = event.getWorld();
    }

    /**
     * @param event PlayerInteractEvent
     */
    @EventHandler(priority= EventPriority.NORMAL)
    public void onWorldLoad(WorldLoadEvent event)
    {
        World w = event.getWorld();
    }

    @EventHandler(priority= EventPriority.NORMAL)
    public void onWorldUnloa(WorldUnloadEvent event)
    {
        World w = event.getWorld();
    }

    @EventHandler(priority= EventPriority.NORMAL)
    public void onWorldSave(WorldSaveEvent event)
    {
        World w = event.getWorld();
    }

}
