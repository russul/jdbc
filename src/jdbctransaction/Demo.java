package jdbctransaction;

public class Demo {

	public static void main(String[] args) {
		AccountService accountService = new AccountService();
		accountService.transfer("ls", "zs", 100);

	}

}
