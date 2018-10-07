package hw_5;

import java.util.ArrayList;
import java.util.Collections;

public class BaseNumber implements Comparable<BaseNumber> {
	private int n;
	private int base;
	// reverse 상태 ( 1_0_0 이라면 0,0,1 상태 )
	private ArrayList<Integer> repr;

	public BaseNumber(int n, int base) {
		this.n = n;
		this.base = base;
		repr = new ArrayList<>();
		this.convert();
	}

	public BaseNumber(ArrayList<Integer> repr, int base) {
		this.repr = repr;
		this.base = base;
		this.n = this.toInt();
	}

	private void convert() {
		int t = this.n;
		if (t == 0) {
			this.repr.add(0);
			return;
		}
		while (t > 0) {
			this.repr.add(t % this.base);
			t /= this.base;
		}
	}

	private int toInt() {
		int v = 0;
		int k = 0;
		for (int i : this.repr) {
			v += i * (int) Math.pow(this.base, k++);
		}
		return v;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Collections.reverse(this.repr);
		for (int i : this.repr)
			sb.append(i + "_");
		Collections.reverse(this.repr);
		return String.format("%s(%d)[%d]", sb.toString(), this.base, this.n);
	}

	public ArrayList<Integer> getRepr() {
		return repr;
	}
	// compareTo 할때마다 clone 만들어야 되나..?
	// 일단 이러는 이유는 return 문으로 바로 탈출해 버려
	// reverse 상태 복구가 힘들기 때문
	@Override
	public int compareTo(BaseNumber bn) {
		ArrayList<Integer> n1 = (ArrayList<Integer>) this.repr.clone();
		ArrayList<Integer> n2 = (ArrayList<Integer>) bn.getRepr().clone();
		Collections.reverse(n1);
		Collections.reverse(n2);
		if (this.repr.size() > n2.size())
			return 1;
		else if (this.repr.size() < n2.size())
			return -1;
		for (int i = 0; i < n2.size(); i++) {
			if (this.repr.get(i) > n2.get(i))
				return 1;
			else if (this.repr.get(i) < n2.get(i))
				return -1;
		}
		return 0;
	}

	public BaseNumber plus(BaseNumber bn) {
		int carryBit = 0;
		ArrayList<Integer> res = new ArrayList<>();
		ArrayList<Integer> bn2 = bn.getRepr();
		int n1, n2, stepSum;
		for (int i = 0; i < this.repr.size(); i++) {
			n1 = this.repr.get(i);
			n2 = bn2.get(i);
			System.out.format("%2d + %2d + carry(%d) = ", n1, n2, carryBit);
			
			stepSum = n1 + n2 + carryBit;
			if (stepSum >= this.base) {
				System.out.format("%d -> 1%n", stepSum -= this.base);
				carryBit = 1;
			} else {
				System.out.println(stepSum);
				carryBit = 0;
			}
			res.add(stepSum);
		}
		return new BaseNumber(res, this.base);
	}

	public BaseNumber minus(BaseNumber bn) {
		int borrowBit = 0;
		ArrayList<Integer> res = new ArrayList<>();
		ArrayList<Integer> bn2 = bn.getRepr();
		if (this.compareTo(bn) == 0)
			return new BaseNumber(0, this.base);
		if (this.compareTo(bn) < 0)
			return this;
		int n1, n2, stepSum;
		for (int i = 0; i < this.repr.size(); i++) {
			n1 = this.repr.get(i);
			if (i < bn2.size())
				n2 = bn2.get(i);
			else
				n2 = 0;
			System.out.format("%2d - %2d - borrow(%d) = ", n1, n2, borrowBit);
			
			stepSum = n1 - n2 - borrowBit;
			if (stepSum < 0) {
				System.out.format("%d -> -1%n", stepSum += this.base);
				borrowBit = 1;
			} else {
				System.out.println(stepSum);
				borrowBit = 0;
			}
			res.add(stepSum);
		}
		// trim
		int i = res.size() - 1;
		while(res.get(i) == 0)
			res.remove(i--);
		
		return new BaseNumber(res, this.base);
	}
}