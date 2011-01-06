package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.standard.Monopoly;
import soc.common.game.developmentCards.standard.RoadBuilding;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.developmentCards.standard.YearOfPlenty;
import soc.gwtClient.game.abstractWidgets.GamePanel;
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

public class DevelopmentCardBitmapMenuItem extends MenuItem
{
    DevelopmentCard developmentCard;
    MenuBar menuBar;
    GamePanel gamePanel;
    
    public DevelopmentCardBitmapMenuItem(DevelopmentCard devCard, GamePanel gamePanel)
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
        String location = "iconz/48/" + developmentCard.toString() + "48.png"; 
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
