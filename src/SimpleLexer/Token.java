package SimpleLexer;

//添加自定义关键字的标签名  VOID
class Tag {
	public final static int MAIN = 256, WHILE = 257, DO = 258, IF = 259, ELSE = 260, FOR = 261, NUMBER = 262,
			REAL = 263, TRUE = 264, FALSE = 265, BASIC = 266, ID = 267, LE = 268, GE = 269, NE = 270, EQ = 271,
			AND = 272, OR = 273, VOID = 274;
}

public class Token {
	public final int tag;

	public Token(int t) {
		tag = t;
	}

	public String toString() {
		String temp = "";
		switch (tag) {
		case '+':
		case '-':
		case '*':
		case '/':
		case '=':
		case '<':
		case '>':
			temp = "OPERATOR,";
			break;
		case '(':
		case ')':
		case '{':
		case '}':
		case ';':
			temp = "DELIMITER,";
			break;
		}
		return "<" + temp + (char) tag + ">";
	}
}

class Num extends Token {
	public final int value;

	public Num(int v) {
		super(Tag.NUMBER);
		value = v;
	}

	public String toString() {
		return "<NUMBER," + value + ">";
	}
}

class Real extends Token {
	public final float value;

	public Real(float v) {
		super(Tag.REAL);
		value = v;
	}

	public String toString() {
		return "<REAL," + value + ">";
	}
}

class Word extends Token {
	public String lexeme = "";

	public Word(String s, int tag) {
		super(tag);
		lexeme = s;
	}

	public String toString() {
		String temp = "";
		switch (tag) {
		case Tag.MAIN:
		case Tag.WHILE:
		case Tag.DO:
		case Tag.IF:
		case Tag.ELSE:
		case Tag.FOR:
		case Tag.VOID:  //作业：自定义关键字
			temp = "KEYWORD";
			break;
		case Tag.ID:
			temp = "ID";
			break;
		case Tag.TRUE:
		case Tag.FALSE:
			temp = "BOOLVALUE";
			break;
		case Tag.LE:
		case Tag.GE:
		case Tag.NE:
		case Tag.EQ:
			temp = "OPERATOR";
			break;
		}
		return "<" + temp + "," + lexeme + ">";
	}
    //添加自定义关键字 void 类名为voi
	public static final Word and = new Word("&&", Tag.AND), or = new Word("||", Tag.OR), eq = new Word("==", Tag.EQ),
			ne = new Word("<>", Tag.NE), le = new Word("<=", Tag.LE), ge = new Word(">=", Tag.GE),
			voi = new Word("void", Tag.VOID);

	public static final Word True = new Word("true", Tag.TRUE), False = new Word("false", Tag.FALSE);
}

class Type extends Word {
	public int width = 0;

	public Type(String s, int tag, int w) {
		super(s, tag);
		width = w;
	}

	public String toString() {
		return "<BASICTYPE," + lexeme + ">";
	}

	public static final Type Int = new Type("int", Tag.BASIC, 4), Float = new Type("float", Tag.BASIC, 8),
			Char = new Type("char", Tag.BASIC, 1), Bool = new Type("bool", Tag.BASIC, 1);

}
