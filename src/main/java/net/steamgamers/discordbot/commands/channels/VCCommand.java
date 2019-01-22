package net.steamgamers.discordbot.commands.channels;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.commands.Command;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands.channels
 *
 * @author SweetKebab_
 * Created the 2019-01-20 at 12:28.
 */
public class VCCommand extends Command {
    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        if(member.getRoles().contains(guild.getRoleById("334192954564411394"))) {
            if(args.length < 1) {
                LocknKey.createVC(event, null, 0, true);
            } else {

            }
        }
    }
}
