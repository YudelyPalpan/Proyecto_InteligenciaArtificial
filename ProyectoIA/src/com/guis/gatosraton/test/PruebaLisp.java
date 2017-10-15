package com.guis.gatosraton.test;

import org.armedbear.lisp.Cons;
import org.armedbear.lisp.Fixnum;
import org.armedbear.lisp.Function;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.Package;
import org.armedbear.lisp.Packages;
import org.armedbear.lisp.Symbol;

public class PruebaLisp {
	public static void main(String[] args) {

		Interpreter interpreter = Interpreter.createInstance();
		
		interpreter.eval("(load \"src/lisp/gatos_raton.lisp\")");
		System.out.println("Objeto Cargado");
		
		Package defaultPackage = Packages.findPackage("CL-USER");
		// Cogiendo una funcion de lisp en JAVA
		
		Symbol myFunctionSym = defaultPackage.findAccessibleSymbol("HOLA-MUNDO");
		
		Function myFunction = (Function) myFunctionSym.getSymbolFunction();
		
		Cons list = (Cons) myFunction.execute(Fixnum.getInstance(25));
		System.out.println(list.printObject());
	}
}
