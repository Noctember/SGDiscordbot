package net.steamgamers.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.Instant;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands
 *
 * @author SweetKebab_
 * Created the 2019-01-16 at 18:32.
 */
public abstract class Command {
    public EmbedBuilder help(MessageReceivedEvent e) {
        return (new EmbedBuilder()
                .setColor(Color.GREEN)
                .setTimestamp(Instant.now())
                .setFooter("Command Help/Usage",null));
    }

    public abstract void action(String[] args, MessageReceivedEvent event);

    protected void reply(TextChannel channel, String msg) {
        channel.sendMessage(msg).queue();
    }
}
