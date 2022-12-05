package hash;

public class Hash_Function {
		public int m;
	public Hash_Function(int m) {
		this.m=m;
	}
	
	public int hMod(int k) {
		return k%m;
	}
	
	public int hMod(int k,int i) {
		return (k+i)%m;
	}
	public int hMul(int i) {
		//int A=Randint();
		//return (m*(kA mod 1));
		return 0;
	}
}
