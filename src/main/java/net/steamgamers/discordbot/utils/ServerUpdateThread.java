package net.steamgamers.discordbot.utils;

import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import com.github.koraktor.steamcondenser.steam.servers.SourceServer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.utils
 *
 * @author SweetKebab_
 * Created the 2019-01-16 at 18:59.
 */
public class ServerUpdateThread implements Runnable {
    private final String serverName;
    private String serverNamee = "";
    private TextChannel name;
    private String serverIP;
    private int port;
    private String lastMap;
    private String msgID = "";
    private HashMap<String, Set<String>> serverPlayers;
    private Instant timestamp = null;
    private String url;
    private boolean wasOffline;
    private Queue<String> lastMaps;

    public ServerUpdateThread(TextChannel tc, String serverIP, int port, String serverName) {
        name = tc;
        this.serverIP = serverIP;
        this.port = port;
        this.serverName = serverName;
        wasOffline = false;
        lastMaps = new LinkedList<>();
    }

    public void run() {
        if(lastMaps.size() >= 6)
            lastMaps.poll();

        Queue<String> revQ = reverse(lastMaps);
        String lMaps = "1) %1\n2) %2\n3) %3\n4) %4\n5) %5";
        int i = 0;
        System.out.println(lastMaps);
        for (String name : revQ) {
            i++;
            lMaps = lMaps.replace("%" + i, name);
        }
        lMaps = lMaps.replace("%1", "Need data");
        lMaps = lMaps.replace("%2", "Need data");
        lMaps = lMaps.replace("%3", "Need data");
        lMaps = lMaps.replace("%4", "Need data");
        lMaps = lMaps.replace("%5", "Need data");


        SourceServer server;
        int attempts = 0;
        parentloop:
        while (true) {
            try {
                server = new SourceServer(InetAddress.getByName(serverIP), port);
                server.initialize();
                break;
            } catch (NullPointerException | TimeoutException | SteamCondenserException | UnknownHostException e) {
                attempts++;
                if (attempts >= 10) {
                    wasOffline = true;
//                    if (!wasOffline)
//                        name.getManager().setTopic(name.getTopic().substring(1 ,name.getTopic().length()-18) + "(Server is offline)").submit();
                    return;
                }
                continue parentloop;
            }
        }

//        if (wasOffline)
//            name.getManager().setTopic(name.getTopic().substring(1 ,name.getTopic().length()-19) + "(Server is online)").submit();
        String serverInfo = server.toString();
        server.disconnect();
        String map;
        try {
            map = serverInfo.split("mapName: ")[1].split("Players:")[0].replace("\n", "");
        } catch (ArrayIndexOutOfBoundsException ioob) {
            map = "ERROR";
        }
        if (serverNamee.isEmpty())
            serverNamee = serverInfo.split("serverName: ")[1].split("  secure: ")[0].replace("\n", "");
        int currentPlayers = Integer.parseInt(serverInfo.split("numberOfPlayers: ")[1].split(" ")[0].replace("\n", ""));
        int maxPlayers = Integer.parseInt(serverInfo.split("maxPlayers: ")[1].split(" ")[0].replace("\n", ""));
        url = "http://158.69.59.239/mapimgs/" + StringUtils.substring(map, 0, 31) + ".jpg";
        try {
            Jsoup.connect(url).ignoreContentType(true).get();
        } catch (UnsupportedMimeTypeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            url = "https://image.gametracker.com/images/maps/160x120/csgo/" + StringUtils.substring(map, 0, 31) + ".jpg";
        }

        if (currentPlayers > maxPlayers) {
            currentPlayers = maxPlayers;
        }

        String players = currentPlayers + "/" + maxPlayers;

        if (lastMap == null || !lastMap.equalsIgnoreCase(map)) {
            timestamp = Instant.now();
            if (!msgID.isEmpty())
                name.getMessageById(msgID).queue((msg) -> {
                    msg.delete().submit();
                });

            EmbedBuilder embed = null;
            try {
                embed = new EmbedBuilder()
                        .setColor(UtilString.averageColorFromURL(new URL(url), true))
                        .setTimestamp(timestamp).setThumbnail(url).setTitle(serverNamee)
                        .addField(Emoji.MAP + " Last maps", lMaps, false)
                        .setDescription("Now Playing: **" + map.replace("_", "\\_") + "**\nPlayers Online: **" + players + "**\nQuick Join: **steam://connect/" + serverIP + ":" + port + "**");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            name.sendMessage(embed.build()).queue((msg) -> {
                msgID = msg.getId();
            });
            lastMap = map;
        } else {
            if (!msgID.isEmpty() && !(msgID == null)) {
                if(!lastMaps.contains(map))
                    lastMaps.add(map);
                EmbedBuilder embed = null;
                try {
                    embed = new EmbedBuilder()
                            .setColor(UtilString.averageColorFromURL(new URL(url), true))
                            .setTimestamp(timestamp).setThumbnail(url).setTitle(serverNamee)
                            .addField(Emoji.MAP + " Last maps", lMaps, false)
                            .setDescription("Now Playing: **" + map.replace("_", "\\_") + "**\nPlayers Online: **" + players + "**\nQuick Join: **steam://connect/" + serverIP + ":" + port + "**");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                EmbedBuilder finalEmbed = embed;
                name.getMessageById(msgID).queue((msg) -> {
                    msg.editMessage(finalEmbed.build()).submit();
                });
            }
        }

    }

    private Queue<String> reverse(Queue<String> queue) {
        List<String> collect = new ArrayList<>(queue);
        Collections.reverse(collect);
        return new LinkedList<>(collect);
    }

    public void delete() {
        name.getMessageById(msgID).queue(msg -> {
            msg.delete().submit();
        });
    }
}
