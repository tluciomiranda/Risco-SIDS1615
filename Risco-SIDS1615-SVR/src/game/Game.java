package game;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Game implements HttpHandler 
{
	public Game()
	{
		//cenas
	}

	@Override
	public void handle(HttpExchange e) throws IOException 
	{
		
	}
	
	public int getNrPlayers()
	{
		return 2; //alterar para calculo do nr de players actual
	}
}
