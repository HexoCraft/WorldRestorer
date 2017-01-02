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

public class Config extends Configuration
{
    /* Plugin */
    @ConfigPath(path = "plugin")
    @ConfigValue(path = "plugin.useMetrics")          public boolean useMetrics     = (boolean) true;
    @ConfigValue(path = "plugin.useUpdater")          public boolean useUpdater     = (boolean) true;
    @ConfigValue(path = "plugin.downloadUpdate")      public boolean downloadUpdate = (boolean) true;

    /* Message */
    @ConfigValue(path = "messages")                   public String messages = "messages.yml";


    public Config(JavaPlugin plugin, String fileName, boolean load)
    {
        super(plugin, fileName);

        if(load) load();
    }
}
