import computation.contextfreegrammar.*;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.HashSet;

public class MyGrammar {
	public static ContextFreeGrammar makeGrammar() {

		// You can write your code here to make the context-free grammar from the assignment
		// List of all required variables

		Variable s_0 = new Variable("S0");
		Variable s = new Variable('S');
		Variable t = new Variable('T');
		Variable f = new Variable('F');
		Variable t_1 = new Variable("T1");
		Variable f_1 = new Variable("F1");
		Variable s_1 = new Variable("S1");
		Variable l = new Variable('L');
		Variable r = new Variable('R');
		Variable p = new Variable('P');
		Variable m = new Variable('M');

		HashSet<Variable> variables = new HashSet<>();
		variables.add(s_0);
		variables.add(s);
		variables.add(t);
		variables.add(f);
		variables.add(t_1);
		variables.add(f_1);
		variables.add(s_1);
		variables.add(l);
		variables.add(r);
		variables.add(p);
		variables.add(m);

		// List of all required terminals

		Terminal plus = new Terminal('+');
		Terminal times = new Terminal('*');
		Terminal left_b = new Terminal('(');
		Terminal right_b = new Terminal(')');
		Terminal one = new Terminal('1');
		Terminal zero = new Terminal('0');
		Terminal x = new Terminal('x');

		HashSet<Terminal> terminals = new HashSet<>();
		terminals.add(plus);
		terminals.add(times);
		terminals.add(left_b);
		terminals.add(right_b);
		terminals.add(one);
		terminals.add(zero);
		terminals.add(x);

		// List of rules in our language

		Rule l0 = new Rule(m, new Word(times));
		Rule l1 = new Rule(p, new Word(plus));
		Rule l2 = new Rule(r, new Word(right_b));
		Rule l3 = new Rule(l, new Word(left_b));
		Rule l4 = new Rule(s_1, new Word(s, r));
		Rule l5 = new Rule(f_1, new Word(m, f));
		Rule l6 = new Rule(t_1, new Word(p, t));
		Rule l7 = new Rule(f, new Word(l, s_1));
		Rule l8 = new Rule(f, new Word(one));
		Rule l9 = new Rule(f, new Word(zero));
		Rule l10 = new Rule(f, new Word(x));
		Rule l11 = new Rule(t, new Word(t, f_1));
		Rule l12 = new Rule(t, new Word(l, s_1));
		Rule l13 = new Rule(t, new Word(one));
		Rule l14 = new Rule(t, new Word(zero));
		Rule l15 = new Rule(t, new Word(x));
		Rule l16 = new Rule(s, new Word(s, t_1));
		Rule l17 = new Rule(s, new Word(t, f_1));
		Rule l18 = new Rule(s, new Word(l, s_1));
		Rule l19 = new Rule(s, new Word(one));
		Rule l20 = new Rule(s, new Word(zero));
		Rule l21 = new Rule(s, new Word(x));
		Rule l22 = new Rule(s_0, new Word(s, t_1));
		Rule l23 = new Rule(s_0, new Word(t, f_1));
		Rule l24 = new Rule(s_0, new Word(l, s_1));
		Rule l25 = new Rule(s_0, new Word(one));
		Rule l26 = new Rule(s_0, new Word(zero));
		Rule l27 = new Rule(s_0, new Word(x));


		ArrayList<Rule> rules = new ArrayList<>();
		rules.add(l0);
		rules.add(l1);
		rules.add(l2);
		rules.add(l3);
		rules.add(l4);
		rules.add(l5);
		rules.add(l6);
		rules.add(l7);
		rules.add(l8);
		rules.add(l9);
		rules.add(l10);
		rules.add(l11);
		rules.add(l12);
		rules.add(l13);
		rules.add(l14);
		rules.add(l15);
		rules.add(l16);
		rules.add(l17);
		rules.add(l18);
		rules.add(l19);
		rules.add(l20);
		rules.add(l21);
		rules.add(l22);
		rules.add(l23);
		rules.add(l24);
		rules.add(l25);
		rules.add(l26);
		rules.add(l27);

		ContextFreeGrammar cfg = new ContextFreeGrammar(variables, terminals, rules, s_0);
		return cfg;
	}
}
