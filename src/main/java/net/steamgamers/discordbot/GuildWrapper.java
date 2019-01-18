package net.steamgamers.discordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot
 *
 * @author SweetKebab_
 * Created the 2019-01-16 at 08:41.
 */
public class GuildWrapper {
    /*
    *  JDA
    * */
    private final JDA jda;
    private final Guild guild;

    private String prefix = "!";

    public GuildWrapper(JDA jda, Guild guild) {
        this.jda = jda;
        this.guild = guild;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
