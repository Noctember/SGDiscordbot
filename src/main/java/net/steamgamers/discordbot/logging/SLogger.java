package net.steamgamers.discordbot.logging;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jdk.nashorn.internal.objects.Global;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.steamgamers.discordbot.DiscordBot;
import net.steamgamers.discordbot.utils.Emoji;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static net.dv8tion.jda.api.requests.ErrorResponse.*;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.logging
 *
 * @author SweetKebab_
 * Created the 2019-01-16 at 18:04.
 */
public class SLogger {
    public static Logger startLogger = Logger.getLogger(DiscordBot.class.getName());
    public static Logger errorLogger = Logger.getLogger("Error");
    public static Logger commandLogger = Logger.getLogger("command");

    /**
     * Logging when bot status changed
     * @param msg the message for logging
     */
    public static void updateLog(String msg) {
        try {
            FileHandler fh = new FileHandler("main.txt", true);
            startLogger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            startLogger.setUseParentHandlers(false);

            startLogger.info(msg);
            fh.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Logging when an exception is thrown
     * @param ex the Exception for logging
     * @param obj the event/class for getting guild name/author name
     * @param at the exception source (class name)
     * @param cause cause of the exception
     */
    public static void errorLog(Exception ex, Object obj, String at, String cause) {
        try {
            FileHandler fhe = new FileHandler("error.txt", true);
            errorLogger.addHandler(fhe);
            SimpleFormatter formatter = new SimpleFormatter();
            fhe.setFormatter(formatter);
            errorLogger.setUseParentHandlers(false);

            /* Get where the exception is from */
            String from;
            if(obj instanceof MessageReceivedEvent) {
                MessageReceivedEvent event = (MessageReceivedEvent) obj;
                if (event.getChannelType() == event.getChannelType().TEXT)
                    from = "guild: " + event.getGuild().getName();
                else if (event.getChannelType() == event.getChannelType().PRIVATE)
                    from = "PM: " + event.getAuthor().getName();
                else
                    from = ": Unknown (From unknown channel type.)";

                /* Log the exception in DiscordBot Server #bug_report */
                handleExceptionLog(ex, event, toHasteBin(stackToString(ex)));
            } else if(obj != null) {
                from = "Class: " + obj.toString();
            } else {
                from = "Unknown";
            }

            /* Log the exception in local file */
            Logger.getGlobal().log(Level.WARNING, "Error in " + at + " from " + from);
            errorLogger.log(Level.WARNING, "From" + from + "\n\t Cause: " + at + " -> " + cause, ex);
            fhe.close();

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Logging when a command is called.
     * @param event the event for getting guild name/author name
     * @param command the command called
     * @param description the description of this command call
     */
    public static void commandLog(MessageReceivedEvent event, String command, String description) {
        try {
            FileHandler fhc = new FileHandler("command.txt", true);
            commandLogger.addHandler(fhc);
            SimpleFormatter formatter = new SimpleFormatter();
            fhc.setFormatter(formatter);
            commandLogger.setUseParentHandlers(false);

            String from;
            if(event.getChannelType() == event.getChannelType().TEXT)
                from = " guild : " + event.getGuild().getName();
            else if (event.getChannelType() == event.getChannelType().PRIVATE)
                from = " PM: " +event.getAuthor().getName();
            else
                from = ": Unknown (Probably local source)";

            commandLogger.log(Level.INFO, "From" + from + "\n\tcommand: " + command + "\tDescription: " + description);
            fhc.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Return the stack trace of an exception
     * @param ex
     * @return
     */
    public static String stackToString(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter w = new PrintWriter(sw);
        ex.printStackTrace(w);
        return sw.toString();
    }

    /**
     * Paste a String to HasteBin and return the URL
     *
     * @param message The string to be sent in the body of the POST request
     * @return A formatted URL which links to the pasted file
     */
    public static String toHasteBin(String message) {
        try {
            JsonNode obj = Unirest.post("https://hastebin.com/documents")
                    .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)")
                    .header("Content-Type", "text/plain")
                    .body(message)
                    .asJson()
                    .getBody();

            return "https://hastebin.com/" + obj.getObject().getString("key");

        } catch (UnirestException e) {
            SLogger.errorLog(e, null, "Pasting to HasteBin", "Probably server side");
        }
        return null;
    }

    /**
     * Log the exception to DiscordBot server's #bug-report
     * @param ex
     * @param e
     * @param hasteBinURL
     */
    public static void handleExceptionLog(Exception ex, MessageReceivedEvent e, String hasteBinURL)
    {
        String user = "", id = "", from = "", from2 = "None", channel = "";
        EmbedBuilder error = new EmbedBuilder().setAuthor(ex.getClass().getName(), hasteBinURL, null)
                .setColor(Color.RED).setTimestamp(Instant.now())
                .addField("User", e.getAuthor().getName()+" #"+e.getAuthor().getDiscriminator()+" ("+e.getAuthor().getId()+")", true);

        if (e.isFromType(ChannelType.TEXT)) {
            from = "Guild";
            from2 = e.getGuild().getName()+"("+e.getGuild().getId()+")";
            channel = e.getChannel().getName()+" ("+e.getChannel().getId()+")";
        } else if (e.isFromType(ChannelType.PRIVATE)) {
            from = "PM";
            from2 = e.getAuthor().getName();
            channel = e.getPrivateChannel().getName()+" ("+e.getPrivateChannel().getId()+")";
        }

        error.addField(from, from2, true)
                .addField("Channel", channel, true)
                .addField("Stack Trace", "**[" + hasteBinURL + "]("+hasteBinURL+")**", true);
//        DiscordBot.getGuild(Global.B_SERVER_ID).getTextChannelById(Global.B_SERVER_ERROR).sendMessage(error.build()).queue();
    }

    public static boolean errorResponseHandler(ErrorResponseException ere, MessageReceivedEvent e) {
        boolean handled = true;
        String error = Emoji.ERROR + " ";
        switch (ere.getErrorResponse()) {
            case CANNOT_SEND_TO_USER:
                e.getChannel().sendMessage(error+"Impossible d'envoyer un  message à "+e.getAuthor().getName()).queue();
                break;
            case EMBED_DISABLED:
                e.getChannel().sendMessage(error+"Activez les embeds pour que je puisse parler librement.").queue();
                break;
            case INVALID_BULK_DELETE:
            case INVALID_BULK_DELETE_MESSAGE_AGE:
                e.getChannel().sendMessage(error+"Erreur lors de la suppression des messages. "+
                        " Les messages peuvent être trop anciens (plus de 2 semaines).").queue();
                break;
            case MISSING_ACCESS:
                e.getChannel().sendMessage(error+"Accès manquant.").queue();
                break;
            case UNKNOWN_GUILD:
            case UNKNOWN_CHANNEL:
            case UNKNOWN_MEMBER:
                errorLog(ere,e,"ErrorResponseHandler","Unknown guild,channel,or member");
                break;
            default:
                handled = false;
                break;
        }

        return handled;
    }
}

