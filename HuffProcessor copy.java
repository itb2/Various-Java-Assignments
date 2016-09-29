import java.util.PriorityQueue;

public class HuffProcessor implements Processor {
	private int[] count;
	private HuffNode root;
	private String[] paths = new String[257];

	public void compress(BitInputStream in, BitOutputStream out) {
		count(in);
		huffmanTree();
		extractCodes(root, "");

		out.writeBits(BITS_PER_INT, HUFF_NUMBER); // adding the HuffNumber
		writeHeader(root, out);

		writeBody(in, out);

	}

	public void count(BitInputStream in) {

		count = new int[256];
		int value;
		while ((value = in.readBits(BITS_PER_WORD)) != -1) {
			count[value]++;
		}
		// System.out.println(Arrays.toString(count));
		in.reset();
	}

	public void huffmanTree() {
		PriorityQueue<HuffNode> pq = new PriorityQueue<HuffNode>();
	
		for (int i = 0; i < 256; i++) {
			if (count[i] != 0) {
				HuffNode huff = new HuffNode(i, count[i]); // not adding the
															// character but its
															// pos in the count?
				pq.add(huff);
				// System.out.println(s++);
			}
		}
		pq.add(new HuffNode(PSEUDO_EOF, 0));

		while (pq.size() > 1) {
			HuffNode a = pq.poll();
			HuffNode b = pq.poll();
			HuffNode parent = new HuffNode(-1, a.weight() + b.weight(), a, b);
			pq.add(parent);

		}
		root = pq.poll();

	}

	public void extractCodes(HuffNode current, String path) {

		if (current.left() == null && current.right() == null) {

			paths[current.value()] = path;
			// System.out.println(current.value() + " " + path);
			return;
		}

		extractCodes(current.left(), path + 0);
		extractCodes(current.right(), path + 1);

	}

	public void writeHeader(HuffNode current, BitOutputStream out) {
		if (current.left() == null && current.right() == null) {
			out.writeBits(1, 1);
			out.writeBits(9, current.value());
			
			return;
		}

		out.writeBits(1, 0);
		writeHeader(current.left(), out);
		writeHeader(current.right(), out);

	}

	public void writeBody(BitInputStream in, BitOutputStream out) {
		int val;
		//int n = 0;
		while ((val = in.readBits(BITS_PER_WORD)) != -1) {
			String code = paths[val];
			
			
			out.writeBits(code.length(), Integer.parseInt(code, 2));
			
			// n++;
			// if (n== 769) System.out.println(val + "-" + code);
		}
		String code = paths[PSEUDO_EOF];
		out.writeBits(code.length(), Integer.parseInt(code, 2));
		// in.reset();
	}

	@Override
	public void decompress(BitInputStream in, BitOutputStream out) {
		
			if (in.readBits(BITS_PER_INT) != HUFF_NUMBER) throw new HuffException("Header is not there");
			
			root = readHeader(in);
			
			 HuffNode current = root;
			 int num;
			 while ((num = in.readBits(1)) != -1){
				 if (num == 1) current = current.right();
				 else current = current.left();
				 
				 if (current.left()== null && current.right() == null){
					 if (current.value() == PSEUDO_EOF) return;
					 else{
						 out.writeBits(8, current.value());
						 current = root;
					 }
				 }
			 }
			 throw new HuffException("There's nothing in the file");
	}
	private HuffNode readHeader(BitInputStream in){
		if (in.readBits(1) == 0 ){
			HuffNode left = readHeader(in);
			HuffNode right = readHeader(in);
			return new HuffNode(-1, left.weight() + right.weight(), left, right);
		}else{
			return new HuffNode(in.readBits(9), 0 );
		}
		
	}

}
