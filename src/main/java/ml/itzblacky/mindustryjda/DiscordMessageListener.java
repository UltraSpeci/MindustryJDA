package ml.itzblacky.mindustryjda;

import io.anuke.arc.util.Log;
import io.anuke.mindustry.Vars;
import io.anuke.mindustry.content.Items;
import io.anuke.mindustry.entities.type.Player;
import io.anuke.mindustry.world.modules.ItemModule;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static ml.itzblacky.mindustryjda.Discord.getHexColor;
import static ml.itzblacky.mindustryjda.Utils.ColorUtils.removeColorString;
import static ml.itzblacky.mindustryjda.Utils.ConfigUtils.getString;
import static ml.itzblacky.mindustryjda.Utils.Utils.isCommand;
import static ml.itzblacky.mindustryjda.Utils.Utils.sendMindustryMessage;

public class DiscordMessageListener extends ListenerAdapter {
    private Discord discord;
    private String prefix = getString("prefix");

    public DiscordMessageListener(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getTextChannel() == discord.getChannel() && !isCommand(prefix, e.getMessage().getContentRaw())) {
            if (e.getMessage().getAuthor().isBot()) return;
            String hex = getHexColor(e.getGuild().getMember(e.getMessage().getAuthor()));
            String toSend = getString("discord_to_mindustry_chat_format").replace("<playername>", e.getAuthor().getName())
                    .replace("<rolecolor>", hex)
                    .replace("<message>", e.getMessage().getContentRaw());
            sendMindustryMessage(toSend);
            Log.info(toSend);
        }
        if (e.getMessage().getContentRaw().startsWith(prefix + "resources")) {
            if (e.getMessage().getAuthor().isBot()) return;
            if (!Vars.state.rules.waves) {
                e.getChannel().sendMessage("This command is available only in Survival mode due to limitations!").queue();
                return;
            }
            if (Vars.playerGroup.isEmpty()) {
                e.getChannel().sendMessage("No player is online!").queue();
                return;
            }
            ItemModule core = Vars.playerGroup.all().get(0).getClosestCore().items;
            StringBuilder toSend = new StringBuilder();
            toSend.append("Copper: " + core.get(Items.copper) + "\n");
            toSend.append("Lead: " + core.get(Items.lead) + "\n");
            toSend.append("Graphite: " + core.get(Items.graphite) + "\n");
            toSend.append("Titanium: " + core.get(Items.titanium) + "\n");
            toSend.append("Silicon: " + core.get(Items.silicon) + "\n");
            toSend.append("Metaglass: " + core.get(Items.metaglass) + "\n");
            toSend.append("Thorium: " + core.get(Items.thorium) + "\n");
            toSend.append("Plastanium: " + core.get(Items.plastanium) + "\n");
            toSend.append("Phase Fabric: " + core.get(Items.phasefabric) + "\n");
            toSend.append("Surge Alloy: " + core.get(Items.surgealloy) + "\n");
            e.getChannel().sendMessage(toSend.toString()).queue();
            return;
        }
        if (e.getMessage().getContentRaw().equalsIgnoreCase(prefix + "info")) {
            if (e.getMessage().getAuthor().isBot()) return;
            try {
                StringBuilder toSend = new StringBuilder();
                String mapName = removeColorString(Vars.world.getMap().name());
                String mapAuthor = removeColorString(Vars.world.getMap().author());
                int waveCount = Vars.state.wave;
                int enemyCount = Vars.state.enemies;
                int playerCount = Vars.playerGroup.size();
                toSend.append("Map: " + mapName + " ([author]) \n".replace("[author]", mapAuthor));
                toSend.append("Wave: " + waveCount + "\n");
                toSend.append("Enemies Alive: " + enemyCount + "\n");
                toSend.append("Players: " + playerCount + "\n");
                e.getChannel().sendMessage(toSend.toString()).queue();
            } catch (NullPointerException npe) {
                e.getChannel().sendMessage("Game have not been started yet!");
            }
            return;
        }
        if (e.getMessage().getContentRaw().equalsIgnoreCase(prefix + "players")) {
            if (e.getMessage().getAuthor().isBot()) return;
            if (Vars.playerGroup.isEmpty()) {
                e.getChannel().sendMessage("No Player is online!").queue();
                return;
            }
            StringBuilder toSend = new StringBuilder();
            toSend.append("Players: \n");
            for (Player p : Vars.playerGroup.all()) {
                toSend.append(removeColorString(p.name) + ", ");
            }
            e.getChannel().sendMessage(toSend.toString()).queue();
            return;
        }
    }
}
