package ml.itzblacky.mindustryjda;

import io.anuke.arc.util.Log;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.security.auth.login.LoginException;
import java.util.Map;

import static ml.itzblacky.mindustryjda.Utils.getString;

public class Discord {
    private JDA jda;
    private TextChannel channel;
    private Map<String, Object> config;

    public void initJDA() {
        config = Main.getConfigMap();
        String botToken = getString(config, "bot_token");
        try {
            jda = new JDABuilder(botToken)
                    .setActivity(Activity.playing(getString(config, "playing_message")))
                    .addEventListeners(new DiscordMessageListener(this))
                    .build()
                    .awaitReady();
        } catch (LoginException e) {
            Log.info("Error logging into discord! Perhaps you have not changed the bot token yet?");
            Main.disablePlugin();
        } catch (InterruptedException e) {
            Log.info("Error loggin into discord!");
            e.printStackTrace();
            Main.disablePlugin();

        }
        if (jda != null) {
            setMainTextChannel();
        }
    }

    private void setMainTextChannel() {
        TextChannel chnl = jda.getTextChannelById(getString(config, "chat_channel_id"));
        try {
            if (chnl == null) {
                Log.info("Chat channel not found! Perhaps you have not changed the text channel id yet?");
            } else {
                channel = chnl;
                Log.info("Channel found! forwarding texts..");
                channel.sendMessage("Server started!").queue();
            }
        } catch (NullPointerException e) {
            Log.info("Chat channel not found! Perhaps you have not changed the text channel id yet?");
        }
    }

    public void sendDiscordMessage(String msg) {
        channel.sendMessage(msg).queue();
    }

    public JDA getJda() {
        return jda;
    }

    public TextChannel getChannel() {
        return channel;
    }
}
