package interfaces;

public class Interfaces
{
	private Login loginInterface;
	//private Choose
	
	// Login objects
	
	public Interfaces()
	{
		loginInterface = new Login();
	}
	
	public void controlaInterfaces()
	{
		this.loginInterface.setVisible(true);
				
		while(true)
		{
			if(!loginInterface.isVisible())
			{
				if(!loginInterface.getCreateAccount().isVisible())
				{
					//lancar interface de escolha de salas
				}
			}
		}
	}
	
	public Login getLoginUI()
	{
		return loginInterface;
	}
}