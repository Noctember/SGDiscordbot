package net.steamgamers.discordbot.commands.steam;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.commands.Command;
import net.steamgamers.discordbot.utils.Emoji;
import net.steamgamers.discordbot.utils.GameMEAPI;
import net.steamgamers.discordbot.utils.UtilString;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
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
                    GameMEAPI api;
                    switch (type) {
                        case "ttt":
                            api = new GameMEAPI(args[0], "ttt");
                            api.prepare();
                            eb.setDescription("Rank: `" + api.get("rank") +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.valueOf(api.get("time"))) +
                                    "`\nSkill points: `" + api.get("skill") +
                                    "`\nKills: `" + api.get("kills")  +
                                    "`\nDeaths: `" + api.get("deaths") +
                                    "`\nFavorite gun: `" + api.get("favgun")+"`")
                                    .setTitle(api.get("name")+"'s TTT Stats")
                                    .setThumbnail(api.get("avatar"));
                            reply(event.getTextChannel(), eb.build());
                            break;
                        case "scrim":
                            api = new GameMEAPI(args[0], "scrim");
                            api.prepare();
                            eb.setDescription("Rank: `" + api.get("rank") +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.valueOf(api.get("time"))) +
                                    "`\nSkill points: `" + api.get("skill") +
                                    "`\nKills: `" + api.get("kills")  +
                                    "`\nDeaths: `" + api.get("deaths") +
                                    "`\nFavorite gun: `" + api.get("favgun")+"`")
                                    .setTitle(api.get("name")+"'s Scrim Stats")
                                    .setThumbnail(api.get("avatar"));
                            reply(event.getTextChannel(), eb.build());
                            break;
                        case "ze":
                            api = new GameMEAPI(args[0], "ze");
                            api.prepare();
                            eb.setDescription("Rank: `" +api.get("rank") +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.parseLong(api.get("time"))) +
                                    "`\nSkill points: `" + api.get("skill") +
                                    "`\nKills: `" + api.get("kills")  +
                                    "`\nDeaths: `" + api.get("deaths") +
                                    "`\nFavorite gun: `" + api.get("favgun")+"`")
                                    .setTitle(api.get("name")+"'s ZE Stats")
                                    .setThumbnail(api.get("avatar"));
                            reply(event.getTextChannel(), eb.build());
                            break;
                        case "jb":
                            api = new GameMEAPI(args[0], "jb");
                            api.prepare();
                            eb.setDescription("Rank: `" + api.get("rank") +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.valueOf(api.get("time"))) +
                                    "`\nSkill points: `" + api.get("skill") +
                                    "`\nKills: `" + api.get("kills")  +
                                    "`\nDeaths: `" + api.get("deaths") +
                                    "`\nFavorite gun: `" + api.get("favgun")+"`")
                                    .setTitle(api.get("name")+"'s JB Stats")
                                    .setThumbnail(api.get("avatar"));
                            reply(event.getTextChannel(), eb.build());
                            break;
                        case "mg":
                            api = new GameMEAPI(args[0], "mg");
                            api.prepare();
                            eb.setDescription("Rank: `" + api.get("rank") +
                                    "`\nConnection time: `" + UtilString.formatTime(Long.valueOf(api.get("time"))) +
                                    "`\nSkill points: `" + api.get("skill") +
                                    "`\nKills: `" + api.get("kills")  +
                                    "`\nDeaths: `" + api.get("deaths") +
                                    "`\nFavorite gun: `" + api.get("favgun")+"`")
                                    .setTitle(api.get("name")+"'s MG Stats")
                                    .setThumbnail(api.get("avatar"));
                            reply(event.getTextChannel(), eb.build());
                            break;

                    }
                } else {
                    reply(event.getTextChannel(), Emoji.ERROR + " Usage is `!" + type + " steamid`\nNote: you can find your steam id @ https://steamid.io");
                }
            } else {
                reply(event.getTextChannel(), Emoji.ERROR + " Usage is `!" + type + " steamid`\nNote: you can find your steam id @ https://steamid.io");
            }
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            reply(event.getTextChannel(), Emoji.ERROR+" No data found for this id.");
        }
    }


}
