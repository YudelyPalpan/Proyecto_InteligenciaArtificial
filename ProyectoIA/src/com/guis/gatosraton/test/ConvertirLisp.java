package com.guis.gatosraton.test;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.armedbear.lisp.Fixnum;
import org.armedbear.lisp.Function;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Package;
import org.armedbear.lisp.Packages;
import org.armedbear.lisp.Symbol;

import com.guis.gatosraton.views.FormTablero;

public class ConvertirLisp {

	private Interpreter interpreter;
	private Package defaultPackage;

	// Direccion del archivo: src/lisp/gatos_raton.lisp
	public ConvertirLisp(String nombreArchivo) {
		interpreter = Interpreter.createInstance();
		interpreter.eval("(load \"" + nombreArchivo + "\")");
		defaultPackage = Packages.findPackage("CL-USER");
	}

	public LispObject getEActual() {
		Symbol myFunctionSym = defaultPackage.findAccessibleSymbol("ESTADO-ACTUAL");

		Function myFunction = (Function) myFunctionSym.getSymbolFunction();

		return myFunction.execute();
	}

	public void setEActual(LispObject estadoActual) {
		Symbol myFunctionSym = defaultPackage.findAccessibleSymbol("SET-EACTUAL");

		Function myFunction = (Function) myFunctionSym.getSymbolFunction();

		myFunction.execute(estadoActual);
	}

	public void convertirTablero(JButton[][] tablero, LispObject estadoActual) {

		LispObject tableroLisp[] = estadoActual.car().copyToArray();
		int i = 0, j = 0;
		for (LispObject fila : tableroLisp) {
			LispObject arrayFila[] = fila.copyToArray();
			for (LispObject casilla : arrayFila) {
				switch (casilla.printObject()) {
				case "1":
					tablero[i][j].setEnabled(true);
					tablero[i][j].setIcon(new ImageIcon(FormTablero.class.getResource("/com/guis/gatosraton/views/img/gatito.png")));
					break;
				case "9":
					tablero[i][j].setEnabled(true);
					tablero[i][j].setIcon(new ImageIcon(FormTablero.class.getResource("/com/guis/gatosraton/views/img/ratoncito.png")));
					break;
				case "0":
					tablero[i][j].setEnabled(false);
					tablero[i][j].setIcon(null);
					tablero[i][j].setText("");
					break;
				}

				j++;
			}
			j = 0;
			i++;
		}

	}

	public LispObject ejecutarAccion(String nombreAccion) {
		
		LispObject eActual = getEActual();
		
		Symbol myFunctionSym = defaultPackage.findAccessibleSymbol(nombreAccion);
		
		if(myFunctionSym != null) {
			Function myFunction = (Function) myFunctionSym.getSymbolFunction();
			return myFunction.execute(eActual, posicionRaton(eActual.car()));
		} else {
			return null;
		}
	}

	private LispObject posicionRaton(LispObject tablero) {

		Symbol myFunctionSym = defaultPackage.findAccessibleSymbol("POS-RATON");

		Function myFunction = (Function) myFunctionSym.getSymbolFunction();

		return myFunction.execute(tablero);
	}
	
	public LispObject jugadaMinimax() {
		Symbol myFunctionSym = defaultPackage.findAccessibleSymbol("ELECCION-MINIMAX");
		
		if(myFunctionSym != null) {
			Function myFunction = (Function) myFunctionSym.getSymbolFunction();
			return myFunction.execute(getEActual());
		} else {
			return null;
		}
	}
	
	public void iniciarJuego(int turno) {
		Symbol myFunctionSym = defaultPackage.findAccessibleSymbol("INICIAR-NUEVO-JUEGO");
		if(myFunctionSym != null) {
			Function myFunction = (Function) myFunctionSym.getSymbolFunction();
			myFunction.execute(Fixnum.getInstance(turno));
		} 
	}
	
	public String jugadorGanador() {
		String ganador = null;
		Symbol myFunctionSym = defaultPackage.findAccessibleSymbol("ES-GANADOR");
		if(myFunctionSym != null) {
			Function myFunction = (Function) myFunctionSym.getSymbolFunction();
			LispObject gatoGanador = myFunction.execute(getEActual());
			if(gatoGanador.printObject().equals("T")){
				ganador = "Ha ganado el gato";
			} else {
				myFunctionSym = defaultPackage.findAccessibleSymbol("ES-PERDEDOR");
				if(myFunctionSym != null) {
					myFunction = (Function) myFunctionSym.getSymbolFunction();
					LispObject ratonGanador = myFunction.execute(getEActual());
					ganador = (ratonGanador.printObject().equals("T")) ? "Ha ganado el raton" : null;
				} 
			}
		} 
		
		return ganador;
	}
}
