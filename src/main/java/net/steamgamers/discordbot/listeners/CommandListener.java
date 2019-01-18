package net.steamgamers.discordbot.listeners;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.exceptions.PermissionException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.steamgamers.discordbot.DiscordBot;
import net.steamgamers.discordbot.logging.SLogger;
import net.steamgamers.discordbot.utils.Emoji;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.listeners
 *
 * @author SweetKebab_
 * Created the 2019-01-15 at 22:00.
 */
public class CommandListener extends ListenerAdapter {
    public static void handleCommand(CommandParser.CommandContainer cmd) {
        if (DiscordBot.commands.containsKey(cmd.invoke)) {
            cmd.event.getChannel().sendTyping().queue(success -> {
                MessageReceivedEvent e = cmd.event;

                try {
                    if (cmd.args.length > 0 && cmd.args[0].equals("-h")) {
                        e.getChannel().sendMessage(DiscordBot.commands.get(cmd.invoke).help(e).build()).queue();
                    } else {
                        DiscordBot.commands.get(cmd.invoke).action(cmd.args, e);
                    }
                } catch (NullPointerException npe) {

                    if (e.isFromType(ChannelType.PRIVATE)) {
                        e.getPrivateChannel().sendMessage(Emoji.ERROR + " This command is not supported in DM.").queue();
                    } else {
                        throw npe;
                    }

                } catch (PermissionException pe) {
                    pe.printStackTrace();
                    e.getChannel().sendMessage(Emoji.ERROR + " I need the following permission to the command!\n" + "`" + pe.getPermission().getName() + "`").queue();
                } catch (ErrorResponseException ere) {
                    if (!SLogger.errorResponseHandler(ere, e))
                        throw ere;
                } catch (Exception ex) {
                    String hastePaste = SLogger.toHasteBin(SLogger.stackToString(ex));
                    e.getChannel().sendMessage(Emoji.ERROR + " An error occurred!\n"+hastePaste).queue();
                    SLogger.handleExceptionLog(ex, e, hastePaste);
                }
            });
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isFake())
            return;

        if ((event.getChannelType().isGuild() && !event.getGuild().isAvailable()) ||
                (event.getChannelType().isGuild() && !event.getTextChannel().canTalk()))
            return;

        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (event.getChannelType() == ChannelType.TEXT &&
                    ((!event.getMessage().getMentionedMembers().isEmpty() && (event.getMessage().getMentionedMembers().get(0)).equals(event.getJDA().getSelfUser())) ||
                    event.getMessage().getContentRaw().startsWith(DiscordBot.getGuild(event.getGuild()).getPrefix()))) {
                try {
                    handleCommand(DiscordBot.parser.parse(event.getMessage().getContentRaw(), event));
                } catch (Exception ex) {
                    event.getChannel().sendMessage(Emoji.ERROR + " An error occured! \n```" + SLogger.stackToString(ex) + "```").queue();
                }
            }
        }
    }

}
