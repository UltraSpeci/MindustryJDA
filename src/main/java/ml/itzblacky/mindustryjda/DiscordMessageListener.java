package ml.itzblacky.mindustryjda;

import io.anuke.arc.util.Log;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static ml.itzblacky.mindustryjda.Utils.ConfigUtils.getString;
import static ml.itzblacky.mindustryjda.Utils.Utils.sendMindustryMessage;

public class DiscordMessageListener extends ListenerAdapter {
    private Discord discord;

    public DiscordMessageListener(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (!(e.getMessage().getTextChannel() == discord.getChannel())) return;
        if (e.getMessage().getAuthor().isBot()) return;
        String toSend = getString("discord_to_mindustry_chat_format").replace("<playername>", e.getAuthor().getName())
                .replace("<rolecolor>", "")
                .replace("<message>", e.getMessage().getContentRaw());
        sendMindustryMessage(toSend);
        Log.info(toSend);
    }
}
