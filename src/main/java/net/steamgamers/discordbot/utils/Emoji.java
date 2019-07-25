package net.steamgamers.discordbot.utils;

import com.vdurmont.emoji.EmojiParser;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot
 *
 * @author SweetKebab_
 * Created the 2019-01-16 at 18:02.
 */
public class Emoji {
    public final static String SUCCESS = EmojiParser.parseToUnicode(":white_check_mark:");
    public final static String ENVELOPE = EmojiParser.parseToUnicode(":incoming_envelope:");
    public static final String BAN = EmojiParser.parseToUnicode(":hammer:");
    public static final String ERROR = EmojiParser.parseToUnicode(":warning:");
    public final static String YES = EmojiParser.parseToUnicode(":o:");
    public final static String NO = EmojiParser.parseToUnicode(":x:");
    public final static String IN = EmojiParser.parseToUnicode(":inbox_tray:");
    public final static String OUT = EmojiParser.parseToUnicode(":outbox_tray:");
    public final static String PING = EmojiParser.parseToUnicode(":ping_pong:");
    public final static String HEART_BEAT = EmojiParser.parseToUnicode(":heartbeat:");
    public final static String STOPWATCH = EmojiParser.parseToUnicode(":stopwatch:");
    public final static String STATUS = EmojiParser.parseToUnicode(":vertical_traffic_light:");
    public final static String STATISTIC = EmojiParser.parseToUnicode(":bar_chart:");
    public final static String GUILDS = EmojiParser.parseToUnicode(":card_file_box:");
    public final static String SHARDS = EmojiParser.parseToUnicode(":file_cabinet:");
    public final static String TEXT = EmojiParser.parseToUnicode(":speech_balloon:");
    public final static String SPY = EmojiParser.parseToUnicode(":spy:");
    public final static String INFORMATION = EmojiParser.parseToUnicode(":information_source:");
    public final static String UP = EmojiParser.parseToUnicode(":arrow_up:");
    public final static String DOWN = EmojiParser.parseToUnicode(":arrow_down:");
    public final static String RIGHT = EmojiParser.parseToUnicode(":arrow_right:");
    public final static String LEFT = EmojiParser.parseToUnicode(":arrow_left:");
    public final static String INVITE = EmojiParser.parseToUnicode(":postbox:");

    public final static String MUSIC = EmojiParser.parseToUnicode(":musical_keyboard:");
    public final static String PLAYER = EmojiParser.parseToUnicode(":black_right_pointing_triangle_with_double_vertical_bar:");
    public final static String GLOBE = EmojiParser.parseToUnicode(":globe_with_meridians:");
    public final static String NOTES = EmojiParser.parseToUnicode(":notes:");
    public final static String FM = EmojiParser.parseToUnicode(":arrow_heading_down:");
    public final static String RADIO = EmojiParser.parseToUnicode(":radio_button:");
    public final static String AUTOPLAY = EmojiParser.parseToUnicode(":leftwards_arrow_with_hook:");
    public final static String REPEAT = EmojiParser.parseToUnicode(":repeat:");
    public final static String REPEAT_SINGLE = EmojiParser.parseToUnicode(":repeat_one:");
    public final static String PAUSE = EmojiParser.parseToUnicode(":double_vertical_bar:");
    public final static String NEXT_TRACK = EmojiParser.parseToUnicode(":black_right_pointing_double_triangle_with_vertical_bar:");
    public final static String UP_VOTE = EmojiParser.parseToUnicode(":arrow_up_small:");
    public final static String RESUME = EmojiParser.parseToUnicode(":arrow_forward:");
    public final static String JUMP = EmojiParser.parseToUnicode(":left_right_arrow:");
    public final static String VOLUME_NONE = EmojiParser.parseToUnicode(":mute:");
    public final static String VOLUME_LOW = EmojiParser.parseToUnicode(":sound:");
    public final static String VOLUME_HIGH = EmojiParser.parseToUnicode(":loud_sound:");
    public final static String SHUFFLE = EmojiParser.parseToUnicode(":twisted_rightwards_arrows:");
    public final static String STOP = EmojiParser.parseToUnicode(":black_square_for_stop:");

    public final static String FILM_PROJECTOR = EmojiParser.parseToUnicode(":film_projector:");
    public final static String FILM_FRAMES = EmojiParser.parseToUnicode(":film_frames:");
    public final static String DATE = EmojiParser.parseToUnicode(":date:");
    public final static String STAR = EmojiParser.parseToUnicode(":star:");
    public final static String STARS = EmojiParser.parseToUnicode(":stars:");
    public final static String TROPHY = EmojiParser.parseToUnicode(":trophy:");
    public final static String BOOK = EmojiParser.parseToUnicode(":book:");
    public static final String MAP = EmojiParser.parseToUnicode(":map:");
    public static String SEARCH = EmojiParser.parseToUnicode(":mag:");
    public final static String GAME = EmojiParser.parseToUnicode(":video_game:");

    public final static String ONE = EmojiParser.parseToUnicode(":one:");
    public final static String TWO = EmojiParser.parseToUnicode(":two:");
    public final static String THREE = EmojiParser.parseToUnicode(":three:");
    public final static String FOUR = EmojiParser.parseToUnicode(":four:");
    public final static String FIVE = EmojiParser.parseToUnicode(":five:");
    public final static String SIX = EmojiParser.parseToUnicode(":six:");
    public final static String SEVEN = EmojiParser.parseToUnicode(":seven:");
    public final static String EIGHT = EmojiParser.parseToUnicode(":eight:");
    public final static String NINE = EmojiParser.parseToUnicode(":nine:");
    public final static String ZERO = EmojiParser.parseToUnicode(":zero:");
    public final static String HUNDRED = EmojiParser.parseToUnicode(":100:");

    public final static String GUILD_ONLINE = "<:Online:318443011941990402>";
    public final static String GUILD_IDLE = "<:Absent:318443014064177153>";
    public final static String GUILD_DND = "<:Do_Not_Disturb:318721182998462474>";
    public final static String GUILD_OFFLINE = "<:Offline:318443014064177152>";
    public final static String GUILD_STREAMING = "<:streaming:313956277132853248>";

    public final static String CHECK = "<:checked:432201649100947456>";
    public final static String UNCHECK = "<:unchecked:432222008231591957>";


    public static String numToEmoji(int digit) {
        String output = "";
        switch(digit) {
            case 1:
                output += Emoji.ONE;
                break;
            case 2:
                output += Emoji.TWO;
                break;
            case 3:
                output += Emoji.THREE;
                break;
            case 4:
                output += Emoji.FOUR;
                break;
            case 5:
                output += Emoji.FIVE;
                break;
            case 6:
                output += Emoji.SIX;
                break;
            case 7:
                output += Emoji.SEVEN;
                break;
            case 8:
                output += Emoji.EIGHT;
                break;
            case 9:
                output += Emoji.NINE;
                break;
            default:
                output += Emoji.ZERO;
                break;
        }

        return output;
    }
}