package net.steamgamers.discordbot.commands.channels;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.steamgamers.discordbot.utils.Emoji;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands.channels
 *
 * @author SweetKebab_
 * Created the 2019-01-20 at 12:39.
 */
public class LocknKey {
    private File directory = new File("channels/");

    public static void createVC(MessageReceivedEvent event, String name, int size, boolean inform) {
        final String[] id = {""};
        if (name == null || name.isEmpty())
            name = event.getAuthor().getName() + "'s Room";

        ChannelAction<VoiceChannel> voice = event.getGuild().getCategoryById("536602155470290963").createVoiceChannel(name);
        if (size != 0)
            voice.setUserlimit(size);
        if (inform)
            event.getTextChannel().sendMessage(Emoji.SUCCESS + " Created a voice channel named `" + name + "`");
        voice.queue(voiceChannel -> {
            voiceChannel.createPermissionOverride(event.getGuild().getPublicRole()).setDeny(Permission.VOICE_CONNECT, Permission.VIEW_CHANNEL).submit();
            voiceChannel.createPermissionOverride(event.getMember()).setAllow(Permission.VOICE_CONNECT, Permission.VIEW_CHANNEL, Permission.VOICE_MUTE_OTHERS, Permission.VOICE_DEAF_OTHERS).submit();
            id[0] = voiceChannel.getId();
        });
    }
}
