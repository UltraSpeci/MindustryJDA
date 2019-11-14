package ml.itzblacky.mindustryjda;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static ml.itzblacky.mindustryjda.Utils.sendMindustryMessage;

public class DiscordMessageListener extends ListenerAdapter {
    private Discord discord;

    public DiscordMessageListener(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getTextChannel() == discord.getChannel()) {
            sendMindustryMessage("[Discord] " + e.getAuthor().getName() + " " + e.getMessage().getContentRaw());
        }
    }
}
