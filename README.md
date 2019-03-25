# mc-summercash
A Minecraft plugin allowing players to transfer their SummerCash within Minecraft.

## Usage
The build.sh script is quite handy. Use it like so:

To quickly download and install a pre-built 1.13.2 Minecraft Bukkit/Spigot server, use
```./build.sh --install-server```.
You can then run ```cd server/ && ./start.sh``` to start the Bukkit/Spigot server.

This pre-built server does not come with the mc-summercash plugin installed. So, to build the plugin and install it into the minecraft server, run ```./build.sh --build```.

## In-Game Commands
`/account` creates a new SummerCash account and links it to the player's Minecraft username. A user can only run this command once.

`/balance` displays the amount of SummerCash (SMC) on the user's account. This command will only work if a user has created an account.

`/transaction <recipient> <amount of SMC>` will create, sign, and publish a transaction for the given amount to the SummerCash network. Like `/balance`, this command will only work if a user has created an account.

## Bugs
Please report any bugs you find in the `bugs.md` file. A formal write-up is not necessary.