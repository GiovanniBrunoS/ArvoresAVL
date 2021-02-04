import java.util.ArrayList;
import java.util.List;

import avl.BinaryTreeAVL;
import avl.DataAVL;
import binary.BinaryTreeBin;
import binary.DataBin;

public class Main {

	public static void main(String args[]) {
		compararTrees();
		
		BinaryTreeAVL tree = new BinaryTreeAVL();
		tree.insertNode(new DataAVL(50));
		tree.insertNode(new DataAVL(25));
		tree.insertNode(new DataAVL(65));
		tree.insertNode(new DataAVL(10));
		tree.insertNode(new DataAVL(30));
		tree.insertNode(new DataAVL(60));
		tree.insertNode(new DataAVL(70));
		tree.insertNode(new DataAVL(5));
		tree.insertNode(new DataAVL(55));
		tree.insertNode(new DataAVL(68));
		tree.insertNode(new DataAVL(80));
		tree.insertNode(new DataAVL(67));
		tree.insertNode(new DataAVL(69));
		
		System.out.println(testarBalanceamento(tree));
		System.out.println();
		
		tree.insertNode(new DataAVL(2));
		tree.insertNode(new DataAVL(4));
		tree.insertNode(new DataAVL(3));
		tree.insertNode(new DataAVL(1));
		tree.insertNode(new DataAVL(9));
		tree.insertNode(new DataAVL(8));
		tree.insertNode(new DataAVL(7));
		tree.insertNode(new DataAVL(6));
		
		System.out.println(tree);
		System.out.println();
		removerDireita(tree);
		System.out.println(tree);
	}

	static void compararTrees() {
		
		List<Integer> listValues = new ArrayList<>();
		List<Integer> listArvoreBinMedia = new ArrayList<>();
		List<Integer> listArvoreAVLMedia = new ArrayList<>();

		for (int i = 0; i < 10000; i++) {
			BinaryTreeAVL btavl = new BinaryTreeAVL();
			BinaryTreeBin btbin = new BinaryTreeBin();
			do {
				int n = (int) (Math.random() * 100000) + 1;
				if (!listValues.contains(n)) {
					listValues.add(n);
					btavl.insertNode(new DataAVL(n));
					btbin.insertNode(new DataBin(n));
				}
			} while (listValues.size() < 20000);
			listArvoreAVLMedia.add(btavl.getRoot().getHeight());
			listArvoreBinMedia.add(btbin.getRoot().getHeight());
			listValues.clear();
			if(i%100==0) {
				System.out.println(i);
			}
		}

		System.out.println(calcularMedia(listArvoreAVLMedia));
		System.out.println(calcularMedia(listArvoreBinMedia));
	}
	
	static String calcularMedia(List<Integer> lista) {
		
		int sum = 0;
		int menor = lista.get(0);
		int maior = lista.get(0);
		double media;
		for (Integer altura : lista) {
			if (altura < menor)
				menor = altura;
			if (altura > maior)
				maior = altura;
			sum += altura;
		}
		media = sum / lista.size();
		return "Menor = " + menor + ", Maior = " + maior + ", Media = " + media;
	}

	static Boolean testarBalanceamento(BinaryTreeAVL tree) {
		return tree.balanceCheck();
	}
	
	static void removerDireita(BinaryTreeAVL tree) {
		tree.removeRight();
		tree.rebalanceTree();
	}
}
