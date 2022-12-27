package org.genesis.toolbox.fundamental;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: TwoTuple
 * @Package org.genesis.toolbox.fundamental
 * @Description: two tupple generic
 * @date 2018/8/1 16:29
 */
public class TwoTuple<A, B> {
    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
    }
}

