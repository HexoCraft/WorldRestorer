# WorldRestorer 
Save, load, reset any worlds. Change spawn, add commands and more options per world.
Fully integrated with Multiverse and fully configurable.

WorldRester is a plugin which will allow you to save, load or reset a world.
Highly configurable you will be able to configure actions to perform before unloading a world like :
- Adding a delay before unloading the world,
- Define a delay message to boradcast to all players in the world,
- Remove players from the world and spawn then in the main world,
- Kick players from the server,
- Force a spawn location which can be different of the world spawn or even be in an another world,
- Add a list of server commands to execute before unloading the world.

And also after loading the world :
- Respawn players in the same world,
- Force a spawn loaction in loaded world which can be different of the world spawn.
- Add a list of server commands to execute before unloading the world.

####Commands:
* /WorldRestorer help : Display WorldRestorer help
* /WorldRestorer save \<world> [save as] : Save a world (Save you wolrds with the /save-all command to unsure that your last modifications will be saved)
* /WorldRestorer load \<saved world> [load as] : Load a world
* /WorldRestorer reset \<world> : reset a world
* /WorldRestorer delete \<saved world> : Delete a saved world
* /WorldRestorer list : List saved world
* /WorldRestorer kickplayers \<world> : Kick players from the world
* /WorldRestorer removeplayers \<world> : Remove players from the world
* /WorldRestorer respawnplayers \<world> : Respawn players from the world
* /WorldRestorer config : Respawn players from the world
    * unload : Config unload options
        * delay
        * delaymessage
        * removeplayers
        * kickplayers
        * spawnplayers
        * spawnlocation
        * addcommand
        * removecommand
        * removeallcommands
    * load : Config load options
        * respawn
        * forcespawn
        * spawnlocation
        * addcommand
        * removecommand
        * removeallcommands
* /WorldRestorer reload : Reload WorldRestorer

If you are like me and you don't like to type long commands, Worldrestorer include a full help support with full tab completion and also a clickable help.

####Permissions:
* WorldRestorer.save : Save a world
* WorldRestorer.load : Load a world
* WorldRestorer.delete : delete a saved world
* WorldRestorer.list : List all saved worlds
* WorldRestorer.kickplayers : Kick all the players from the world
* WorldRestorer.removeplayers : Remove all the players from the world
* WorldRestorer.respawnplayers : Respawn all the players previously removed from the world
* WorldRestorer.config : Edit the config of a saved world
* WorldRestorer.admin : Admin permissions
  * WorldRestorer.save
  * WorldRestorer.load
  * WorldRestorer.delete
  * WorldRestorer.list
  * WorldRestorer.kickplayers
  * WorldRestorer.removeplayers
  * WorldRestorer.respawnplayers
  * WorldRestorer.config

####Messages:
All the messages sent by WorldRestorer are configurable in the message.yml file.
You can also create your own message file and change it in the config file.
Feel free to send me any translation and I'll add it into WorldRestorer.

####Config:
The plugin use metrics and an integrated updater.<br>
Both can be disable in config.yml

####Ressources:
Releases : https://github.com/hexosse/WorldRestorer/releases

