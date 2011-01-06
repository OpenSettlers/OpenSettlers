Open Settlers - An open client-server implementation of Settlers

Introduction
------------

OpenSettlers is a web-based version of the board game Settlers of Catan.

This client-server system supports multiple simultaneous games between 
people and computer-controlled opponents. Initially created as an AI research project 
under the name of JSettlers, later renamed to OpenSettlers. 

The client can be run by simply opening an html document: an alpha preview is visible
at http://opensettlers.sourceforge.net. 

The server can either be run on the client (playing against bots only supported),
or run on any webserver supporting java (not yet implemented).

Keep in mind that soc.common, soc.gwtClient and soc.gwtServer is new OpenSettlers code, 
where all the other packages in soc are old JSettlers code. In the future, we will make
an effort to rewrite the JSettlers code to the soc.common, soc.gwtClient and soc.gwtServer
packages, as well adding new packages to support other types of connections (instead of
only comet/websocket) and clients (applets/javaFX).

OpenSettlers is an open-source project licensed under the GPL version 3. The software is 
hosted at 

http://github.com/generateui/OpenSettlers.  

Questions, bugs, and patches can be directed there.

 -- The OpenSettlers Development Team


Contents
--------
  Documentation
  	Documentation is created using our wiki taking advantage of tagged articles.
  	See our wiki at https://sourceforge.net/apps/mediawiki/opensettlers/index.php?title=Main_Page.
  Requirements
    Client: web browser (single player against bots only)
    Server: A host running a java webserver 
  Setting up and testing
  Shutting down the server
  Hosting an OpenSettlers Server
  Development and Compiling