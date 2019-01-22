package net.steamgamers.discordbot.utils;

import com.github.koraktor.steamcondenser.steam.servers.SourceServer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.steamgamers.discordbot.logging.SLogger;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.URL;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.utils
 *
 * @author SweetKebab_
 * Created the 2019-01-16 at 18:59.
 */
public class ServerUpdateThread implements Runnable {
    private final String serverName;
    private Thread thread;
    private TextChannel name;
    private String serverIP;
    private int port;
    private String lastMap;
    private String msgID = "";
    private HashMap<String, Set<String>> serverPlayers;
    private Instant timestamp = null;
    private String url;

    public ServerUpdateThread(TextChannel tc, String serverIP, int port, String serverName) {
        name = tc;
        this.serverIP = serverIP;
        this.port = port;
        this.serverName = serverName;
    }


    public void start() {
        if (thread == null) {
            thread = new Thread(this, serverIP + ":" + port);
            thread.start();
        }
    }

    public void run() {
        try {
            SourceServer server = new SourceServer(InetAddress.getByName(serverIP), port);

            try {
                server.initialize();
            } catch (Exception ex) {
            }

            String serverInfo = server.toString();
            String map;
            try {
                map = serverInfo.split("mapName: ")[1].split("Players:")[0].replace("\n", "");
            } catch (ArrayIndexOutOfBoundsException ioob) {
                map = "ERROR";
            }
            String serverName = serverInfo.split("serverName: ")[1].split("  secure: ")[0].replace("\n", "");
            int currentPlayers = Integer.parseInt(serverInfo.split("numberOfPlayers: ")[1].split(" ")[0].replace("\n", ""));
            int maxPlayers = Integer.parseInt(serverInfo.split("maxPlayers: ")[1].split(" ")[0].replace("\n", ""));
            url = "http://158.69.59.239/mapimgs/" + StringUtils.substring(map, 0, 31) + ".jpg";
            try {
                Jsoup.connect(url).get();
            } catch (HttpStatusException | ConnectException e) {
                url = "https://image.gametracker.com/images/maps/160x120/csgo/" + StringUtils.substring(map, 0, 31) + ".jpg";
            } catch (UnsupportedMimeTypeException e) {}

            if (currentPlayers > maxPlayers) {
                currentPlayers = maxPlayers;
            }

            String players = currentPlayers + "/" + maxPlayers;

            if (lastMap == null || !lastMap.equalsIgnoreCase(map)) {
                timestamp = Instant.now();
                if(!msgID.isEmpty())
                    name.getMessageById(msgID).queue((msg) -> {
                        msg.delete().submit();
                    });

                EmbedBuilder embed = new EmbedBuilder()
                        .setColor(UtilString.averageColorFromURL(new URL(url), true))
                        .setTimestamp(timestamp).setThumbnail(url).setTitle(serverName)
                        .setDescription("Now Playing: **" + map.replace("_", "\\_") + "**\nPlayers Online: **" + players + "**\nQuick Join: **steam://connect/" + serverIP + ":" + port + "**");
                name.sendMessage(embed.build()).queue((msg) -> {
                    msgID = msg.getId();
                });
                    server.disconnect();
                lastMap = map;
            } else {
                if (!msgID.isEmpty() && !(msgID == null)) {
                    EmbedBuilder embed = new EmbedBuilder()
                            .setColor(UtilString.averageColorFromURL(new URL(url), true))
                            .setTimestamp(timestamp).setThumbnail(url).setTitle(serverName)
                            .setDescription("Now Playing: **" + map.replace("_", "\\_") + "**\nPlayers Online: **" + players + "**\nQuick Join: **steam://connect/" + serverIP + ":" + port + "**");
                    name.getMessageById(msgID).queue((msg) -> {
                        msg.editMessage(embed.build()).submit();
                    });
                }
            }

//            }
        } catch (Exception e) {
            SLogger.handleExceptionLog(e, null, SLogger.stackToString(e));
        }
    }
}
