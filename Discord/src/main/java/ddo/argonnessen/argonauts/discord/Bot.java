package ddo.argonnessen.argonauts.discord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.discord.cmd.CommandExecution;
import ddo.argonnessen.argonauts.discord.cmd.CommandExecutionMapper;
import ddo.argonnessen.argonauts.discord.handler.BotReadyEvent;
import ddo.argonnessen.argonauts.discord.handler.CommandHandler;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.EventDispatcher;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import reactor.core.publisher.Flux;

/**
 * bot service
 */
@Service
public class Bot {

	/**
	 * 
	 */
	@Autowired
	List<CommandExecution> map;

	/**
	 * 
	 */
	String token;
	
	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * register commands
	 * 
	 * @param c
	 * @param client
	 */
	void registerCommand(GatewayDiscordClient client) {
		CommandHandler ch = new CommandHandler();
		EventDispatcher eventDispatcher = client.getEventDispatcher();
		Flux<MessageCreateEvent> messageCreateEvent = eventDispatcher.on(MessageCreateEvent.class);
		Flux<Message> map1 = messageCreateEvent.map(MessageCreateEvent::getMessage);
		Flux<Message> filter = map1.filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false));
		Flux<Message> filter2 = filter.filter(ch);
		Flux<MessageChannel> flatMap = filter2.flatMap(Message::getChannel);
		Flux<Message> flatMap2 = flatMap.flatMap(ch);
		flatMap2.subscribe();
	}


	/**
	 * execute
	 */
	public void execute() {
		GatewayDiscordClient client = DiscordClientBuilder.create(token).build().login().block();
		client.getEventDispatcher().on(ReadyEvent.class).subscribe(new BotReadyEvent());
		registerCommand(client);
		for (Command c : Command.values()) {
			Class<? extends CommandExecution> commandExecution = CommandExecutionMapper.getCommandExecution(c);
			for (CommandExecution ce : map) {
				if (commandExecution.equals(ce.getClass())) {
					c.setCommandExecution(ce);
					break;
				}
			}
		}
		client.onDisconnect().block();
	}



}
