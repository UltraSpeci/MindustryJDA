package ml.itzblacky.mindustryjda;

import io.anuke.arc.util.Log;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.Map;

import static ml.itzblacky.mindustryjda.Utils.ColorUtils.removeColorString;
import static ml.itzblacky.mindustryjda.Utils.ConfigUtils.getString;

public class Discord {
    private JDA jda;
    private TextChannel channel;
    private Map<String, Object> config;

    public void initJDA() {
        config = Main.getConfigMap();
        String botToken = getString("bot_token");
        try {
            jda = new JDABuilder(botToken)
                    .setActivity(Activity.playing(getString("playing_message")))
                    .addEventListeners(new DiscordMessageListener(this))
                    .build()
                    .awaitReady();
        } catch (LoginException e) {
            Log.info("Error logging into discord! Perhaps you have not changed the bot token yet?");
        } catch (InterruptedException e) {
            Log.info("Error logging into discord!");
            e.printStackTrace();

        }

        if (jda != null) {
            setMainTextChannel();
        }
    }

    private void setMainTextChannel() {
        TextChannel chnl = jda.getTextChannelById(getString("chat_channel_id"));
        try {
            if (chnl == null) {
                Log.info("Chat channel not found! Perhaps you have not changed the text channel id yet?");
            } else {
                channel = chnl;
                Log.info("Channel found! forwarding texts..");
                sendDiscordMessage("Server started!");
            }
        } catch (NullPointerException e) {
            Log.info("Chat channel not found! Perhaps you have not changed the text channel id yet?");
        }
    }

    public void sendDiscordMessage(String msg) {
        channel.sendMessage(removeColorString(msg)).queue();
    }

    public JDA getJda() {
        return jda;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public static Role getTopRole(Member member) {
        return member.getRoles().size() != 0 ? member.getRoles().get(0) : null;
    }

    public static String getHexColor(Member member) {
        Role role = getTopRole(member);
        if (role == null) return "#FFFFFF";
        Color color = role.getColor();
        if (color == null) return "#FFFFFF";
        return String.format("#%02x%02x%02x", color.getRed(), color.getBlue(), color.getGreen());
    }
}
