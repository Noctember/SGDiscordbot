package net.steamgamers.discordbot.commands.steam;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.DiscordBot;
import net.steamgamers.discordbot.commands.Command;
import net.steamgamers.discordbot.utils.GameMEAPI;
import net.steamgamers.discordbot.utils.GfxUtil;
import net.steamgamers.discordbot.utils.UtilString;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.util.Objects;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands.steam
 *
 * @author SweetKebab_
 * Created the 2019-02-03 at 17:29.
 */
public class Stats2Command extends Command {
    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            GameMEAPI api = new GameMEAPI("STEAM_0:1:151420642","ttt");
            api.prepare();
            BufferedImage profileImg = getUserAvatar(api.get("avatar"));
            String name = api.get("name");
            String contry = api.get("cn");
            String platerid = api.get("id");
            String uniqid = api.get("uniqueid");
            String time = UtilString.formatTime(Long.valueOf(api.get("time")));
            String last = UtilString.formatDurationToString(((System.currentTimeMillis()/1000)-Long.valueOf(api.get("lastconnect")))*1000)+" ago (Total: "+api.get("totalconnects")+" Connects)";
            String fav = api.get("favgun");

            BufferedImage result = new BufferedImage(
                    508, 177,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) result.getGraphics();
            Font defaultFont = new Font("Verdana, Arial, sans-serif", Font.BOLD, 12);
            BufferedImage backgroundImage = ImageIO.read(Objects.requireNonNull(DiscordBot.class.getClassLoader().getResource("background.png")));

            g.drawImage(backgroundImage, 0, 0, 508, 177, 0, 0, 508, 177, null);
            g.drawImage(profileImg, 1, 45, 152, 176, 0, 0, profileImg.getWidth(), profileImg.getHeight(), null);
            GfxUtil.addText(name, defaultFont, 156, 40, g, new Color(0xa0a0a0));
            GfxUtil.addText(contry, defaultFont, 275, 60, g, new Color(0xa0a0a0));
            GfxUtil.addText(platerid, defaultFont, 275, 85, g, new Color(0xa0a0a0));
            GfxUtil.addText(uniqid, defaultFont, 275, 105, g, new Color(0xa0a0a0));
            GfxUtil.addText(last, defaultFont, 275, 128, g, new Color(0xa0a0a0));
            GfxUtil.addText(time, defaultFont, 275, 150, g, new Color(0xa0a0a0));
            GfxUtil.addText(fav, defaultFont, 275, 172, g, new Color(0xa0a0a0));

            File file = new File("profile_v3_" + event.getAuthor().getId() + ".png");
            ImageIO.write(result, "png", file);
            event.getTextChannel().sendFile(file).submit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getUserAvatar(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "Steam-Gamers discord bot");
        BufferedImage profileImg;
        try {
            profileImg = ImageIO.read(connection.getInputStream());
        } catch (Exception ignored) {
            profileImg = ImageIO.read(Objects.requireNonNull(DiscordBot.class.getClassLoader().getResource("default_profile.jpg")));
        }
        return profileImg;
    }
}
