package net.steamgamers.discordbot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.steamgamers.discordbot.commands.Command;
import net.steamgamers.discordbot.commands.steam.CurrentCommand;
import net.steamgamers.discordbot.listeners.CommandListener;
import net.steamgamers.discordbot.listeners.CommandParser;
import net.steamgamers.discordbot.utils.ServerUpdateThread;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot
 *
 * @author SweetKebab_
 * Created the 2019-01-15 at 21:52.
 */
public class DiscordBot {
    public static HashMap<String, Command> commands = new HashMap();
    private static DiscordBot instance;
    private static JDA jda;
    private static HashMap<String, GuildWrapper> guilds = new HashMap<>();
    public static final CommandParser parser = new CommandParser();

    private DiscordBot() {

    }

    public static void main(String[] args) throws Exception {
        instance = new DiscordBot();
        jda = new JDABuilder(AccountType.BOT)
                .setToken("NTM0OTI4ODM2OTcwNjc2MjI0.DyAu-w.0K-1NI_k3zcqtLXQEW0o5RL1rVU")
                .addEventListeners(new CommandListener())
                .setAutoReconnect(true)
                .setMaxReconnectDelay(300)
                .setEnableShutdownHook(true)
                .build();

        Thread.sleep(1000);
        for (Guild g : jda.getGuilds()) {
            GuildWrapper newGuild = new GuildWrapper(jda, /*DiscordBot.playerManager,*/ g);
            guilds.put(g.getId(), newGuild);
        }
        Logger.getLogger("com.github.koraktor.steamcondenser").setLevel(Level.WARNING);
        ServerUpdateThread mg = new ServerUpdateThread(jda.getGuildById("169274032355540992").getTextChannelById("535257722506182675"), "66.150.121.70",27016, "MiniGames");
        ServerUpdateThread jb = new ServerUpdateThread(jda.getGuildById("169274032355540992").getTextChannelById("535257722506182675"), "70.42.74.162",27017, "JailBreak");
        ServerUpdateThread ttt = new ServerUpdateThread(jda.getGuildById("169274032355540992").getTextChannelById("535257722506182675"), "66.150.121.70",27015,"Terrorist Town");
        ServerUpdateThread ze = new ServerUpdateThread(jda.getGuildById("169274032355540992").getTextChannelById("535257722506182675"), "70.42.74.162",27015, "Zombie Escape");
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(mg, 0, 15, TimeUnit.SECONDS);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(jb, 5, 15, TimeUnit.SECONDS);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(ttt, 10, 15, TimeUnit.SECONDS);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(ze, 15, 15, TimeUnit.SECONDS);


        setupCommands();
    }

    private static void setupCommands() {
        commands.put("current", new CurrentCommand());
    }

    public static GuildWrapper getGuild(Guild guild) {
        return guilds.get(guild.getId());
    }

    public static GuildWrapper getGuild(String id) {
        return guilds.get(id);
    }

    public HashMap<String, GuildWrapper> getGuilds() {
        return guilds;
    }
}