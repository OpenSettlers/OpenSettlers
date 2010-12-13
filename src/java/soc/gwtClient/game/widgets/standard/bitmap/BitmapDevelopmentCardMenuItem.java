package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.Monopoly;
import soc.common.game.developmentCards.RoadBuilding;
import soc.common.game.developmentCards.Soldier;
import soc.common.game.developmentCards.VictoryPoint;
import soc.common.game.developmentCards.YearOfPlenty;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.dialogs.PlayMonopolyDialog;
import soc.gwtClient.game.dialogs.PlayRoadBuildingDialog;
import soc.gwtClient.game.dialogs.PlaySoldierDialog;
import soc.gwtClient.game.dialogs.PlayVictoryPointDialog;
import soc.gwtClient.game.dialogs.PlayYearOfPlentyDialog;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class BitmapDevelopmentCardMenuItem extends MenuItem
{
    DevelopmentCard developmentCard;
    MenuBar menuBar;
    IGamePanel gamePanel;
    
    public BitmapDevelopmentCardMenuItem(DevelopmentCard devCard, IGamePanel gamePanel)
    {
        super("", (Command) null);
        
        this.developmentCard=devCard;
        this.gamePanel=gamePanel;
        this.setHTML(getHtml());
        
        this.setCommand(new Command()
        {
            @Override
            public void execute()
            {
                createPlayDevelopmentCardDialogBox().show();
            }
        });
    }

    private String getHtml()
    {
        String location = "icons/48/" + developmentCard.toString() + "48.png"; 
        return "<img src=\"" + location + "\" />" + developmentCard.toString();
    }

    /**
     * @return the developmentCard
     */
    public DevelopmentCard getDevelopmentCard()
    {
        return developmentCard;
    }
    private DialogBox createPlayDevelopmentCardDialogBox()
    {
        DialogBox developmentCardDialog=null;
        
        if (developmentCard instanceof Soldier)
        {
            developmentCardDialog = new PlaySoldierDialog((Soldier)developmentCard, gamePanel);
        }
        if (developmentCard instanceof VictoryPoint)
        {
            developmentCardDialog = new PlayVictoryPointDialog((VictoryPoint)developmentCard, gamePanel);
        }
        if (developmentCard instanceof YearOfPlenty)
        {
            developmentCardDialog = new PlayYearOfPlentyDialog((YearOfPlenty)developmentCard, gamePanel);
        }
        if (developmentCard instanceof Monopoly)
        {
            developmentCardDialog = new PlayMonopolyDialog((Monopoly)developmentCard, gamePanel);
        }
        if (developmentCard instanceof RoadBuilding)
        {
            developmentCardDialog = new PlayRoadBuildingDialog((RoadBuilding)developmentCard, gamePanel);
        }
        
        return developmentCardDialog;
    }
    
}
