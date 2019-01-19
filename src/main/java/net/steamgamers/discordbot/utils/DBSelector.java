package net.steamgamers.discordbot.utils;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.utils
 *
 * @author SweetKebab_
 * Created the 2019-01-18 at 08:46.
 */
public abstract class DBSelector<T> {

    private Message message;
    private Member author;
    private Guild guild;
    private MessageChannel channel;

    public DBSelector(Message msg, Member ar) {
        this.message = msg;
        this.author = ar;
        this.guild = msg.getGuild();
        this.channel = msg.getChannel();
    }

    public DBSelector() {
        this.message = null;
        this.author = null;
        this.guild = null;
        this.channel = null;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public void setchannel(MessageChannel textchannel) {
        this.channel = textchannel;
    }

    public abstract boolean isSelection(GenericMessageEvent event);

    public abstract int selector(String choice);

    protected boolean isSamePlace(Guild g, TextChannel tc) {
        return g.getId().equals(this.guild.getId()) && tc.getId().equals(this.channel.getId());
    }

    protected boolean isSameAuthor(Member mem) {
        return mem.getUser().getId().equals(this.author.getUser().getId());
    }

}
