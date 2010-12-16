package soc.common.actions.gameAction;

/*
 * A generic message from the server. Currently used as error message
 * for debugging purposes. POssible future use is for example servers
 * rebooting.
 */
public class MessageFromServer extends GameAction
{
    private String serverMessage;

    /**
     * @return the serverMessage
     */
    public String getServerMessage()
    {
        return serverMessage;
    }

    /**
     * @param serverMessage the serverMessage to set
     */
    public MessageFromServer setServerMessage(String serverMessage)
    {
        this.serverMessage = serverMessage;
    
        return this;
    } 
}
