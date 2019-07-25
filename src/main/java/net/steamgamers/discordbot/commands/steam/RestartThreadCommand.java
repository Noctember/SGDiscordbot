package net.steamgamers.discordbot.commands.steam;

import com.github.koraktor.steamcondenser.steam.servers.SourceServer;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.DiscordBot;
import net.steamgamers.discordbot.commands.Command;
import net.steamgamers.discordbot.utils.ServerUpdateThread;

import java.net.InetAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands.steam
 *
 * @author SweetKebab_
 * Created the 2019-01-28 at 20:20.
 */
public class RestartThreadCommand extends Command {
    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(event.getAuthor().getId().equals("118499178790780929") || event.getMember().getRoles().contains(event.getGuild().getRoleById("399345599297617923"))){
            try {
//                DiscordBot.scheduler.shutdownNow();
//                ScheduledExecutorService sc = Executors.newSingleThreadScheduledExecutor();
//                DiscordBot.scheduler = sc;
//                sc.scheduleAtFixedRate(DiscordBot.mg, 0,15, TimeUnit.SECONDS);
//                sc.scheduleAtFixedRate(DiscordBot.jb, 0,15, TimeUnit.SECONDS);
//                sc.scheduleAtFixedRate(DiscordBot.ttt, 0,15, TimeUnit.SECONDS);
//                sc.scheduleAtFixedRate(DiscordBot.ze, 0,15, TimeUnit.SECONDS);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
