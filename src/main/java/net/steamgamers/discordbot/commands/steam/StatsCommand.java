package net.steamgamers.discordbot.commands.steam;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.commands.Command;
import net.steamgamers.discordbot.utils.Emoji;
import net.steamgamers.discordbot.utils.GameMEAPI;

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
    private Pattern steamreg = Pattern.compile("/^STEAM_\\d:\\d:\\d+$/");

    public StatsCommand(String invoke) {
        type = invoke;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (args.length > 0) {///^STEAM_[0-5]:[01]:\d+$/
//                if (steamreg.matcher(args[0]).find()) {
                    EmbedBuilder eb = new EmbedBuilder().setColor(Color.GREEN).setThumbnail(GameMEAPI.getAvatar(args[0]));
                    switch (type) {
                        case "ttt":
                            eb.setDescription("Rank: `" + GameMEAPI.getTTTStats("rank", args[0]) +
                                    "`\nSkill points: `" + GameMEAPI.getTTTStats("skill", args[0]) +
                                    "`\nKills: `" + GameMEAPI.getTTTStats("kills", args[0])  +
                                    "`\nDeaths: `" + GameMEAPI.getTTTStats("deaths", args[0]) +
                                    "`\nFavorite gun: `" + GameMEAPI.getTTTStats("name", args[0])+"`")
                                    .setTitle(GameMEAPI.getTTTStats("name", args[0]));
                            reply(event.getTextChannel(), eb.build());

                    }
                } else {
                    reply(event.getTextChannel(), Emoji.ERROR + " Usage is `!" + type + " steamid`");
                }
//            } else {
//                reply(event.getTextChannel(), Emoji.ERROR + " Usage is `!" + type + " steamid`");
//            }
        } catch (Exception ex) {
        }
    }
}
