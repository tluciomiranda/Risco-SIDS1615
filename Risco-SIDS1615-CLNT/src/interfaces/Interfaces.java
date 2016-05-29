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
		String res; 
		
		while((res = loginInterface.getResult()) != null)
		{
			System.out.println(res);
			if(res.equals("Criar"))
			{
				this.loginInterface.setVisible(false);
				this.createAccInterface.setVisible(true);
				
				loginInterface.setResult(null);
			}
			else if(res.equals("LoginOK"))
			{
				System.out.println(res);
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