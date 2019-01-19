package net.steamgamers.discordbot.listeners;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.steamgamers.discordbot.utils.EmojiSelection;

import java.util.HashMap;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.listeners
 *
 * @author SweetKebab_
 * Created the 2019-01-18 at 08:41.
 */
public class SelectorListener extends ListenerAdapter {

    private static HashMap<String, EmojiSelection> emojiSelector = new HashMap<String, EmojiSelection>();

    public static void addEmojiSelection(String author, EmojiSelection select) {
        if (select.getMessage().isFromType(ChannelType.PRIVATE) || select.getGuild().getSelfMember().hasPermission((GuildChannel) select.getChannel(), Permission.MESSAGE_ADD_REACTION)) {
            for (String em : select.getOption()) {
                select.getMessage().addReaction(em).queue();
            }
            emojiSelector.put(author, select);
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isFake())
            return;

        if (event.getChannelType().isGuild() && !event.getGuild().isAvailable() ||
                (event.getChannelType().isGuild() && !event.getTextChannel().canTalk()) ||
                (!event.getChannelType().isGuild() && event.getPrivateChannel().isFake()))
            return;


    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (emojiSelector.containsKey(event.getUser().getId())) {
            EmojiSelection selection = emojiSelector.get(event.getUser().getId());
            if (selection.isSelection(event)) {
                selection.action(selection.selector(event.getReactionEmote().getName()));
                emojiSelector.remove(event.getUser().getId());
            }
        }
    }
}
