; tablero
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

; Turno actual
(setq tr 1) 
; Avanzar al gato a la derecha
(defun avanzar-gato-derecha (estado)
   (let
    (
     (tab (nth 0 estado))
     (turno (nth 2 estado))
     (i (nth 0 (nth 1 estado)))
     (j (nth 1 (nth 1 estado)))
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
       (setf tr 9)
       (list tab (list (+ i 1) (+ j 1)) 9)
       )
      )
   )
)
; Avanzar gato a la izquierda
(defun avanzar-gato-izquierda (estado)
  (let
    (
     (tab (nth 0 estado))
     (turno (nth 2 estado))
     (i (nth 0 (nth 1 estado)))
     (j (nth 1 (nth 1 estado)))
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
       (setf tr 9)
       (list tab (list (+ i 1) (- j 1)) 9)
      )
     )
   )
)
; Avanzar raton a la derecha
(defun avanzar-raton-derecha (estado)
  (let
    (
     (tab (nth 0 estado))
     (turno (nth 2 estado))
     (i (nth 0 (nth 1 estado)))
     (j (nth 1 (nth 1 estado)))
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
       (setf tr 1)
       (list tab (list (- i 1) (+ j 1)) 1)
      )
     )
   )
)
; Avanzar raton a la izquierda
(defun avanzar-raton-izquierda (estado)
  (let
    (
     (tab (nth 0 estado))
     (turno (nth 2 estado))
     (i (nth 0 (nth 1 estado)))
     (j (nth 1 (nth 1 estado)))
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
       (setf tr 1)
       (list tab (list (- i 1) (- j 1)) 1)
      )
     )
   )
)

; Retroceder raton izquierda
(defun retro-raton-derecha (estado)

  (let
    (
     (tab (nth 0 estado))
     (turno (nth 2 estado))
     (i (nth 0 (nth 1 estado)))
     (j (nth 1 (nth 1 estado)))
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
       (setf tr 1)
       (list tab (list (+ i 1) (+ j 1)) 1)
      )
     )
   )
)
; Retroceder raton derecha
(defun retro-raton-izquierda (estado)
  (let
    (
     (tab (nth 0 estado))
     (turno (nth 2 estado))
     (i (nth 0 (nth 1 estado)))
     (j (nth 1 (nth 1 estado)))
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
       (setf tr 1)
       (list tab (list (+ i 1) (- j 1)) 1)
      )
     )
   )
)

(defun vista-jugada (estado)
  (cond 
   ((not estado) "Jugada no valida")
   ((not (not estado)) (imprimir-tablero (nth 0 estado))
    (format t "~&Posicion: ~S" (nth 1 estado))
    (FORMAT T "~&TURNO: ~S" (NTH 2 ESTADO))  
   )
   )
 )

(defun imprimir-tablero (tablero)
  (dolist (fila tablero)
     (format t "~&~S" fila)
  )
)

; Nosotros mi amor <3
(defun mensaje (mensaje)
 print "holi amotito *-*"
 print "ati?"
)
(list 'si 'le 'hago 'un 'cambio 'como 'subo '?)