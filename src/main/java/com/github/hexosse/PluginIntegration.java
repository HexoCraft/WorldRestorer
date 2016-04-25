package com.github.hexosse;

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


/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class PluginIntegration
{

	/**
	 * Determines if all packages in a String array are within the Classpath
	 * This is the best way to determine if a specific plugin exists and will be
	 * loaded. If the plugin package isn't loaded, we shouldn't bother waiting
	 * for it!
	 * @param packages String Array of package names to check
	 * @return Success or Failure
	 */
	private static boolean packagesExists(String...packages) {
		try {
			for (String pkg : packages) {
				Class.forName(pkg);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}


/*
	private void hookChat (String name, Class<? extends Chat> hookClass, ServicePriority priority, String...packages) {
		try {
			if (packagesExists(packages)) {
				Chat chat = hookClass.getConstructor(Plugin.class, Permission.class).newInstance(this, perms);
				sm.register(Chat.class, chat, this, priority);
				log.info(String.format("[Chat] %s found: %s", name, chat.isEnabled() ? "Loaded" : "Waiting"));
			}
		} catch (Exception e) {
			log.severe(String.format("[Chat] There was an error hooking %s - check to make sure you're using a compatible version!", name));
		}
	}

	private void hookEconomy (String name, Class<? extends Economy> hookClass, ServicePriority priority, String...packages) {
		try {
			if (packagesExists(packages)) {
				Economy econ = hookClass.getConstructor(Plugin.class).newInstance(this);
				sm.register(Economy.class, econ, this, priority);
				log.info(String.format("[Economy] %s found: %s", name, econ.isEnabled() ? "Loaded" : "Waiting"));
			}
		} catch (Exception e) {
			log.severe(String.format("[Economy] There was an error hooking %s - check to make sure you're using a compatible version!", name));
		}
	}

	private void hookPermission (String name, Class<? extends Permission> hookClass, ServicePriority priority, String...packages) {
		try {
			if (packagesExists(packages)) {
				Permission perms = hookClass.getConstructor(Plugin.class).newInstance(this);
				sm.register(Permission.class, perms, this, priority);
				log.info(String.format("[Permission] %s found: %s", name, perms.isEnabled() ? "Loaded" : "Waiting"));
			}
		} catch (Exception e) {
			log.severe(String.format("[Permission] There was an error hooking %s - check to make sure you're using a compatible version!", name));
		}
	}*/
}
