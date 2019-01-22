package net.steamgamers.discordbot.commands.mod;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;
import net.steamgamers.discordbot.commands.Command;
import net.steamgamers.discordbot.utils.Emoji;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands.mod
 *
 * @author SweetKebab_
 * Created the 2019-01-18 at 22:33.
 */
public class PruneCommand extends Command {
    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        TextChannel chan = event.getTextChannel();

        //Parse String to int, detect it the input is valid.
        Integer msgs = 0;
        try {
            msgs = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
        }


        //Delete command call
        event.getTextChannel().getMessageById(args[0]).queue(msg -> {
            msg.delete().submit();
        });
        event.getTextChannel().deleteMessageById(event.getMessage().getId()).submit();


    }

}
