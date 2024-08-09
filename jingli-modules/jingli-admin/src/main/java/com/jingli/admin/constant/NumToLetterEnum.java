package com.jingli.admin.constant;

/**
 * @author liangbo
 * @version 1.0
 * @Description 数字转字母
 **/
public enum NumToLetterEnum {

    /**
     * 数字转字母
     */
    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    D(4, "D"),
    E(5, "E"),
    F(6, "F"),
    G(7, "G"),
    H(8, "G"),
    I(9, "I"),
    J(10, "J"),
    K(11, "K"),
    L(12, "L"),
    M(13, "M"),
    N(14, "N"),
    O(15, "O"),
    P(16, "P"),
    Q(17, "Q"),
    R(18, "R"),
    S(19, "S"),
    T(20, "T"),
    U(21, "U"),
    V(22, "V"),
    W(23, "W"),
    X(24, "X"),
    Y(25, "Y"),
    Z(26, "Z"),
    ;

    private final Integer code;

    private final String letter;

    NumToLetterEnum(Integer code, String message) {
        this.code = code;
        this.letter = message;
    }

    public static NumToLetterEnum match(int key) {
        NumToLetterEnum result = null;
        for (NumToLetterEnum s : values()) {
            if (s.getCode() == key) {
                result = s;
                break;
            }
        }
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public String getLetter() {
        return letter;
    }
}
