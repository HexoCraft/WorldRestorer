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
        "# | WorldRestorer by hexosse                            | #",
        "############################################################"
})
@ConfigFile.ConfigFooter(comment = {
        " ",
        " ",
        "############################################################"
})

public class Config extends ConfigFile<WorldRestorer>
{
    /* Plugin */
    @ConfigComment(path = "plugin")
    @ConfigOptions(path = "plugin.useMetrics")          public boolean useMetrics = (boolean) true;
    @ConfigOptions(path = "plugin.useUpdater")          public boolean useUpdater = (boolean) true;
    @ConfigOptions(path = "plugin.downloadUpdate")      public boolean downloadUpdate = (boolean) true;

    /* Message */
    @ConfigOptions(path = "messages")                   public String messages = "messages.yml";


    public Config(WorldRestorer plugin, File dataFolder, String filename)
    {
        super(plugin, new File(dataFolder, filename));
    }

    public void reloadConfig() {
        load();
    }
}
