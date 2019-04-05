package game.state.bitSet;

import java.util.function.BiFunction;

public class ShiftableBitSet implements Cloneable {

    public static final int BITSPERWORD = Long.SIZE;
    private final long[] words;
    private final int size;

    public ShiftableBitSet(int size) {

        if (size < 0) {
            throw new NegativeArraySizeException("size < 0: " + size);
        }

        this.size = size;
        words = new long[(int) (Math.ceil((size * 1.0) / BITSPERWORD))];
    }

    public ShiftableBitSet(ShiftableBitSet shiftableBitSet) {

        this.words = shiftableBitSet.words.clone();
        this.size = shiftableBitSet.size;
    }

    public ShiftableBitSet or(ShiftableBitSet other) {

        merge(other, (a, b) -> a | b);
        return this;
    }

    public ShiftableBitSet and(ShiftableBitSet other) {

        merge(other, (a, b) -> a & b);
        return this;
    }

    public ShiftableBitSet xor(ShiftableBitSet other) {

        merge(other, (a, b) -> a ^ b);
        return this;
    }

    public ShiftableBitSet not() {

        merge(this, (a, b) -> ~a);
        return this;
    }

    public ShiftableBitSet clear() {

        for (int i = 0; i < words.length; i++) {
            words[i] = 0;
        }
        return this;
    }

    public ShiftableBitSet clear(int where) {

        int word = wordOffset(where);
        int bit = bitOffset(where);
        words[word] &= ~(1L << bit);
        return this;
    }

    public ShiftableBitSet set(int where) {

        int word = wordOffset(where);
        int bit = bitOffset(where);
        words[word] |= (1L << bit);
        return this;
    }

    public int get(int where) {

        int word = wordOffset(where);
        int bit = bitOffset(where);
        return (words[word] & (1L << bit)) != 0 ? 1 : 0;
    }

    private void merge(ShiftableBitSet other, BiFunction<Long, Long, Long> mergeTransform) {

        if (other.size != size) {
            throw new UnsupportedOperationException("Incompatible sizes");
        }
        for (int i = 0; i < words.length; i++) {
            words[i] = mergeTransform.apply(words[i], other.words[i]);
        }
        int bitOffset = bitOffset(size);
        if (bitOffset > 0) {
            words[words.length - 1] = words[words.length - 1] & ((1L << bitOffset) - 1);
        }
    }

    public ShiftableBitSet shiftLeft(int shiftAmount) {

        int word = wordOffset(shiftAmount);
        int bit = bitOffset(Math.abs(shiftAmount));
        if (shiftAmount == 0) {
            return this;
        }
        if (Math.abs(shiftAmount) >= size) {
            clear();
            return this;
        }
        if (shiftAmount > 0) {
            for (int i = words.length - 1; i >= word + 1; i--) {
                words[i] = (words[i - word] << bit) | overflowRight(words[i - word - 1], bit);
            }
            words[word] = (words[0] << bit);
            for (int i = 0; i < word; i++) {
                words[i] = 0;
            }
        } else {

            for (int i = 0; i < words.length + word - 1; i++) {
                words[i] = (words[i - word] >>> bit) | overflowLeft(words[i - word + 1], bit);
            }
            words[words.length + word - 1] = (words[words.length - 1] >>> bit);
            for (int i = words.length + word; i < words.length; i++) {
                words[i] = 0;
            }
        }

        return this;
    }

    private static int wordOffset(int shiftAmount) {

        return shiftAmount / BITSPERWORD;
    }

    private static int bitOffset(int shiftAmount) {

        return shiftAmount % BITSPERWORD;
    }

    private static long overflowRight(long w, int shift) {

        if (shift == 0) {
            return 0;
        }
        return w >>> (BITSPERWORD - shift);
    }

    private static long overflowLeft(long w, int shift) {

        if (shift == 0) {
            return 0;
        }
        return w << (BITSPERWORD - shift);
    }

    @Override
    public String toString() {

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int val = get(i);
            buf.append(val);
        }
        return buf.toString();
    }

    public ShiftableBitSet clone() {

        return new ShiftableBitSet(this);
    }
}
