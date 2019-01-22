package net.steamgamers.discordbot.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.utils
 *
 * @author SweetKebab_
 * Created the 2019-01-16 at 18:18.
 */
public class UtilString {

    public static String getMessage(String[] args, int min) {
        return Arrays.stream(args).skip(min).collect(Collectors.joining(" ")).trim();
    }
    public static String formatDurationToString(Long duration) {
        TimeUnit u = TimeUnit.MILLISECONDS;
        long hours = u.toHours(duration) % 24;
        long minutes = u.toMinutes(duration) % 60;
        long seconds = u.toSeconds(duration) % 60;
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
    public static String formatTime(Long time) {
        TimeUnit u = TimeUnit.MILLISECONDS;
        long days = u.toDays(time) % 7;
        long hours = u.toHours(time) % 24;
        long minutes = u.toMinutes(time) % 60;
        long seconds = u.toSeconds(time) % 60;
        String day = "";
        String hour = "";
        String minute = "";
        String second = "";
        if (days > 0) {
            day = String.format("%2d day(s), ", days);
        }
        if (hours > 0) {
            hour = String.format("%2d hour(s), ", hours);
        }
        if (minutes > 0) {
            minute = String.format("%2d minute(s), ", minutes);
        }
        if (seconds > 0) {
            second = String.format("%2d second(s)", seconds);
        }
        return day + hour + minute + second + " ";
    }
    public static Color averageColorFromURL(URL url, boolean handleExceptions) {
        BufferedImage image = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
            image = ImageIO.read(connection.getInputStream());

            final int pixels = image.getWidth() * image.getHeight();
            int red = 0;
            int green = 0;
            int blue = 0;

            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    Color pixel = new Color(image.getRGB(x, y));

                    red += pixel.getRed();
                    green += pixel.getGreen();
                    blue += pixel.getBlue();
                }
            }

            return new Color(red / pixels, green / pixels, blue / pixels);
        } catch (Exception e) {
            if (handleExceptions) {
                return new Color(0, 154, 255);
            } else {
                return null;
            }
        }
    }

}
