package net.steamgamers.discordbot.commands.restricted;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.steamgamers.discordbot.DiscordBot;
import net.steamgamers.discordbot.commands.Command;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.commands.restricted
 *
 * @author SweetKebab_
 * Created the 2019-01-28 at 21:19.
 */
public class EvalCommand extends Command {
    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals("118499178790780929") || event.getMember().getRoles().contains(event.getGuild().getRoleById("399345599297617923"))) {

            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

            /* Imports */
            try {
                engine.eval("var imports = new JavaImporter(java.io, java.lang, java.util);");
            } catch (ScriptException ex) {
                ex.printStackTrace();
            }

            /* Put string representations */
            engine.put("jda", event.getJDA());
            engine.put("api", event.getJDA());
            engine.put("main", DiscordBot.class);

            engine.put("message", event.getMessage());
            engine.put("guild", event.getGuild());
            engine.put("server", event.getGuild());
            engine.put("channel", event.getChannel());
            engine.put("tc", event.getTextChannel());
            engine.put("pm", event.getPrivateChannel());
            engine.put("vc", event.getMember().getVoiceState().getChannel());

            engine.put("author", event.getAuthor());
            engine.put("member", event.getMember());
            engine.put("self", event.getGuild().getSelfMember().getUser());
            engine.put("selfmem", event.getGuild().getSelfMember());

            ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

            ScheduledFuture<?> future = service.schedule(() -> {

                /* Initialize Objects */
                long startExec = System.currentTimeMillis();
                Object out = null;
                EmbedBuilder message = new EmbedBuilder()
                        .setColor(Color.BLACK).setAuthor("Eval", null, event.getGuild().getSelfMember().getUser().getEffectiveAvatarUrl())
                        .setFooter("Requested by " + event.getMember().getEffectiveName(), event.getAuthor().getEffectiveAvatarUrl())
                        .setTimestamp(Instant.now());

                try {
                    /* Build input script */
                    String script = "";
                    for (int i = 0; i < args.length; i++) {
                        args[i] = args[i].replace("```java", "").replace("```", "");
                        script += i == args.length - 1 ? args[i] : args[i] + " ";
                    }
                    message.addField(" Input", "```java\n\n" + script + "```", false);

                    /* Output */
                    out = engine.eval(script);
                    message.addField(" Output", "```java\n\n" + out.toString() + "```", false);

                    /* Exception */
                } catch (Exception ex) {
                    message.addField(" Error", "```java\n\n" + ex.getMessage() + "```", false);
                }
                /* Timer */
                message.addField(" Timing", System.currentTimeMillis() - startExec + " milliseconds", false);
                event.getChannel().sendMessage(message.build()).queue();

                service.shutdownNow();

            }, 0, TimeUnit.MILLISECONDS);
        }
    }
}
