package net.steamgamers.discordbot.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.DiscordBot;
import net.steamgamers.discordbot.utils.UtilString;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.listeners
 *
 * @author SweetKebab_
 * Created the 2019-01-16 at 18:17.
 */
public class CommandParser {
    public CommandContainer parse(String raw, MessageReceivedEvent e) {
        ArrayList<String> split = new ArrayList<>();

        String beheaded = "";

        if (raw.startsWith(DiscordBot.getGuild(e.getGuild()).getPrefix())) {
            String a = UtilString.getMessage(raw.split(" "), 1);
            beheaded = raw.split(" ")[0].substring(DiscordBot.getGuild(e.getGuild()).getPrefix().length()) + " " + a;

        } else if (raw.startsWith("@" + e.getGuild().getSelfMember().getEffectiveName()))
            beheaded = raw.replace("@" + e.getGuild().getSelfMember().getEffectiveName() + " ", "");

        String[] splitBeheaded = beheaded.split("\\s+");

        split.addAll(Arrays.asList(splitBeheaded));

        String invoke = split.get(0).toLowerCase();
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);

        return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args, e);
    }

    public CommandContainer parsePrivate(String rw, MessageReceivedEvent e) {
        ArrayList<String> split = new ArrayList<>();
        String raw = rw;

        String beheaded = "";
        if (raw.startsWith("!"))
            beheaded = raw.replaceFirst("!", "");
        else if (raw.startsWith("@" + e.getJDA().getSelfUser().getName()))
            beheaded = raw.replaceFirst("@" + e.getGuild().getSelfMember().getEffectiveName() + " ", "");
        else
            beheaded = raw;

        String[] splitBeheaded = beheaded.split("\\s+");

        split.addAll(Arrays.asList(splitBeheaded));

        String invoke = split.get(0);
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);

        return new CommandContainer(raw, null, splitBeheaded, invoke, args, e);
    }

    public String[] parseRespond(String rw, MessageReceivedEvent e) {
        String[] split = rw.split("\\s+");
        String[] splitted = new String[2];
        splitted[0] = split[0];
        splitted[1] = "";
        for (int i = 0; i < split.length; i++) {
            if (i != 0)
                splitted[1] += split[i] + " ";
        }

        return splitted;
    }

    public class CommandContainer {
        public final String raw;
        public final String beheaded;
        public final String[] splitBeheaded;
        public final String invoke;
        public final String[] args;
        public final MessageReceivedEvent event;

        public CommandContainer(String rw, String Beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent e) {
            this.raw = rw;
            this.beheaded = Beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            this.event = e;
        }
    }
}
