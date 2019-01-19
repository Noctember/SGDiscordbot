package net.steamgamers.discordbot.utils;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import java.util.List;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.utils
 *
 * @author SweetKebab_
 * Created the 2019-01-18 at 08:45.
 */
public abstract class EmojiSelection extends DBSelector {

    private List<String> option;

    public EmojiSelection(Message msg, Member ar, List<String> opt) {
        super(msg, ar);
        this.option = opt;
    }

    public EmojiSelection() {
        super();
    }

    @Override
    public boolean isSelection(GenericMessageEvent event) {
        if(event instanceof MessageReactionAddEvent) {
            MessageReactionAddEvent e = (MessageReactionAddEvent) event;
            if (e.isFromType(ChannelType.PRIVATE)) {
                return option.contains(e.getReaction().getReactionEmote().getName());
            } else {
                if (!isSameAuthor(e.getMember()) || !isSamePlace(e.getGuild(), e.getTextChannel()))
                    return false;
                if (!e.getMessageId().equals(this.getMessage().getId()))
                    return false;
                if (e.getReaction().isSelf())
                    return false;
                return option.contains(e.getReaction().getReactionEmote().getName());
            }
        }
        return false;
    }

    public int selector(String choice) {
        for(int i = 0; i < option.size(); i++) {
            if(option.get(i) == null ? choice == null : option.get(i).equals(choice))
                return i;
        }
        return -1;
    }

    public abstract void action(int chose);

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public void addOption(String option) {
        this.option.add(option);
    }
}
