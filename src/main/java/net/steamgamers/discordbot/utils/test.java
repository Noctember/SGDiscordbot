package net.steamgamers.discordbot.utils;

import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import com.github.koraktor.steamcondenser.steam.servers.SourceServer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.utils
 *
 * @author SweetKebab_
 * Created the 2019-01-18 at 16:58.
 */
public class test {
    public static void main(String[] args) throws TimeoutException, SteamCondenserException, UnknownHostException {
        SourceServer server = null;
        server = new SourceServer(InetAddress.getByName("66.70.180.186"), 27015);
        server.initialize();
        System.out.println(server.getPlayers().toString());
    }

}
