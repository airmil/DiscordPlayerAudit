package ddo.argonnessen.argonauts.discord.handler;

import java.util.function.Function;
import java.util.function.Predicate;

import org.reactivestreams.Publisher;

import ddo.argonnessen.argonauts.discord.CommandBean;
import ddo.argonnessen.argonauts.discord.CommandParser;
import ddo.argonnessen.argonauts.discord.cmd.CommandExecution;
import ddo.argonnessen.argonauts.discord.exception.CommandException;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * 
 */
public class CommandHandler implements Predicate<Message>, Function<MessageChannel, Publisher<Message>> {

	/**
	 * 
	 */
	private CommandBean commandBean;

	/**
	 * command parser
	 */
	CommandParser parser = new CommandParser();

	@Override
	public boolean test(Message t) {
		String lowerCase = t.getContent().toLowerCase();
		commandBean = parser.parse(lowerCase);
		return commandBean != null;
	}

	@Override
	public Publisher<Message> apply(MessageChannel t) {
		CommandExecution commandExecution = commandBean.getCommand().getCommandExecution();
		String execute;
		try {
			execute = commandExecution.execute(commandBean);
		} catch (CommandException e) {
			execute = e.getMessage();
		}
		return t.createMessage(execute);
	}
}
