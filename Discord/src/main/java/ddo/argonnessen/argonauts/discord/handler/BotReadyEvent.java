/**
 * 
 */
package ddo.argonnessen.argonauts.discord.handler;

import java.util.function.Consumer;

import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;

/**
 * 
 */
public class BotReadyEvent implements Consumer<ReadyEvent> {

	@Override
	public void accept(ReadyEvent event) {
		User self = event.getSelf();
		System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator())); //$NON-NLS-1$
	}
}
