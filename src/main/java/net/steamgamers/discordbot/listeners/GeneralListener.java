package net.steamgamers.discordbot.listeners;

import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.steamgamers.discordbot.DiscordBot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.listeners
 *
 * @author SweetKebab_
 * Created the 2019-01-29 at 17:42.
 */
public class GeneralListener extends ListenerAdapter {
    @Override
    public void onResume(ResumedEvent event) {
    }
}
