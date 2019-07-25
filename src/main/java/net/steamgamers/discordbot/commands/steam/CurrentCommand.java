package net.steamgamers.discordbot.commands.steam;

import com.github.koraktor.steamcondenser.steam.servers.SourceServer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.commands.Command;
import net.steamgamers.discordbot.utils.UtilString;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.URL;
import java.time.Instant;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands.steam
 *
 * @author SweetKebab_
 * Created the 2019-01-16 at 18:56.
 */
public class CurrentCommand extends Command {
    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        if (guild.getId().equals("399344695970496512")) {
//        if (guild.getId().equals("169274032355540992")) { FOR TESTING
            try {
                SourceServer server = null;

                switch (event.getTextChannel().getId()) {
                    //ZE534890763452940298
                    case "534890763452940298":
//                    case "169274032355540992":
                        System.out.println("ZE");
                        server = new SourceServer(InetAddress.getByName("66.70.180.186"), 27015);
                        break;
                    //JB
                    case "534896149283340308":
//                    case "337155536917233666":

                        System.out.println("ZEASd");
                        server = new SourceServer(InetAddress.getByName("70.42.74.162"), 27017);
                        break;
                    //TTT
                    case "534897335235248128":
//                    case "347298379568513024":
                        server = new SourceServer(InetAddress.getByName("66.150.121.70"), 27015);
                        break;
                    //MG
                    case "534897360631758849":
//                    case "429500597511782406":
                        server = new SourceServer(InetAddress.getByName("66.150.121.70"), 27016);
                        break;
                     //SCRIM
                    case "534897400708595740":
                        server = new SourceServer(InetAddress.getByName("70.42.74.162"), 27016);
                        break;
                }
                try {
                    server.initialize();
                } catch (Exception ex) {ex.printStackTrace();}
                String serverInfo = server.toString();
                String map = serverInfo.split("mapName: ")[1].split("Players:")[0].replace("\n", "");
                String serverName = serverInfo.split("serverName: ")[1].split("  secure: ")[0].replace("\n", "");
                int currentPlayers = Integer.parseInt(serverInfo.split("numberOfPlayers: ")[1].split(" ")[0].replace("\n", ""));
                int maxPlayers = Integer.parseInt(serverInfo.split("maxPlayers: ")[1].split(" ")[0].replace("\n", ""));
                String url = "http://158.69.59.239/mapimgs/" + StringUtils.substring(map, 0, 31) + ".jpg";
                try {
                    Jsoup.connect(url).get();
                } catch (HttpStatusException | ConnectException e) {
                    url = "https://image.gametracker.com/images/maps/160x120/csgo/" + StringUtils.substring(map, 0, 31) + ".jpg";
                } catch (UnsupportedMimeTypeException e) {}

                String players = currentPlayers + "/" + maxPlayers;
//                String playersl = String.join(", ", server.getPlayers().keySet());
                StringJoiner playersj = new StringJoiner(", " , "", "");
                System.out.println(server.getPlayers().toString());
                server.getPlayers().forEach((k,v)->playersj.add(v.getName()));
                String playersl = playersj.toString();
                EmbedBuilder embed = new EmbedBuilder()
                        .setColor(UtilString.averageColorFromURL(new URL(url), true))
                        .setTimestamp(Instant.now()).setThumbnail(url).setTitle(serverName)
                        .setDescription("Now Playing: **" + map.replace("_", "\\_") + "**\nPlayers Online: **" + players + "**\nOnline players: **`"+ playersl+"`**");
                event.getTextChannel().sendMessage(embed.build()).queue(message -> message.delete().queueAfter(2, TimeUnit.MINUTES));
                server.disconnect();
                event.getMessage().delete().submit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
