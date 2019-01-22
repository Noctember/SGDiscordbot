package net.steamgamers.discordbot.commands.steam;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.commands.Command;
import net.steamgamers.discordbot.utils.Emoji;
import net.steamgamers.discordbot.utils.GameMEAPI;
import net.steamgamers.discordbot.utils.UtilString;

import java.awt.*;
import java.util.regex.Pattern;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands.steam
 *
 * @author SweetKebab_
 * Created the 2019-01-18 at 19:00.
 */
public class StatsCommand extends Command {
    private String type = "";
    private Pattern steamreg = Pattern.compile("^STEAM_\\d:\\d:\\d+$");

    public StatsCommand(String invoke) {
        type = invoke;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (args.length > 0) {
                if (steamreg.matcher(args[0]).find()) {
                    EmbedBuilder eb = new EmbedBuilder().setColor(Color.GREEN);
                    switch (type) {
                        case "ttt":
                            eb.setDescription("Rank: `" + GameMEAPI.getTTTStats("rank", args[0]) +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.valueOf(GameMEAPI.getTTTStats("time", args[0])+"000")) +
                                    "`\nSkill points: `" + GameMEAPI.getTTTStats("skill", args[0]) +
                                    "`\nKills: `" + GameMEAPI.getTTTStats("kills", args[0])  +
                                    "`\nDeaths: `" + GameMEAPI.getTTTStats("deaths", args[0]) +
                                    "`\nFavorite gun: `" + GameMEAPI.getFavgunTTT("name", args[0])+"`")
                                    .setTitle(GameMEAPI.getTTTStats("name", args[0])+"'s TTT Stats")
                                    .setThumbnail(GameMEAPI.getTTTStats("avatar", args[0]));
                            reply(event.getTextChannel(), eb.build());
                        case "scrim":
                            eb.setDescription("Rank: `" + GameMEAPI.getScrimStats("rank", args[0]) +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.valueOf(GameMEAPI.getScrimStats("time", args[0])+"000")) +
                                    "`\nSkill points: `" + GameMEAPI.getScrimStats("skill", args[0]) +
                                    "`\nKills: `" + GameMEAPI.getScrimStats("kills", args[0])  +
                                    "`\nDeaths: `" + GameMEAPI.getScrimStats("deaths", args[0]) +
                                    "`\nFavorite gun: `" + GameMEAPI.getFavgunScrim("name", args[0])+"`")
                                    .setTitle(GameMEAPI.getScrimStats("name", args[0])+"'s Scrim Stats")
                                    .setThumbnail(GameMEAPI.getScrimStats("avatar", args[0]));
                            reply(event.getTextChannel(), eb.build());
                        case "ze":
                            eb.setDescription("Rank: `" + GameMEAPI.getZEStats("rank", args[0]) +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.valueOf(GameMEAPI.getZEStats("time", args[0])+"000")) +
                                    "`\nSkill points: `" + GameMEAPI.getZEStats("skill", args[0]) +
                                    "`\nKills: `" + GameMEAPI.getZEStats("kills", args[0])  +
                                    "`\nDeaths: `" + GameMEAPI.getZEStats("deaths", args[0]) +
                                    "`\nFavorite gun: `" + GameMEAPI.getFavgunZE("name", args[0])+"`")
                                    .setTitle(GameMEAPI.getZEStats("name", args[0])+"'s ZE Stats")
                                    .setThumbnail(GameMEAPI.getZEStats("avatar", args[0]));
                            reply(event.getTextChannel(), eb.build());
                        case "jb":
                            eb.setDescription("Rank: `" + GameMEAPI.getJBStats("rank", args[0]) +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.valueOf(GameMEAPI.getJBStats("time", args[0])+"000")) +
                                    "`\nSkill points: `" + GameMEAPI.getJBStats("skill", args[0]) +
                                    "`\nKills: `" + GameMEAPI.getJBStats("kills", args[0])  +
                                    "`\nDeaths: `" + GameMEAPI.getJBStats("deaths", args[0]) +
                                    "`\nFavorite gun: `" + GameMEAPI.getFavgunJB("name", args[0])+"`")
                                    .setTitle(GameMEAPI.getJBStats("name", args[0])+"'s JB Stats")
                                    .setThumbnail(GameMEAPI.getJBStats("avatar", args[0]));
                            reply(event.getTextChannel(), eb.build());
                        case "mg":
                            eb.setDescription("Rank: `" + GameMEAPI.getMGStats("rank", args[0]) +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.valueOf(GameMEAPI.getMGStats("time", args[0])+"000")) +
                                    "`\nSkill points: `" + GameMEAPI.getMGStats("skill", args[0]) +
                                    "`\nKills: `" + GameMEAPI.getMGStats("kills", args[0])  +
                                    "`\nDeaths: `" + GameMEAPI.getMGStats("deaths", args[0]) +
                                    "`\nFavorite gun: `" + GameMEAPI.getFavgunMG("name", args[0])+"`")
                                    .setTitle(GameMEAPI.getMGStats("name", args[0])+"'s MG Stats")
                                    .setThumbnail(GameMEAPI.getMGStats("avatar", args[0]));
                            reply(event.getTextChannel(), eb.build());


                    }
                } else {
                    reply(event.getTextChannel(), Emoji.ERROR + " Usage is `!" + type + " steamid`");
                }
            } else {
                reply(event.getTextChannel(), Emoji.ERROR + " Usage is `!" + type + " steamid`");
            }
        } catch (Exception ex) {
        }
    }
}
