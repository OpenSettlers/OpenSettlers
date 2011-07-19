package org.soc.gwt.server;

import java.io.IOException;

import javax.servlet.ServletException;

import net.zschech.gwt.comet.server.CometServlet;
import net.zschech.gwt.comet.server.CometServletResponse;
import net.zschech.gwt.comet.server.CometSession;

public class CatanServlet extends CometServlet
{
    private static final long serialVersionUID = -8819586416016822076L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.zschech.gwt.comet.server.CometServlet#doComet(net.zschech.gwt.comet
     * .server.CometServletResponse)
     */

    @Override
    protected void doComet(CometServletResponse cometResponse)
            throws ServletException, IOException
    {
        CometSession cometSession = cometResponse.getSession();
        // cometResponse.write(wc);
    }
}
