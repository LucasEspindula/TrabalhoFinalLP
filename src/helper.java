import java.util.Random;

public class helper {
	
	public String gerador(int quantidade) {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
		 
		Random random = new Random();
		 
		String armazenaChaves = "";
		int index = -1;
		for( int i = 0; i < quantidade; i++ ) {
		   index = random.nextInt( letras.length() );
		   armazenaChaves += letras.substring( index, index + 1 );
		}
		return armazenaChaves;
//		System.out.println( armazenaChaves );
	}
}
