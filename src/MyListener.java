/**
 * PACKAGE_NAME
 * brief description of the program
 *
 * @author Nikhil Narayana, naraya12@purdue.edu
 * CS180 Section 811
 */

import org.apache.commons.codec.binary.StringUtils;
import org.jsoup.helper.StringUtil;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class MyListener extends ListenerAdapter {
    public static void main(String[] args) throws Exception {
        //Configure what we want our bot to do
        Configuration configuration = new Configuration.Builder()
                .setName("fakyou") //Set the nick of the bot. CHANGE IN YOUR CODE
                .setServerHostname("irc.dtella.net") //Join the freenode network
                .addAutoJoinChannel("#fak") //Join the official #pircbotx channel
                .addListener(new MyListener()) //Add our listener that will be called on Events
                .buildConfiguration();

        //Create our bot with the configuration
        PircBotX bot = new PircBotX(configuration);
        //Connect to the server
        bot.startBot();
    }

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        int fak = org.apache.commons.lang3.StringUtils.countMatches(event.getMessage(),"fak");
        for (int i = 0; i < fak; i++)
            event.getBot().sendIRC().message("#fak", "Fak you");

        if (event.getMessage().equals("yo"))
            event.getBot().sendIRC().message("#fak", "no");

        if(event.getMessage().equals("!leave")) {
            event.getBot().sendIRC().message("#fak", "ok bye");
            event.getBot().sendIRC().quitServer();
        }
    }
}
