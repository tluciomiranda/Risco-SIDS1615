package interfaces;

public class Interfaces
{
	private Login loginInterface;
	private CreateAcc createAccInterface;
	
	
	// Login objects
	
	public Interfaces()
	{
		loginInterface = new Login();
		createAccInterface = new CreateAcc();
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
	
	public CreateAcc getCreateAccInterface()
	{
		return createAccInterface;
	}
	
	public void doStuff(){
		this.loginInterface.setVisible(false);
		this.createAccInterface.setVisible(true);
	}
}