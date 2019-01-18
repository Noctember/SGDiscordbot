package net.steamgamers.discordbot.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
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
