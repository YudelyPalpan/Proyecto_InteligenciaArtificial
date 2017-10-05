;tablero
(setq tablero
      '(
        (1 0 1 0 1 0 1 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 9 0 0 0 0)
        )
)

(setq tab_gato
      '(
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 1 0 1 0 0 0)
        (0 0 0 9 0 0 0 0)
        (0 0 1 0 1 0 0 0)
        (0 0 0 0 0 0 0 0)
        )
)

(setq tab_raton
      '(
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 0 9 0 0 0 0)
        (0 0 1 0 1 0 0 0)
        (0 0 0 0 0 0 0 0)
        (0 0 1 0 0 0 1 0)
        (0 0 0 0 0 0 0 0)
      )
)
; Turno actual
(setq tr 1) 
; Avanzar al gato a la derecha
(defun avanzar-gato-derecha (estado p-ficha-mov)
  (let
    (
     ; La funcion copy-tree crea una copia pero no liga las variables
     (tab (copy-tree (nth 0 estado)))
     ; Se establece el turno del jugador en la variable TURNO
     (turno (nth 1 estado))
     ; Pos i de la ficha
     (i (nth 0 p-ficha-mov))
     ; Pos j de la ficha
     (j (nth 1 p-ficha-mov))
     )
    (cond
      (
       (and (and (< i 8) (< j 8)) 
            (and (< (+ i 1) 8) (< (+ j 1) 8)) 
            (= turno 1)
            (= (nth (+ j 1) (nth (+ i 1) tab)) 0)
            (= (nth j (nth i tab)) 1)
            )
       (setf (nth (+ j 1) (nth (+ i 1) tab)) 1)
       (setf (nth j (nth i tab)) 0)
       (list tab 9)
       )
      )
    )
  )
; Avanzar gato a la izquierda
(defun avanzar-gato-izquierda (estado p-ficha-mov)
  (let
    (
     (tab (copy-tree (nth 0 estado)))
     (turno (nth 1 estado))
     (i (nth 0 p-ficha-mov))
     (j (nth 1 p-ficha-mov))
     )
    (cond
      (
       (and (and (< i 8)  (>= j 0))
            (and (< (+ i 1) 8)  (>= (- j 1) 0)) 
            (= turno 1) 
            (= (nth (- j 1) (nth (+ i 1) tab)) 0)
            (= (nth j (nth i tab)) 1)
            )
       
       (setf (nth (- j 1) (nth (+ i 1) tab)) 1)
       (setf (nth j (nth i tab)) 0)
       (list tab 9)
       )
      )
    )
  )
; Avanzar raton a la derecha
(defun avanzar-raton-derecha (estado p-ficha-mov)
  (let
    (
     (tab (copy-tree (nth 0 estado)))
     (turno (nth 1 estado))
     (i (nth 0 p-ficha-mov))
     (j (nth 1 p-ficha-mov))
     )
    (cond
      (
       (and (and (>= i 0)  (< j 8))
            (and (>= (- i 1) 0)  (< (+ j 1) 8)) 
            (= turno 9)
            (= (nth (+ j 1) (nth (- i 1) tab)) 0)
            (= (nth j (nth i tab)) 9)
            )
       
       (setf (nth (+ j 1) (nth (- i 1) tab)) 9)
       (setf (nth j (nth i tab)) 0)
       (list tab 1)
       )
      )
    )
  )
; Avanzar raton a la izquierda
(defun avanzar-raton-izquierda (estado p-ficha-mov)
  (let
    (
     (tab (copy-tree (nth 0 estado)))
     (turno (nth 1 estado))
     (i (nth 0 p-ficha-mov))
     (j (nth 1 p-ficha-mov))
     )
    (cond
      (
       (and (and (>= i 0)  (>= j 0)) 
            (and (>= (- i 1) 0)  (>= (- j 1) 0)) 
            (= turno 9) 
            (= (nth (- j 1) (nth (- i 1) tab)) 0)
            (= (nth j (nth i tab)) 9)
            )
       
       (setf (nth (- j 1) (nth (- i 1) tab)) 9)
       (setf (nth j (nth i tab)) 0)
       (list tab 1)
       )
      )
    )
  )

; Retroceder raton izquierda
(defun retro-raton-derecha (estado p-ficha-mov)
  (let
    (
     (tab (copy-tree (nth 0 estado)))
     (turno (nth 1 estado))
     (i (nth 0 p-ficha-mov))
     (j (nth 1 p-ficha-mov))
     )
    (cond
      ; Primera condicion
      (
       ; Comprobacion
       (and (and (< i 8)  (< j 8)) 
            (and (< (+ i 1) 8)  (< (+ j 1) 8)) 
            (= turno 9) 
            (= (nth (+ j 1) (nth (+ i 1) tab)) 0)
            (= (nth j (nth i tab)) 9)
            )
       ; Acciones
       (setf (nth (+ j 1) (nth (+ i 1) tab)) 9)
       (setf (nth j (nth i tab)) 0)
       (list tab 1)
       )
      )
    )
  )
; Retroceder raton derecha
(defun retro-raton-izquierda (estado p-ficha-mov)
  (let
    (
     (tab (copy-tree (nth 0 estado)))
     (turno (nth 1 estado))
     (i (nth 0 p-ficha-mov))
     (j (nth 1 p-ficha-mov))
     )
    (cond
      ; Primera condicion
      (
       ; Comprobacion
       (and (and (< i 8)  (>= j 0)) 
            (and (< (+ i 1) 8) (>= (- j 1) 0)) 
            (= turno 9) 
            (= (nth (- j 1) (nth (+ i 1) tab)) 0)
            (= (nth j (nth i tab)) 9)
            )
       ; Acciones
       (setf (nth (- j 1) (nth (+ i 1) tab)) 9)
       (setf (nth j (nth i tab)) 0)
       (list tab 1)
       )
      )
    )
  )

(defun vista-jugada (estado)
  (cond 
    ((not estado) "Jugada no valida")
    ((not (not estado)) 
     (imprimir-tablero (nth 0 estado))
     (format t "~&Turno: ~S" (nth 1 estado))
    )
  )
)

(defun imprimir-tablero (tablero)
  (dolist (fila tablero)
    (format t "~&~S" fila)
    )
  )

(defun suma-fila (fila)
  ((lambda (sum)
     (mapcar #' (lambda (i) (setq sum (+ sum i))) fila)
     sum
     ) 0)
  )

(defun sumar-filas-adelante (tablero n)
  ((lambda (sum)
     (dotimes (i n) (setq sum (+ sum (suma-fila (nth i tablero)))))
     sum
     ) 0)
  )

(defun pos-raton (tablero)
  ((lambda (x y)
     (dotimes (i 8)
       (dotimes (j 8)
         (cond 
           ((= (nth j (nth i tablero)) 9)
            (setq x i)
            (setq y j)
            )
           )
         )
       )
     (list x y)
     ) 0 0)
)

(defun pos-gatos (tablero)
    ((lambda (pos-gatos)
     (dotimes (i 8)
       (dotimes (j 8)
         (cond 
           ((= (nth j (nth i tablero)) 1)
            (setq pos-gatos (append pos-gatos (list (list i j))))
            )
           )
         )
       )
       pos-gatos
     ) nil)
)

(defun raton-encerrado (estado)
  ((lambda (tab i j turno)
     (if (not (and (avanzar-raton-izquierda estado)
                   (avanzar-raton-derecha estado)
                   (retro-raton-izquierda estado)
                   (retro-raton-derecha estado))) t nil)
     ) (copy-tree (nth 0 estado)) (nth 0 (nth 1 estado)) (nth 1 (nth 1 estado)) (nth 2 estado))
  )

(defun test-parada (estado)
  ((lambda (tab i j turno)
     (cond
       ((= turno 9) (if (= (sumar-filas-adelante tab i) 0) "Ha ganado el raton"))
       ((= turno 1) (if (raton-encerrado estado) "Ha ganado el gato"))
       )
     ) (copy-tree (nth 0 estado)) (nth 0 (nth 1 estado)) (nth 1 (nth 1 estado)) (nth 2 estado))
)

(defun gen-sucesores (estado p-ficha-mov)
  (setq sucesores nil)
  (cond
   ((= (nth 1 estado) 9)
    (if (avanzar-raton-izquierda estado p-ficha-mov) 
        (setq sucesores (append sucesores (list (avanzar-raton-izquierda estado p-ficha-mov)))))
    (if (avanzar-raton-derecha estado p-ficha-mov) 
        (setq sucesores (append sucesores (list (avanzar-raton-derecha estado p-ficha-mov)))))
    (if (retro-raton-izquierda estado p-ficha-mov) 
        (setq sucesores (append sucesores (list (retro-raton-izquierda estado p-ficha-mov)))))
    (if (retro-raton-derecha estado p-ficha-mov) 
        (setq sucesores (append sucesores (list (retro-raton-derecha estado p-ficha-mov)))))
   )
   ((= (nth 1 estado) 1)
    (if (avanzar-gato-izquierda estado p-ficha-mov) 
        (setq sucesores (append sucesores (list (avanzar-gato-izquierda estado p-ficha-mov)))))
    (if (avanzar-gato-derecha estado p-ficha-mov) 
        (setq sucesores (append sucesores (list (avanzar-gato-derecha estado p-ficha-mov)))))
    )
   )
  sucesores
)

(defun mostrar-sucesores (sucesores)
  (mapcar #' (lambda (sucesor) (vista-jugada sucesor)) sucesores)
)
