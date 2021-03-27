package mp.sucess.color;

import org.bukkit.event.Listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

public class ChatEvent implements Listener{
	//												#123456
	private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		event.setMessage(format(event.getMessage()));
	}
	
	private String format(String msg) {
		if (Bukkit.getVersion().contains("1.16")) {
			//hex colors
			Matcher match = pattern.matcher(msg);
			while (match.find()) {
				String color = msg.substring(match.start(), match.end());
				msg = msg.replace(color, ChatColor.of(color) + "");
				match = pattern.matcher(msg);
			}
		}
		return ChatColor.translateAlternateColorCodes('&', msg);
		
	}
}
