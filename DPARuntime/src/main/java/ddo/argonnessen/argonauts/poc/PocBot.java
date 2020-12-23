package ddo.argonnessen.argonauts.poc;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

/**
 * 
 */
public class PocBot {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String TOKEN = args[0];
		GatewayDiscordClient client = DiscordClientBuilder.create(TOKEN).build().login().block();

		client.getEventDispatcher().on(ReadyEvent.class).subscribe(event -> {
			User self = event.getSelf();
			System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator())); //$NON-NLS-1$
		});
		client.getEventDispatcher().on(MessageCreateEvent.class).map(MessageCreateEvent::getMessage)
				.filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
				.filter(message -> message.getContent().equalsIgnoreCase("!ping")) //$NON-NLS-1$
				.flatMap(Message::getChannel)
				.flatMap(channel -> channel.createMessage("Pong!")).subscribe(); //$NON-NLS-1$
		Iterable<Guild> iterable = client.getGuilds().toIterable();
		for (Guild guild : iterable) {
			System.out.println("guild:" + guild.getName()); //$NON-NLS-1$
			Mono<Member> owner = guild.getOwner();
			Member block = owner.block();
			System.out.println("guild owner:" + block.getUsername()); //$NON-NLS-1$
			Iterable<Member> iterable2 = guild.getMembers().toIterable();
			for (Member m : iterable2) {
				System.out.print("guildy:" + m.getNickname().get()); //$NON-NLS-1$
				if (m.isBot()) {
					System.out.println("/bot"); //$NON-NLS-1$
				} else {
					System.out.println();
				}
			}
			System.out.println("----"); //$NON-NLS-1$
		}
		// client.onDisconnect().block();
	}
}
