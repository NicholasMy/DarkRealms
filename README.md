# DarkRealms
DarkRealms is an open source Spigot plugin designed for use on my privately hosted Minecraft servers. It implements some commonly desired functionality in an
all-in-one minimalistic plugin, while allowing Dark Realms owners to easily manage which features are enabled or disabled. This plugin does not rely on a 3rd party
permissions management plugin, which could be overwhelming for casual players. The main goal of this plugin is to make it as easy as possible for Dark Realms clients
to control their server without needing many 3rd party plugins.

## Command reference:
<> Indicates a required parameter

[] Indicates an optional parameter

### /allow [\<permission> \<level>]
Without arguments, `/allow` will show the current permission levels to the sender. Only ops may use this command.

If the permission and level arguments are provided, this command will modify the configuration file to assign the specified permissions.

Examples for the permission argument: `fly`, `set_spawn`

Examples for the level argument: `nobody`, `ops`, `all`

Example usage to allow only ops to toggle their flight mode: `/allow fly ops`

Example usage to allow everyone to teleport to spawn: `/allow spawn all`

The permission argument is very specific, so you must type it exactly, but you have some wiggle room with the level argument.
For example, you could type `no`, `nobody`, or `none` if you don't want anyone to have access to a permission.
Additionally, for ops, you could type `op`, `ops`, or `operators`. And for everyone, you could type `everyone`, `all`, or `everybody`.
It's generally pretty forgiving, but in case it doesn't understand what you meant, it will give you feedback as to where your error was.
