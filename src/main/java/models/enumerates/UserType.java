package models.enumerates;

public enum UserType {
	ADMINISTRATOR("admin",1),RECEPCIONIST("recepcionist",2),CUSTOMER("customer",3);
	private final String opcao;

	private UserType(String opcao,int cod) {
		this.opcao = opcao;
	}

	public String getOpcao() {
		return opcao;
	}
}
