package soc.common.actions.gameAction;

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
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    } 
}
