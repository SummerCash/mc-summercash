# mc-summercash
A Minecraft plugin allowing players to transfer their SummerCash within Minecraft.

## Usage
The build.sh script is quite handy. Use it like so:

To quickly download and install a pre-built 1.13.2 Minecraft Bukkit/Spigot server, use
```./build.sh --install-server```.
You can then run ```cd server/ && ./start.sh``` to start the Bukkit/Spigot server.

This pre-built server does not come with the mc-summercash-v0.1.jar plugin jarfile in it. So, to build the plugin and install it into the minecraft server, run ```./build.sh --build```.