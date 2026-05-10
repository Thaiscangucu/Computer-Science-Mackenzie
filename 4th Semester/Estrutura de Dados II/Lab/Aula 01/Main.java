/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class Main
{
	public static void main(String[] args) {
		Cachorro meuPet = new Cachorro("Totó",1,2025,"Labrador","caramelo", 5, false);
		Cachorro meuPet2 = new Cachorro();
		/*
		System.out.println("Nome = " + meuPet.nome);
		System.out.println("Raça = " + meuPet.raca);
		System.out.println("Energia = " + meuPet.energia);
		System.out.println("------");
		System.out.println("Nome = " + meuPet2.nome);
		System.out.println("Raça = " + meuPet2.raca);
		System.out.println("Energia = " + meuPet2.energia);
		System.out.println("------");
		*/
		System.out.println(meuPet);
		meuPet.andar();
		System.out.println(meuPet);
		//meuPet.energia = -8;
		meuPet.setEnergia(20);
		System.out.println(meuPet);
		System.out.println("------");
		System.out.println(meuPet.getEnergia());
		
	}
}