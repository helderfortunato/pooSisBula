package gravadores.arquivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import br.ufpb.sisbula.Medicamento;

public class GravadorMedi {
	/*
	 *
	 */
	public Map<String,Medicamento> leMedi() throws IOException{
		ObjectInputStream in = null;
		try{
			in = new ObjectInputStream(new FileInputStream("drogaria.txt"));
			return (Map<String,Medicamento>) in.readObject();
		}catch(FileNotFoundException e){
			throw new IOException("N�o foi encontrado o arquivo");
		}catch(IOException e){
			throw e;
		}catch(ClassNotFoundException e){
			throw new IOException("Classe n�o encontrada no arquivo");
		}finally {
			if(in !=null){
				in.close();
			}
		}
	}
	
	public void gravarMedi(Map<String,Medicamento> mapa) throws IOException{
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream (new FileOutputStream("drogaria.txt"));
		}catch(FileNotFoundException e){
			throw new IOException ("Arquivo n�o encontrado",e);
		}catch(IOException e){
			throw e;
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
}
