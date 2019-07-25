package net.steamgamers.discordbot.commands.restricted;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.commands.Command;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands.restricted
 *
 * @author SweetKebab_
 * Created the 2019-03-06 at 18:53.
 */
public class PinCommand extends Command {
    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals("118499178790780929") || event.getMember().getRoles().contains(event.getGuild().getRoleById("399345599297617923"))) {
            event.getTextChannel().getMessageById(args[0]).queue(msg->msg.pin().queue());
        }
    }
}
