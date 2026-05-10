/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
		Cachorro meuDog = new Cachorro("Totó", 5, false);
		Gato meuGato = new Gato("Mimi", 5, false);
		Scanner sc = new Scanner(System.in);
		int opcao;
		Animal meuPet;
		System.out.println("Você deseja adotar:\n");
		System.out.println("1 - cachorro");
		System.out.println("2 - gato");
		opcao = sc.nextInt();
		if(opcao == 1)
		  meuPet = new Cachorro();
		else
		  meuPet = new Gato();
		
		meuPet.som();
		System.exit(0);
		System.out.println("=========================");
		
		
		
		System.out.println("DADOS DO MEU DOGUINHO:\n\n");
		System.out.println(meuDog);
		meuDog.andar();
		System.out.println(meuDog);
		meuDog.comer(10);
		System.out.println(meuDog);
		System.out.println("------");
		System.out.println(meuDog.getEnergia());
		meuDog.som();
		
		System.out.println("DADOS DO MEU GATINHO:\n\n");
		System.out.println(meuGato);
		meuGato.andar();
		System.out.println(meuGato);
		meuGato.comer(10);
		System.out.println(meuGato);
		System.out.println("------");
		System.out.println(meuGato.getEnergia());
		meuGato.som();
	}
}