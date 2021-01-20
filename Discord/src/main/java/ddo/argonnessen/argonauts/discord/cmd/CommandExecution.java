/**
 * 
 */
package ddo.argonnessen.argonauts.discord.cmd;

import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.arch.Transformation;
import ddo.argonnessen.argonauts.discord.CommandBean;

/**
 * 
 */
@Service
public interface CommandExecution extends Transformation<CommandBean, String> {
	// empty
}
